package ci.event;

import java.util.ArrayList;
import java.util.Calendar;

public class Event {

    private int id;
    private String name;
    private String creator;
    private Calendar date;
    private String location;
    private int warningPeriod;
    private ArrayList<String> badWeather;
    private String description;
    private ArrayList<String> invited;
    private ArrayList<String> accepted;

    public Event(String name, String create, Calendar date, String loc, int warn, ArrayList<String> bad, String descri, ArrayList<String> invi, ArrayList<String> accept ) {
        this.id = -1;
        this.name = name;
        this.creator = create;
        this.date = date;
        this.location = loc;
        this.badWeather = bad;
        this.description = descri;
        this.invited = invi;
        this.accepted = accept;
    }
    
    public Event(String name, String create, Calendar date, String loc, int warn, ArrayList<String> bad, String descri) {
        this.id = -1;
        this.name = name;
        this.creator = create;
        this.date = date;
        this.location = loc;
        this.badWeather = bad;
        this.description = descri;
        this.invited = new ArrayList<String>();
        this.accepted = new ArrayList<String>();
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public void setWarningPeriod(int warningPeriod) {
        this.warningPeriod = warningPeriod;
    }
    
    public void setBadWeather(ArrayList<String> badWeather) {
        this.badWeather = badWeather;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setInvited(ArrayList<String> invited) {
        this.invited = invited;
    }

    public void setAccepted(ArrayList<String> accepted) {
        this.accepted = accepted;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public Calendar getDate() {
        return date;
    }
    
    public String getLocation() {
        return location;
    }

    public int getWarningPeriod() {
        return warningPeriod;
    }
    
    public ArrayList<String> getBadWeather() {
        return badWeather;
    }
    
    public String getDescription() {
        return description;
    }
    
    public ArrayList<String> getInvited() {
        return invited;
    }
    
    public ArrayList<String> getAccepted() {
        return accepted;
    }
}
