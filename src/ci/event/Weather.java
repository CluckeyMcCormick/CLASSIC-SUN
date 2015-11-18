/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;
import java.util.*;
import java.nio.*;
import java.lang.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author timothy.holcombe128
 */
public class Weather {
    
    public static final String url="http://api.wunderground.com/api/";
    public static final String apiKey="243807ae5f7d1215";//project specific
    public static final String tenDayForecast="/forecast10day/q/CA/";
    public static String location="";//spaces must b replaced with underscores
    public static final int day=2; //data file is stored in periods. 2 periods a day. Even is day, odd is night
    
    public static final String[] WEATHER_STRINGS = 
        { "Clear","Scattered Clouds","Partly Cloudy","Mostly Cloudy","Overcast",
        "Chance of Rain","Drizzle","Rain","Fog","Mist","Snow","Hail","Storm" };

    public static final int W_INDEX_CLEAR =     0;
    public static final int W_INDEX_SCATTERED = 1;
    public static final int W_INDEX_PARTLY =    2;
    public static final int W_INDEX_MOSTLY =    3;
    public static final int W_INDEX_OVERCAST =  4;
    public static final int W_INDEX_CHANCE =    5;
    public static final int W_INDEX_DRIZZLE =   6;
    public static final int W_INDEX_RAIN =      7;
    public static final int W_INDEX_FOG =       8;
    public static final int W_INDEX_MIST =      9;
    public static final int W_INDEX_SNOW =     10;
    public static final int W_INDEX_HAIL =     11;
    public static final int W_INDEX_STORM =    12;
    
    public static String[] weatherForecast = new String[10];
    
    public Weather(){
        this.location = "";
    }
    
    public Weather(String place){
        place.replace(' ','_');
        location = place;
    }
    
    public void makeForecast(){
        //major todo check if file of the day has already downloaded else program can only be run 500 times in a day, 10 times a minute 
        get10Forecast();
        process10Forecast();
    }
    
    public void get10Forecast(){
        try {
            System.out.println("Trying json grab!"); //access and download file that contains forecast data
            URL website = new URL(this.url+this.apiKey+this.tenDayForecast+this.location+".json");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(this.location+".json");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            fos.close();
        } catch (MalformedURLException ex) {
            System.err.println("MalformedURLException");
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFound");
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("IOException");
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done trying json grab!");
        System.out.println("Trying json process!");
    }
    
    public void process10Forecast(){
        try{
            Scanner scan = new Scanner(new File(this.location+".json")); //load grabbed file
            String temp="";
            if(scan.hasNextLine()){ //prime scan
                temp=scan.nextLine();
            }
            
            int dayCount=0; //loop incrementor
            
            while(scan.hasNextLine()){//loop reads for ""conditions"" line in the json file
                if(temp.indexOf("\"conditions\"")!=-1){ //16 is where desired portion of string begins, strlen-2 is where desired string ends
                    System.out.println(temp);
                    System.out.println(temp.substring(16,temp.length()-2)); //cut file to desired portion
                    weatherForecast[dayCount]=temp.substring(16,temp.length()-2); //store desired portion in public array
                    dayCount++;
                }
                temp=scan.nextLine();
            }
            
        }catch (FileNotFoundException ex) {
            System.err.println("FileNotFound");
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("IOException");
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int checkWeather(int day, String check){//day cannot exceed 9, if index is -1, check is negative/false, if index is -2 bad input
        int index=-1;
        if(day>-1&&day<10){
            if(weatherForecast[day].indexOf(check)>-1){
                index=weatherForecast[day].indexOf(check);
            }
        }else{
            index=-2;
        }
        return index;
    }
    
    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        location.replace(' ','_');
        Weather.location = location;
    }
    
}
