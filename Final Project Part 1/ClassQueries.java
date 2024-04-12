
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pacma
 */
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassList;
    private static PreparedStatement getClassDescription;
    private static PreparedStatement getSeats;
    private static ResultSet classResultSet;
    private static ResultSet seatsResultSet;
    
    
    public static void addClass(ClassEntry cl)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addClass = connection.prepareStatement("insert into app.class (semester, coursecode, seats, description) values (?,?,?,?) ");
            addClass.setString(1, cl.getSemester());
            addClass.setString(2, cl.getCourseCode());
            addClass.setInt(3, cl.getSeats());
            addClass.setString(4,CourseQueries.getCourseDescription(cl.getCourseCode()));
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }   
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCode = new ArrayList<String>();
        try
        {
            getClassList = connection.prepareStatement("select coursecode from app.class where semester = (?)");
            getClassList.setString(1,semester);
            classResultSet = getClassList.executeQuery();
            
            while(classResultSet.next())
            {
                
                courseCode.add(classResultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCode;
        
    }
    
    public static ArrayList<ClassEntry> getAllClasses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassEntry> classes = new ArrayList<ClassEntry>();
        try
        {
            getClassList = connection.prepareStatement("select * from app.class where semester = (?)");
            getClassList.setString(1,semester);
            classResultSet = getClassList.executeQuery();
            
            while(classResultSet.next())
            {
                ClassEntry c = new ClassEntry(classResultSet.getString(1), classResultSet.getString(2), classResultSet.getInt(3));
                classes.add(c);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classes = new ArrayList<ClassDescription>();
        try
        {
            getClassList = connection.prepareStatement("select * from app.class where semester = (?) ");
            getClassList.setString(1,semester);
            classResultSet = getClassList.executeQuery();
            
            while(classResultSet.next())
            {
                ClassDescription c = new ClassDescription(classResultSet.getString(2),classResultSet.getString(4),classResultSet.getInt(3));
                classes.add(c);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int seats = -1;
        try
        {
            getSeats = connection.prepareStatement("select seats from app.class where semester = (?) and coursecode = (?) limit 1");
            getSeats.setString(1, semester);
            getSeats.setString(2, courseCode);
            seatsResultSet = getSeats.executeQuery();
            
            if(seatsResultSet.next()){
                seats =  seatsResultSet.getInt("seats");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
    }
    
}  
    
    
    
    

