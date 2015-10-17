/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

/**
 *
 * @author nicolas.fredrickson1
 */
public class Invite {
    private User target;
    private int eventID;
    
    public Invite( User target, int eventID) {
        this.target = target;
        this.eventID = eventID;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }
    
    public User getTarget() {
        return this.target;
    }
    
    public int getEventID() {
        return this.eventID;
    }
    
}
