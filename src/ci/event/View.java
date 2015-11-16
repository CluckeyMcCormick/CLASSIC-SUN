/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.sql.ResultSet;
import java.sql.Statement;
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
    public View(ConnectionManager conman) {
        this.conman = conman;
    }

    /**
     * Retrieves the list of events the provided user has been invited to
     *
     * @param u The user whose attendance list we'll be retrieving
     * @return A list of the events this user is attending
     */
    public ArrayList<Event> getEventsInvited(User u) {
        ArrayList<Event> toReturn;
        Statement stmt;
        ResultSet rs;
        Event e;

        toReturn = new ArrayList<>();

        for (Integer i : u.getInvites()) {
            try {
                stmt = conman.getConnection().createStatement();
                rs = stmt.executeQuery(QueryGenerator.selectQueryEvent(i));

                while (rs.next()) {
                    e = new Event(
                            rs.getString(2), //Event Name
                            rs.getString(3), //Event Creator
                            Factory.stringToCalendar(rs.getString(4)), //Date
                            rs.getString(6), //Location
                            rs.getInt(5), //Warning period
                            Factory.stringToStringList(rs.getString(7)), //BadWeather
                            rs.getString(8), //Description
                            Factory.stringToStringList(rs.getString(9)), //invited users
                            Factory.stringToStringList(rs.getString(10))
                    );

                    e.setId(rs.getInt(1));

                    toReturn.add(e);
                }

            } catch (Exception ex) {
                System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            }
        }

        return toReturn;
    }

    /**
     * Retrieves the list of events the provided user has created
     *
     * @param u The user whose created event list we'll be retrieving
     * @return A list of the events this user created
     */
    public ArrayList<Event> getEventsCreated(User u) {
        ArrayList<Event> toReturn;
        Statement stmt;
        ResultSet rs;
        Event e;

        toReturn = new ArrayList<>();

        try {
            stmt = conman.getConnection().createStatement();
            rs = stmt.executeQuery(QueryGenerator.selectQueryEvent(u));

            while (rs.next()) {
                e = new Event(
                        rs.getString(2), //Event Name
                        rs.getString(3), //Event Creator
                        Factory.stringToCalendar(rs.getString(4)), //Date
                        rs.getString(6), //Location
                        rs.getInt(5), //Warning period
                        Factory.stringToStringList(rs.getString(7)), //BadWeather
                        rs.getString(8), //Description
                        Factory.stringToStringList(rs.getString(9)), //invited users
                        Factory.stringToStringList(rs.getString(10)) //accepted users
                );

                e.setId(rs.getInt(1));

                toReturn.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return toReturn;
    }

    /**
     * Retrieves a user object of the provided username
     *
     * @param username The username of the user object we want to retrieve
     * @return The user object we've retrieved
     */
    public User getUser(String email) {
        Statement stmt;
        ResultSet rs;
        User u;
        
        try {
            stmt = conman.getConnection().createStatement();
            rs = stmt.executeQuery(QueryGenerator.selectQueryUser(new User(email)));
            
            rs.next();
            u = new User( 
               Factory.stringToIntegerList(rs.getString(3)),
               rs.getString(2) 
            );
            
            u.setID(rs.getInt(1));
            
        } catch (Exception ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            u = null;
        }
        
        return u;
    }
}
