package ci.event;

import java.util.ArrayList;

public class User{

    private ArrayList<Integer> invites;
    private String email;

    public User(ArrayList<Integer> invites, String email) {
        this.invites = invites;
        this.email = email;
    }

    public User(String email) {
        this.invites = new ArrayList<>();
        this.email = email;
    }

    public void setInvites(ArrayList<Integer> invites) {
        this.invites = invites;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<Integer> getInvites() {
        return invites;
    }
    
    public String getEmail(){
        return this.email;
    }   
}