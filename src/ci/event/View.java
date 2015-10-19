/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.util.ArrayList;

/**
 * Class used to retrieve information from/about the database
 * 
 * @author cluckeymccormick
 */
public class View {
    
    //This is the object we use to get a conenction to the database
    private ConnectionManager conman;
    //For info on how to use this, check out this:
    // <editor-fold defaultstate="collapsed" desc="URL Link">
    
    //http://stackoverflow.com/questions/6567839/using-a-singleton-class-for-database-connection
    //The useful part I'm talking about is the answer's second part - specifically,
    //the part refering to how to use a connection object once you get it
    
    // </editor-fold>
    
    public View(ConnectionManager conman){
        this.conman = conman;
    }
    
    /**
     * Retrieves the list of events the provided user has been invited to
     * 
     * @param u The user whose attendance list we'll be retrieving
     * @return A list of the events this user is attending
     */
    public ArrayList<Event> getEventsInvited(User u)
    {
        return null;
    }
    
    /**
     * Retrieves the list of events the provided user has created
     * 
     * @param u The user whose created event list we'll be retrieving
     * @return A list of the events this user created
     */
    public ArrayList<Event> getEventsCreated(User u)
    {
        return null;
    }
    
    /**
     * Retrieves a user object of the provided username
     * 
     * @param username The username of the user object we want to retrieve
     * @return The user object we've retrieved
     */
    public User getUser(String username)
    {
        return null;
    }
}
