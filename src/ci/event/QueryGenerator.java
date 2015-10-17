/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

/**
 * This class generates database queries for us
 * @author nicolas.fredrickson1
 */
public class QueryGenerator {
    
    private static final String EVENT_TABLE_NAME = "events";
    private static final String USER_TABLE_NAME = "users";   
    
    /**
     * Generates a query that gets the number of entries in the user table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryUser(){
        return "sizeQueryUser";
    }
    
    /**
     * Generates a query that gets the number of entries in the event table
     * 
     * @return A SQL command in the form of a string
     */
    public static String sizeQueryEvent(){
        return "sizeQueryEvent";
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching id - so, basically one entry.
     * 
     * @param eventId The id for the event we are trying to retrieve
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(int eventId){
        return "selectQueryEvent - int input";
    }
    
    /**
     * Generates a query that selects all entries in the event table 
     * with the matching creator.
     * 
     * @param creator The creator-user whose events we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryEvent(User creator){
        return "selectQueryEvent - User input";
    }
    
    /**
     * Generates a query that retrieves the entry of the provided user
     * 
     * @param user The user whose info we're retrieving
     * @return A SQL command in the form of a string
     */
    public static String selectQueryUser(User user){
        return "selectQueryUser";
    }
    
    /**
     * Generates a query that updates the entry of the provided event
     * 
     * @param e The event to update - this should be the UPDATED version
     * @return A SQL command in the form of a string
     */
    public static String updateQueryEvent(Event e){
        return "selectQueryUser";
    }
    
    /**
     * Generates a query that updates the entry of the provided user
     * 
     * @param user The user to update - this should be the UPDATED version
     * @return A SQL command in the form of a string
     */
    public static String updateQueryUser(User user){
        return "selectQueryUser";
    }
}
