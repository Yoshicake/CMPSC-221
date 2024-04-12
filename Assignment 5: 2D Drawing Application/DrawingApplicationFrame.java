/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


/**
 *
 * @author acv
 */
public class DrawingApplicationFrame extends JFrame
{
    
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    private JPanel one = new JPanel();
    private JPanel two = new JPanel();
    private JPanel combined = new JPanel();
    // create the widgets for the firstLine Panel.
    private JLabel shape = new JLabel("Shape:");
    private String shapes[] = {"line", "oval", "rectangle"};
    private JComboBox shapeChoice = new JComboBox<>(shapes);
    private JButton firstColor = new JButton("1st Color...");
    private JButton secondColor = new JButton("2nd Color...");
    private JButton undo = new JButton("Undo");
    private JButton clear = new JButton("Clear");
    //create the widgets for the secondLine Panel.
    private JLabel options = new JLabel("Options:");
    private JCheckBox filled = new JCheckBox("Filled");
    private JCheckBox gradient = new JCheckBox("Use Gradient");
    private JCheckBox dashed = new JCheckBox("Dashed");
    private JLabel width = new JLabel("Line Width:");
    private JSpinner lineWidth = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
    private JLabel length = new JLabel("Dash Length");
    private JSpinner dashLength = new JSpinner(new SpinnerNumberModel(10, 1, 99, 1));
    

    // Variables for drawPanel.
    DrawPanel drawPanel = new DrawPanel();
    Color color1 = Color.BLACK;
    Color color2 = Color.BLACK;
    int lw = 15;
    float dl = 10;
    
    
    ArrayList<MyShapes> shapeList = new ArrayList<MyShapes>();
    // add status label
    JLabel status = new JLabel("");
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {   
        setLayout(new BorderLayout());
        combined.setLayout(new BorderLayout());
        // add widgets to panels
        // firstLine widgets
        one.add(shape);
        one.add(shapeChoice);
        one.add(firstColor);
        one.add(secondColor);
        one.add(undo);
        one.add(clear);
        // secondLine widgets
        two.add(options);
        two.add(filled);
        two.add(gradient);
        two.add(dashed);
        two.add(width);
        two.add(lineWidth);
        two.add(length);
        two.add(dashLength);
        // add top panel of two panels
        combined.add(one,BorderLayout.NORTH);
        combined.add(two,BorderLayout.SOUTH);
        // add topPanel to North, drawPanel to Center, and statusLabel to South
        add(combined,BorderLayout.NORTH);
        add(drawPanel,BorderLayout.CENTER);
        add(status,BorderLayout.SOUTH);
        //add listeners and event handlers
        
        firstColor.addActionListener(e -> {color1 = JColorChooser.showDialog(null, "Select Color 1", color1);
        });
        secondColor.addActionListener(e -> {color2 = JColorChooser.showDialog(null, "Select Color 2", color2);
        });
        undo.addActionListener(e -> {
            try {
                shapeList.remove(shapeList.size()-1);
                drawPanel.repaint();
            }
            catch (Exception ex){
                System.out.println("Array empty when undo");
            }
            
        });
        clear.addActionListener(e -> {
            shapeList = new ArrayList<MyShapes>();
            drawPanel.repaint();
        });
        
    }

    // Create event handlers, if needed
    private MyShapes addShape(Point start, Point end) {
        Paint paint;
        BasicStroke stroke;
        
        if (gradient.isSelected()){
            paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
        } else {
            paint = new GradientPaint(0, 0, color1, 50, 50, color1, true);
        }
        
        if (dashed.isSelected()) {
            float[] dash = {dl};
            stroke = new BasicStroke(lw, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dash, 0);
        } else {
            stroke = new BasicStroke(lw, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        }
        
        if ((shapeChoice.getSelectedItem().toString())=="line") {
            return new MyLine(start, end, paint, stroke);
        } else if ((shapeChoice.getSelectedItem().toString())=="rectangle") {
            return new MyRectangle(start, end, paint, stroke, filled.isSelected());
        } else if ((shapeChoice.getSelectedItem().toString())=="oval") {
            return new MyOval(start, end, paint, stroke, filled.isSelected());
        } 
        return null;
    }
    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {
        private Point start;
        private Point end;
        private ArrayList<MyShapes> tempShapes = new ArrayList<MyShapes>();
        public DrawPanel()
        {
            setBackground(Color.WHITE);
            addMouseListener(new MouseHandler());
            addMouseMotionListener(new MouseHandler());
            
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            for (MyShapes shape:shapeList) {
                shape.draw(g2d);
            }
            for (MyShapes shape:tempShapes) {
                shape.draw(g2d);
            }
            
            tempShapes = new ArrayList<MyShapes>(); 
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                start = event.getPoint();
                lw = (Integer)lineWidth.getValue();
                dl = (Integer)dashLength.getValue();
                
                
            }
            
            @Override
            public void mouseReleased(MouseEvent event)
            {
                end = event.getPoint();
                MyShapes shape = addShape(start, end);
                if (shape != null) {
                    shapeList.add(shape);
                    drawPanel.repaint();
                }
                start = new Point();
                end = new Point();
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
                Point temp = event.getPoint();
                status.setText(String.format("(%d, %d)", event.getX(), event.getY()));
                MyShapes shape = addShape(start, temp);
                if (shape != null) {
                    tempShapes.add(shape);
                    drawPanel.repaint();
                }
                
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                status.setText("("+event.getX()+", "+event.getY()+")");
            }
        }

    }
}
