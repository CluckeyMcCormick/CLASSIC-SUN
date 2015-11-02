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

        System.out.println("In Factory's createEvents method - IMPLEMENT ME!");
        return null;
    }

    public static ArrayList<User> createUsers(ResultSet rs) {

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
}
