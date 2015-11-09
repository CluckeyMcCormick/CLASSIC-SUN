package ci.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Class used to store, update, and remove information in the database.
 * 
 * @author cluckeymccormick
 */
public class Controller {
   
    //This is the object we use to get a conenction to the database
    private ConnectionManager conman;
    private Connection con;
    //For info on how to use this, check out this:
    // <editor-fold defaultstate="collapsed" desc="URL Link">
    
    //http://stackoverflow.com/questions/6567839/using-a-singleton-class-for-database-connection
    //The useful part I'm talking about is the answer's second part - specifically,
    //the part refering to how to use a connection object you get it
    
    // </editor-fold>
    
    public Controller(ConnectionManager conman) {
        this.conman = conman;
        this.con=conman.getConnection();
    }

    /**
     * Attempts to add the provided user to the database.
     *
     * @param u The user we are attempting to add to our database
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse addUser(User u) {
        //Query the database to see if this user exists already
        try{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QueryGenerator.selectQueryUser(u));
        if(rs.next()){ //A user has been returned and it has a previous existence
            ServerResponse failedInsert= new ServerResponse("User already exists.", false);
            return failedInsert;
            
        }else{//No user returned, no existing user with that name/email. add user to database
            stmt.executeQuery(QueryGenerator.insertQueryUser(u));
            ServerResponse successfulInsert= new ServerResponse("User added to database.", true);
            return successfulInsert;
        }     
        
        } catch ( Exception e ) {
            System.err.println( "Exception occured in Controller.addUser" );
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       }
        
            return null;
    }
    
    /**
     * Attempts to update the provided user in the database.
     *
     * @param u The user we are attempting to update in our database
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse updateUser(User u)
    {
        return null;
    }
    
    /**
     * Checks to see if the provided user already exists in the database
     *
     * @param u The user we are checking for in our database.
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse checkForUser(User u){
        try{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QueryGenerator.selectQueryUser(u));
        if(rs.next()){ //A user has been returned and it has a previous existence
            ServerResponse affirmative= new ServerResponse("User exists.", true);
            return affirmative;
            
        }else{//No user returned, no existing user with that name/email. add user to database
            
            ServerResponse negative= new ServerResponse("User does not exist in database.", false);
            return negative;
        }
        
        
        } catch ( Exception e ) {
            System.err.println( "Exception occured in Controller.checkUser" );
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       }
        

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
        try{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(QueryGenerator.sizeQueryEvent());
        //the id should probably be the count from the eventTable
        int id=0;
        if(rs.next()){
            id=rs.getInt("COUNT(*)");//sets id = to number of rows in Events table, 
        }
        e.setId(id);
        //set the event's (e) id using the setId method
        //Add the event to the "event" table
        stmt.executeQuery(QueryGenerator.insertQueryEvent(e));
        //return a ServerResponse, with a message indicating the
        //event has been created, and the boolean true
        ServerResponse successfulInsert= new ServerResponse("Event added to database.", true);
        return successfulInsert;
        } catch ( Exception ex ) {
            System.err.println( "Exception occured in Controller.addEvent" );
            System.err.println( ex.getClass().getName()+": "+ ex.getMessage() );
        }
        
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
        try{
        //If we didn't do it
            //return a ServerResponse, with a message indicating the event
            //doesn't exist, and the boolean false
        //else
            //return a ServerResponse, with a message indicating the
            //event was removed, and the boolean true
        } catch ( Exception ex ) {
            System.err.println( "Exception occured in Controller.addEvent" );
            System.err.println( ex.getClass().getName()+": "+ ex.getMessage() );
        }
        
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
}
