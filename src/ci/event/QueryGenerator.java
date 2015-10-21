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
    private static String[] eventColumns={"ID","EVENTNAME","EVENTCREATOR","DATE","WARNING","LOCATION","BADWEATHERS","DESCRIPTION","INVITED","ACCEPTED"};
    private static String[] userColumns={"ID","EMAIL","INBOX"};
    
    /**
     * Generates a query that gets the number of entries in the user table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryUser() throws SQLException{
        return "SELECT COUNT(*) FROM USERS";
    }
    
    /**
     * Generates a query that gets the number of entries in the event table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryEvent(){
        
        return "SELECT COUNT(*) FROM EVENTS";
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching id - so, basically one entry.
     * 
     * @param eventId The id for the event we are trying to retrieve
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(int eventId){
        return "SELECT * FROM EVENTS WHERE ID = "+eventId;
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching creator.
     * 
     * @param creator The creator-user whose events we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(User creator){
        return "SELECT * FROM EVENTS WHERE EVENTCREATOR = "+creator.getEmail();
    }
    
    /**
     * Generates a query that retrieves the entry of the provided user
     * 
     * @param user The user whose info we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryUser(User user){
        return "SELECT * FROM USERS WHERE EMAIL = "+user.getEmail();
    }
    
    /**
     * Generates a query that updates the entry of the provided event
     * 
     * @param e The event to update - this should be the UPDATED version
     * @return A SQL command in the form of a string
     */
    public static String updateQueryEvent(Event e){//check all event .gets return strings or good values
        String toReturn= "UPDATE EVENTS SET ";
        toReturn+=eventColumns[1]+" = "+e.getName()+", "+eventColumns[2]+" = "+e.getCreator()+", "+eventColumns[3]+" = "+e.getDate()+", "+eventColumns[4]+" = "+e.getWarningPeriod()+", "+eventColumns[5]+" = "+e.getLocation()+", "+eventColumns[6]+" = "+e.getBadWeather()+", "+eventColumns[7]+" = "+e.getDescription()+", "+eventColumns[8]+" = "+e.getInvited()+", "+eventColumns[9]+" = "+e.getAccepted();
        toReturn+="WHERE ID = "+e.getId();
        return toReturn;
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
        return "UPDATE USERS SET INBOX = "+inbox+" WHERE EMAIL = "+user.getEmail();
    }
    
    /**
     * Generates a query that inserts the entry of the provided user
     * 
     * @param user The user to insert - this should be the INSERTED version
     * @return A SQL command in the form of a string
     */
    public static String insertQueryEvent(Event e){
        return "selectQueryUser";
    }
    
    /**
     * Generates a query that inserts the entry of the provided user
     * 
     * @param user The user to insert - this should be the INSERTED version
     * @return A SQL command in the form of a string
     */
    public static String insertQueryUser(User user){
        return "selectQueryUser";
    }

}
