/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pacma
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseDescription;
    private static PreparedStatement getCourseList;
    private static ResultSet resultSet;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (coursecode, description) values (?, ?) ");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static String getCourseDescription(String courseCode)
    {
        connection = DBConnection.getConnection();
        String description = "";
        try
        {
            getCourseDescription = connection.prepareStatement("select description from app.course where coursecode = (?)");
            getCourseDescription.setString(1,courseCode);
            resultSet = getCourseDescription.executeQuery();
            
            if(resultSet.next())
            {
                description = resultSet.getString("description");
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return description;        
    }
    public static ArrayList<String> getAllCourseCodes()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCode = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select coursecode from app.course");
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                courseCode.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCode;
        
    }
    
    
    
}
