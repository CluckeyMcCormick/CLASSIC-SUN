/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.sql.*;
import java.util.*;

/**
 * This class generates database queries for us
 * @author nicolas.fredrickson1 & Timothy Holcombe
 */
public class QueryGenerator {
    
    private static final String EVENT_TABLE_NAME = "EVENTS";
    private static final String USER_TABLE_NAME = "USERS";   
    public static final String[] eventColumns={"ID","EVENTNAME","EVENTCREATOR","DATE","WARNING","LOCATION","BADWEATHERS","DESCRIPTION","INVITED","ACCEPTED"};
    public static final String[] userColumns={"ID","EMAIL","INBOX"};
    
    /**
     * Generates a query that gets the number of entries in the user table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryUser() throws SQLException{
        return "SELECT COUNT(*) FROM USERS;";
    }
    
    /**
     * Generates a query that gets the number of entries in the event table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryEvent(){
        
        return "SELECT COUNT(*) FROM EVENTS;";
    }
    
    public static String idQueryUser(){
        return "SELECT ID FROM USERS;";
    }
    
    public static String idQueryEvent(){
        return "SELECT ID FROM EVENTS;";
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching id - so, basically one entry.
     * 
     * @param eventId The id for the event we are trying to retrieve
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(int eventId){
        return "SELECT * FROM EVENTS WHERE ID = '"+eventId+"';";
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching creator.
     * 
     * @param creator The creator-user whose events we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(User creator){
        return "SELECT * FROM EVENTS WHERE EVENTCREATOR = '"+creator.getEmail()+"';";
    }
    
    /**
     * Generates a query that retrieves the entry of the provided user
     * 
     * @param user The user whose info we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryUser(User user){
        return "SELECT * FROM USERS WHERE EMAIL = '"+user.getEmail()+"';";
    }
    
    /**
     * Generates a query that updates the entry of the provided event
     * 
     * @param e The event to update - this should be the UPDATED version
     * @return A SQL command in the form of a string
     */
    public static String updateQueryEvent(Event e){//check all event .gets return strings or good values
        StringBuilder queBuild = new StringBuilder();
        queBuild.append("UPDATE EVENTS SET ");        
        
        for(int i = 1; i < eventColumns.length; ++i)
        {
            queBuild.append(eventColumns[i] + " = ");
            switch(i)
            {
                //Event Name
                case 1:
                    queBuild.append("'" + e.getName().replaceAll("'", "''") + "', ");
                    break;
                //Event Creator
                case 2:
                    queBuild.append("'" + e.getCreator() + "', ");
                    break;
                //Date
                case 3:
                    queBuild.append("'" + Factory.calendarToString(e.getDate()) + "', ");
                    break;
                //Warning
                case 4:
                    queBuild.append(e.getWarningPeriod() + ", ");
                    break;
                //Location
                case 5:
                    queBuild.append("'" + e.getLocation() + "', ");
                    break;
                //Bad Weathers
                case 6:
                    queBuild.append("'" + Factory.StringListToString(e.getGoodWeather()) + "', ");
                    break;
                //Description
                case 7:
                    queBuild.append("'" + e.getDescription().replaceAll("'", "''") + "', ");
                    break;
                //Invited
                case 8:
                    queBuild.append("'" + Factory.StringListToString(e.getInvited()) + "', ");
                    break;
                //Accepted
                case 9:
                    queBuild.append("'" + Factory.StringListToString(e.getAccepted()) + "' ");
                    break;
            }
        }
        
        queBuild.append("WHERE ID = "+e.getId()+";");
        
        return queBuild.toString();
    }
    
    /**
     * Generates a query that updates the entry of the provided user
     * 
     * @param user The user to update - this should be the UPDATED version
     * @return A SQL command in the form of a string
     * 
     */
    public static String updateQueryUserInbox(User user){
        String inbox=Factory.IntegerListToString(user.getInvites());
        return "UPDATE USERS SET INBOX = '" + inbox + "' WHERE ID = "+user.getID()+";";
    }
    
    /**
     * Generates a query that inserts the entry of the provided user
     * 
     * @param user The user to insert - this should be the INSERTED version
     * @return A SQL command in the form of a string
     */
    public static String insertQueryEvent(Event e){ //getDate needs a string conversion************** done!
        StringBuilder queBuild = new StringBuilder();
        queBuild.append("INSERT INTO EVENTS (");
        
        for(int i = 0; i < eventColumns.length; ++i) 
        {
            queBuild.append(eventColumns[i]);
            
            if(i < eventColumns.length - 1)
            {
                queBuild.append(",");
            }
        }
        
        queBuild.append(") VALUES (");
        queBuild.append(e.getId() + ", ");
        queBuild.append("'" + e.getName().replaceAll("'", "''") + "', ");
        queBuild.append("'" + e.getCreator() + "', ");
        queBuild.append("'" + Factory.calendarToString(e.getDate()) + "', ");
        queBuild.append(e.getWarningPeriod() + ", ");
        queBuild.append("'" + e.getLocation() + "', ");
        queBuild.append("'" + Factory.StringListToString(e.getGoodWeather()) + "', ");
        queBuild.append("'" + e.getDescription().replaceAll("'", "''") + "', ");
        queBuild.append("'" + Factory.StringListToString(e.getInvited()) + "', ");
        queBuild.append("'" + Factory.StringListToString(e.getAccepted()) + "');");
        
        return queBuild.toString();
    }
    /**
     * Generates a query that inserts the entry of the provided user
     * 
     * @param user The user to insert - this should be the INSERTED version
     * @return A SQL command in the form of a string
     */
    public static String insertQueryUser(User user){
        return "INSERT INTO USERS (ID,EMAIL,INBOX)"+
               "VALUES ("+user.getID()+", '"+user.getEmail()+"', '"+Factory.IntegerListToString(user.getInvites())+"');";
    }
    /**
     * Generates a query that deletes an entry in the event table 
     * with the matching id - so, basically one entry.
     * 
     * @param eventId The id for the event we are trying to retrieve
     * @return A SQL command in the form of a string
     */
    public static String deleteQueryEvent(int eventId){
        return "DELETE FROM EVENTS WHERE ID = "+eventId+" RETURNING *;";
    }
    
    /**
     * Generates a query that deletes an entry in the event table 
     * with the matching creator.
     * 
     * @param creator The creator-user whose events we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String deleteQueryEvent(User creator){
        return "DELETE FROM EVENTS WHERE EVENTCREATOR = "+creator.getID()+" RETURNING *;";
    }
    
    /**
     * Generates a query that deletes the entry of the provided user
     * 
     * @param user The user whose info we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String deleteQueryUser(User user){
        return "DELETE FROM USERS WHERE EMAIL = "+user.getEmail()+"RETURNING *;";
    }
}
