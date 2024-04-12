/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author pacma
 */
public class PizzaFrame extends JFrame {
    private final JLabel title;
    private final JLabel description;
    private JTextField inches;
    private JButton calculate;
    private JLabel end;
    private JPanel line2;
    
    
    public PizzaFrame(){
        super("Pizza Servings Calculator");
        setLayout(new GridLayout(4,1));
        
        title = new JLabel("Pizza Servings Calculator");
        add(title);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.RED);
        title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        description = new JLabel("Enter the size of the pizza in inches:");
        inches = new JTextField(4);
        line2 = new JPanel();
        line2.add(description);
        line2.add(inches);
        add(line2);
        
        calculate = new JButton("Calculate Servings");
        add(calculate);
        calculate.addActionListener(new ButtonHandler());
        
        end = new JLabel("");
        end.setHorizontalAlignment(SwingConstants.CENTER);
        add(end);
        
    }
    
    private class ButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent event){
            double size = Double.parseDouble(inches.getText());
            double servings = (size / 8);
            servings = Math.pow(servings,2);
            end.setText(String.format("A %s inch pizza will serve %.2f people." ,inches.getText(),servings));

        }
    }
    
}
