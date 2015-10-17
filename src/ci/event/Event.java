package ci.event;

import java.util.ArrayList;
import java.util.Calendar;

public class Event {

    private int id;
    private String name;
    private User creator;
    private Calendar date;
    private String location;
    private int warningPeriod;
    private Weather badWeather;
    private String description;
    private ArrayList<User> invited;
    private ArrayList<User> accepted;

    public Event(String name, User create, Calendar date, String loc, int warn, Weather bad, String descri, ArrayList<User> invi, ArrayList<User> accept ) {
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
    
    public Event(String name, User create, Calendar date, String loc, int warn, Weather bad, String descri) {
        this.id = -1;
        this.name = name;
        this.creator = create;
        this.date = date;
        this.location = loc;
        this.badWeather = bad;
        this.description = descri;
        this.invited = new ArrayList<User>();
        this.accepted = new ArrayList<User>();
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
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
    
    public void setBadWeather(Weather badWeather) {
        this.badWeather = badWeather;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setInvited(ArrayList<User> invited) {
        this.invited = invited;
    }

    public void setAccepted(ArrayList<User> accepted) {
        this.accepted = accepted;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public User getCreator() {
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
    
    public Weather getBadWeather() {
        return badWeather;
    }
    
    public String getDescription() {
        return description;
    }
    
    public ArrayList<User> getInvited() {
        return invited;
    }
    
    public ArrayList<User> getAccepted() {
        return accepted;
    }
}
