package ci.event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import org.postgresql.util.PSQLException;
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
    
    private Calendar lastCheck;
    
    public Controller(ConnectionManager conman) {
        this.conman = conman;
        this.con=conman.getConnection();
        this.lastCheck=Calendar.getInstance();
    }

    /**
     * Attempts to add the provided user to the database.
     *
     * @param u The user we are attempting to add to our database
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse addUser(User u) {
        //Query the database to see if this user exists already
        ServerResponse resp;
        Statement stmt;
        
        resp = this.checkForUser(u);
        //If the user isn't in here
        if( !resp.getSuccess() )
        {           
            try {
                
                u.setID(this.findFreeID(true));
                
                stmt = con.createStatement();
                stmt.executeUpdate(QueryGenerator.insertQueryUser(u));
            } catch( Exception e ) {
                String message;
                message = "Uncountered unknown error when adding user:\n" 
                    + e.getMessage() + "\nPlease try again.";

                resp = new ServerResponse(message, false);
                e.printStackTrace();
            }         
        }
        
        return resp;
    }
    
    /**
     * Attempts to update the provided user in the database.
     *
     * @param u The user we are attempting to update in our database
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse updateUser(User u)
    {
        ServerResponse resp;
        String query;
        //get a new id for this event from the "event" table
        try{
            Statement stmt = con.createStatement();
            query = QueryGenerator.updateQueryUserInbox(u);
            stmt.executeUpdate(query);
            //return a ServerResponse, with a message indicating the
            //event has been created, and the boolean true
            resp = new ServerResponse("User has been invited.", true);
        } catch ( Exception ex ) {          
            String message;
            message = "Uncountered unknown error when creating event:\n" 
                    + ex.getMessage() + "\nPlease try again.";
            resp = new ServerResponse(message, false);
            ex.printStackTrace();
        }
        
        return resp;
    }
    
    /**
     * Checks to see if the provided user already exists in the database
     *
     * @param u The user we are checking for in our database.
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse checkForUser(User u){
        //Query the database to see if this user exists already
        ServerResponse resp;
        Statement stmt;
        ResultSet rs;
        
        try{         
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryGenerator.selectQueryUser(u));       
            if(rs.next())
            {
                //An exception is thrown for returning an empty ResultSet
                //So, if we reach this line, we know the user is in the datatbase
                resp = new ServerResponse("User already exists.", true); 
            } 
            else
            {                
                resp = new ServerResponse("User doesn't exist.", false);
            }
        } catch (Exception e){
            String message;
            message = "Uncountered unknown error when checking for user:\n" 
                    + e.getMessage() + "\nPlease try again.";

            resp = new ServerResponse(message, false);
        }
        
        return resp;
    }

    /**
     * Attempts to add the provided event to the database.
     *
     * @param e The event we are attempting to add
     * @return The server's response, with a message and a success boolean
     */
    public ServerResponse addEvent(Event e) {
        ServerResponse resp;
        //get a new id for this event from the "event" table
        try{
            Statement stmt = con.createStatement();
            //the id should probably be the count from the eventTable
            e.setId(this.findFreeID(false));
            //set the event's (e) id using the setId method
            //Add the event to the "event" table
            stmt.executeUpdate(QueryGenerator.insertQueryEvent(e));
            //return a ServerResponse, with a message indicating the
            //event has been created, and the boolean true
            resp = new ServerResponse("Event added to database.", true);
        } catch ( Exception ex ) {          
            String message;
            message = "Uncountered unknown error when creating event:\n" 
                    + ex.getMessage() + "\nPlease try again.";
            resp = new ServerResponse(message, false);
            ex.printStackTrace();
        }
        
        return resp;
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
            System.err.println( "Exception occured in Controller.removeEvent" );
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
        ServerResponse resp;
        //get a new id for this event from the "event" table
        try{
            Statement stmt = con.createStatement();
            //Add the event to the "event" table
            stmt.executeUpdate(QueryGenerator.updateQueryEvent(e));
            //return a ServerResponse, with a message indicating the
            //event has been created, and the boolean true
            resp = new ServerResponse("Event has been updated.", true);
        } catch ( Exception ex ) {          
            String message;
            message = "Uncountered unknown error when creating event:\n" 
                    + ex.getMessage() + "\nPlease try again.";
            resp = new ServerResponse(message, false);
            ex.printStackTrace();
        }
        
        return resp;
    }
    
    private int findFreeID(boolean userTable) throws SQLException{
        //Query the database to see if this user exists already
        Statement stmt;
        ResultSet rs;
        
        int freeID;
        
            ArrayList<Integer> IDS;
            stmt = con.createStatement();
            if(userTable)
            {
                rs = stmt.executeQuery(QueryGenerator.idQueryUser());
            }
            else
            {
                rs = stmt.executeQuery(QueryGenerator.idQueryEvent());
            }
              
            IDS = new ArrayList<>();
            
            while(rs.next())
            {
                IDS.add(rs.getInt(1));
            }
            
            for(freeID = 0; IDS.contains(freeID); freeID++);
        
        return freeID;
    }
    
    public boolean sendInvites(Event e){ //use get Event
        boolean allSent=false;
        
        try{
        Mail.Send(e.getInvited(), Factory.createTitle(e), Factory.createInviteMessage(e));
        allSent=true;
        }catch(Exception ex){
            allSent=false;
            System.out.println("An email was bad.");
        }
        return allSent;
    }
    
    public ServerResponse checkWarningWeather(Event e){//checks warning period, ServerResponse true means things are good, false means intervention occured
        Calendar temp=Calendar.getInstance();
        temp.add(Calendar.DAY_OF_MONTH,e.getWarningPeriod());
        
        if(temp.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR) && temp.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
            boolean weatherIsGood=false;
            for(String weather : e.getGoodWeather()){
                if(Weather.weatherForecast[e.getWarningPeriod()-1].equals(weather)){
                    weatherIsGood=true;
                }
            }

            if(!weatherIsGood){
                try{
                Mail.Send(e.getCreator(), Factory.createWarningTitle(e), Factory.createWarningMessage(e));
                }catch(Exception ex){
                    System.out.println("An email was bad.");
                }
                return new ServerResponse(e.getName()+" checked, weather is bad, warning emails sent.", false);
            }else{
                return new ServerResponse(e.getName()+" checked, weather is good.", true);
            }
            
        }else{
            return new ServerResponse("Not time to check "+e.getName(), true);
        }
    }
    
    public ServerResponse dailyWeatherCheck(){//checks all weathers for their warning time and bad weathers, returns true if it updated, returns false if waiting for next day to update.
        Calendar temp=lastCheck.getInstance();
        temp.add(Calendar.DAY_OF_MONTH, 1);
        if(temp.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR) && temp.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
            lastCheck=temp;
            
            ServerResponse resp;
            String query;
            ResultSet rs;
            //get a new id for this event from the "event" table
            try{
                Statement stmt = con.createStatement();
                query = QueryGenerator.allQueryEvent();
                rs=stmt.executeQuery(query);
                
                ArrayList<Event> events=Factory.createEvents(rs);
                
                for(Event e : events){
                   checkWarningWeather(e);
                }
                
                
            } catch ( Exception ex ) {          
                    String message;
                    message = "Uncountered unknown error when querying all events:\n" 
                            + ex.getMessage() + "\nPlease try again.";
                    resp = new ServerResponse(message, false);
                    ex.printStackTrace();
            }
            return new ServerResponse("Daily check completed", true);
        }else{
            return new ServerResponse("", false);
        }
        
    }
}
