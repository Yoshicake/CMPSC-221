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
import java.sql.Timestamp;

public class ScheduleQueries {
    private static Connection connection;
    
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlisted;
    private static PreparedStatement dropStudentSchedule;
    private static PreparedStatement dropSchedule;
    private static PreparedStatement updateScheduleEntry;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry scheduleEntry)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?,?,?,?,?) ");
            addScheduleEntry.setString(1, scheduleEntry.getSemester());
            addScheduleEntry.setString(2, scheduleEntry.getCourseCode());
            addScheduleEntry.setString(3, scheduleEntry.getStudentID());
            addScheduleEntry.setString(4, scheduleEntry.getStatus());
            addScheduleEntry.setTimestamp(5,scheduleEntry.getTimestamp());
            addScheduleEntry.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }   
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        

        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = (?) and studentID = (?)");
            getScheduleByStudent.setString(1,semester);
            getScheduleByStudent.setString(2,studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                String Semester = resultSet.getString("semester");
                String coursecode = resultSet.getString("coursecode");
                String studentid = resultSet.getString("studentid");
                String status = resultSet.getString("status");
                Timestamp time = resultSet.getTimestamp("timestamp");
                ScheduleEntry s = new ScheduleEntry(Semester,coursecode,studentid,status,time);
                schedule.add(s);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
        
    
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int scheduledStudents = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(*) from app.schedule where semester = (?) and coursecode = (?) and status = 'S'");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            if (resultSet.next()){
                scheduledStudents = resultSet.getInt(1);
                System.out.println(scheduledStudents);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlisted = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp " +
                       "from app.schedule " +
                       "where semester = ? " +
                       "and courseCode = ? " +
                       "and status = 'W' " +
                       "order BY app.schedule.timestamp ASC");
            getWaitlisted.setString(1,semester);
            getWaitlisted.setString(2,courseCode);
            resultSet = getWaitlisted.executeQuery();
            
            while(resultSet.next())
            {
                
                ScheduleEntry s = new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getTimestamp(5));
                waitlist.add(s);
                 
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode) {
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudentSchedule = connection.prepareStatement("delete from app.schedule where semester = (?) and studentid = (?) and coursecode = (?)");
            dropStudentSchedule.setString(1,semester);
            dropStudentSchedule.setString(2,studentID);
            dropStudentSchedule.setString(3,courseCode);
            dropStudentSchedule.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudentSchedule = connection.prepareStatement("delete from app.schedule where semester = (?) and coursecode = (?)");
            dropStudentSchedule.setString(1,semester);
            dropStudentSchedule.setString(2,courseCode);
            dropStudentSchedule.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateScheduleEntry = connection.prepareStatement("update app.schedule set status = (?) where semester = (?) and coursecode = (?) and studentID = (?)");
            updateScheduleEntry.setString(1, "S");
            updateScheduleEntry.setString(2, entry.getSemester());
            updateScheduleEntry.setString(3, entry.getCourseCode());
            updateScheduleEntry.setString(4, entry.getStudentID());
            updateScheduleEntry.executeUpdate();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
