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

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    private static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptions = new ArrayList<ClassDescription>();
        try
        {
            statement = connection.prepareStatement("select app.class.courseCode, description, seats from app.class, app.course where semester = ? and app.class.courseCode = app.course.courseCode order by app.class.courseCode");
            statement.setString(1,semester);
            resultSet = statement.executeQuery();
            
            while(resultSet.next())
            {
                ClassDescription cd = new ClassDescription(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3));
                classDescriptions.add(cd);
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classDescriptions;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> scheduledStudents = new ArrayList<StudentEntry>();
        try
        {
            statement = connection.prepareStatement("select app.student.studentid, firstname, lastname, status " +
                       "from app.student, app.schedule " +
                       "where app.schedule.semester = ? " +
                       "and app.schedule.courseCode = ? " +
                       "and app.student.studentid = app.schedule.studentid " +
                       "and app.schedule.status = 'S'");
            statement.setString(1,semester);
            statement.setString(2,courseCode);
            resultSet = statement.executeQuery();
            
            while(resultSet.next())
            {
                
                StudentEntry s = new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                scheduledStudents.add(s);
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
     
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlist = new ArrayList<StudentEntry>();
        try
        {
            statement = connection.prepareStatement("select app.student.studentid, firstname, lastname, status " +
                       "from app.student, app.schedule " +
                       "where app.schedule.semester = ? " +
                       "and app.schedule.courseCode = ? " +
                       "and app.student.studentid = app.schedule.studentid " +
                       "and app.schedule.status = 'W'" +
                       "order BY app.schedule.timestamp ASC");
            statement.setString(1,semester);
            statement.setString(2,courseCode);
            resultSet = statement.executeQuery();
            
            while(resultSet.next())
            {
                
                StudentEntry s = new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                waitlist.add(s);
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
}
