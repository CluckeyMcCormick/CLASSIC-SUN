package ci.event;

import java.sql.Connection;
/**
 * Class used to store, update, and remove information in the database.
 * 
 * @author cluckeymccormick
 */
public class Controller {
   
    public Controller() {
        //empty constructor
    }

    /**
     * Attempts to add the provided user to the database.
     *
     * @param u The user we are attempting to add to our database
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse addUser(User u) {
        //Query the database to see if this user exists already

        //If they do exist
            //return a ServerResponse, with a message indicating the
            //username already exists, and the boolean false
        //If they don't exist
            //Add the user to the "user" table
            //return a ServerResponse, with a message indicating the
            //username has been created, and the boolean true
        System.out.println("In Controller's addUser method - IMPLEMENT ME!");
        return null;
    }

    /**
     * Attempts to add the provided event to the database.
     *
     * @param e The event we are attempting to add
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse addEvent(Event e) {
        //get a new id for this event from the "event" table
        //the id should probably be the count from the eventTable
        
        //set the event's (e) id using the setId method
        //Add the event to the "event" table
        
        //return a ServerResponse, with a message indicating the
        //event has been created, and the boolean true
        System.out.println("In Controller's addEvent method - IMPLEMENT ME!");
        return null;
    }
    
    /**
     * Attempts to remove the provided event from the database.
     *
     * @param e The event we are attempting to remove
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse removeEvent(Event e) {
        //Attempt to remove e from the event table
        
        //If we didn't do it
            //return a ServerResponse, with a message indicating the event
            //doesn't exist, and the boolean false
        //else
            //return a ServerResponse, with a message indicating the
            //event was removed, and the boolean true
        System.out.println("In Controller's addEvent method - IMPLEMENT ME!");
        return null;
    }
    
    /**
     * Attempts to update the provided event in the database
     *
     * @param e The event we are attempting to update
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse updateEvent(Event e) {

        //If we didn't do it
            //return a ServerResponse, with a message indicating the event
            //could not be updated, and the boolean false
        //If the event already exists
            //return a ServerResponse, with a message indicating the
            //event was updated, and the boolean true
        System.out.println("In Controller's updateEvent method - IMPLEMENT ME!");
        return null;
    }

    /**
     * Attempts to add the provided invite to the database.
     *
     * @param i The invite we are attempting to add
     * @return The server's response, with a response and a success boolean
     */
    public ServerResponse addInvite(Invite i) {
        //Check if the invitee exists

        //If they do exist
            //Get a list of their invites - I suggest the View.getInvites method
        
            //If they don't already have this invite
                //remove their invites from the table
                //add this invite to the invite list
                //Add the invite list to the user's invite column
                    //return a ServerResponse, with a message indicating the
                    //invite was sent, and the boolean true
            //else
                //return a ServerResponse, with a message indicating the
                //user has already been invited, and the boolean false
        //else
            //return a ServerResponse, with a message indicating the
            //target user doesn't exist, and the boolean false
        System.out.println("In Controller's addInvite method - IMPLEMENT ME!");
        return null;
    }
    
    public ServerResponse removeInvite(Invite i) {

        //If it does exist
            //Attempt to remove i from said table
            //If we succeed
                //return a ServerResponse, with a message indicating the
                //the removal was successful, and the boolean true
            //else
                //return a ServerResponse, with a message indicating the
                //invite didn't exist, and the boolean false
        //else
            //return a ServerResponse, with a message indicating the
            //target user doesn't have invites, and the boolean false
        System.out.println("In Controller's addInvite method - IMPLEMENT ME!");
        return null;
    }
    

}
