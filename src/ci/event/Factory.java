/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author cluckeymccormick
 */
public class Factory {

    public static ArrayList<Event> createEvents(ResultSet rs) {
        //https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
        ArrayList<Event> events=new ArrayList<Event>();
        try{
            while (rs.next()) {
                events.add(Factory.createEvent(rs));
            }
        }catch ( Exception e ) {
            System.err.println( "Exception occured in Factory.createEvents" );
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return events;
    }
    
    public static Event createEvent(ResultSet rs) throws SQLException{ //only to be used in createEvents()
        Event e = new Event(
            rs.getString(2).replaceAll("''","'"), //Event Name
            rs.getString(3), //Event Creator
            Factory.stringToCalendar(rs.getString(4)), //Date
            rs.getString(6), //Location
            rs.getInt(5), //Warning period
            Factory.stringToStringList(rs.getString(7)), //BadWeather
            rs.getString(8).replaceAll("''", "'"), //Description
            Factory.stringToStringList(rs.getString(9)), //invited users
            Factory.stringToStringList(rs.getString(10)) //accepted 
        );
        
        e.setId(rs.getInt(1));
        
        return e;
    }

    public static ArrayList<User> createUsers(ResultSet rs) {
        ArrayList<User> users=new ArrayList<User>();
        try{
            User tempUser;
            while (rs.next()) {
                tempUser = new User(rs.getString(QueryGenerator.userColumns[1]));
                tempUser.setID(rs.getInt(0));
                tempUser.setInvites(Factory.stringToIntegerList(rs.getString(QueryGenerator.userColumns[2])));
                users.add(tempUser);
                
            }
        }catch ( Exception e ) {
            System.err.println( "Exception occured in Factory.createEvents" );
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        System.out.println("In Factory's createEvents method - IMPLEMENT ME!");
        return null;
    }

    public static String IntegerListToString(ArrayList<Integer> list) {
        StringBuilder builder;
        Integer current;

        Iterator<Integer> iter;

        iter = list.iterator();
        builder = new StringBuilder();

        while (iter.hasNext()) {
            current = iter.next();

            builder.append(current);

            if (iter.hasNext()) {
                builder.append(';');
            }
        }

        return builder.toString();
    }

    public static String StringListToString(ArrayList<String> list) {
        StringBuilder builder;
        String current;

        Iterator<String> iter;

        iter = list.iterator();
        builder = new StringBuilder();

        while (iter.hasNext()) {
            current = iter.next();

            builder.append(current);

            if (iter.hasNext()) {
                builder.append(';');
            }
        }

        return builder.toString();
    }

    public static ArrayList<Integer> stringToIntegerList(String in) {
        ArrayList<Integer> retList;
        Scanner scantron;

        retList = new ArrayList<>();
        scantron = new Scanner(in);
        scantron.useDelimiter(";");

        while (scantron.hasNext()) {
            retList.add(scantron.nextInt());
        }

        return retList;
    }
    
    public static ArrayList<String> stringToStringList(String in) {
        ArrayList<String> retList;
        Scanner scantron;

        retList = new ArrayList<>();
        scantron = new Scanner(in);
        scantron.useDelimiter(";");

        while (scantron.hasNext()) {
            retList.add(scantron.next());
        }

        return retList;
    }
    
    public static Calendar stringToCalendar(String in){
        Calendar cal;
        Scanner read;
        
        cal = new GregorianCalendar();
        read = new Scanner(in);
        read.useDelimiter("/");
        
        cal.set(Calendar.MONTH, read.nextInt() - 1);
        cal.set(Calendar.DAY_OF_MONTH, read.nextInt());
        cal.set(Calendar.YEAR, read.nextInt());
        
        return cal;
    }
    
    public static String calendarToString(Calendar cal){
        StringBuilder string;
        
        string = new StringBuilder();
        string.append(cal.get(Calendar.MONTH) + 1);
        string.append('/');
        string.append(cal.get(Calendar.DAY_OF_MONTH));
        string.append('/');
        string.append(cal.get(Calendar.YEAR));
        
        return string.toString();
    }
    
    public static String createInviteMessage(Event e){
        return e.getCreator()+" has invited you to "+e.getName()+" on "+calendarToString(e.getDate())+". Head to the CI.Events SUN app now to rsvp. \n"+e.getDescription();
    }
    
    public static String createWarningMessage(Event e){
        return e.getCreator()+", your event: "+e.getName()+" on "+calendarToString(e.getDate())+" may have conflicting weather: "+Weather.weatherForecast[e.getWarningPeriod()]+" Head to the CI.Events SUN app now to reschedule if this is a conflict.";
    }
    
    public static String createTitle(Event e){
        return e.getCreator()+" has invited you to an event!";
    }
    
    public static String createWarningTitle(Event e){
        return "Your event: "+e.getName()+" may have bad weather!";
    }
}
