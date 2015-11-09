package ci.event;

import java.util.ArrayList;

public class User{

    private ArrayList<Integer> invites;
    private String email;
    private static int idCount=0;
    private int ID;

    public User(ArrayList<Integer> invites, String email) {
        this.invites = invites;
        this.email = email;
        this.ID=idCount;
        this.idCount++;
    }

    public User(String email) {
        this.invites = new ArrayList<>();
        this.email = email;
        this.ID=idCount;
        this.idCount++;
    }

    public void addInvite(int inv)
    {
        this.invites.add(inv);
    }
    
    public void removeInvite(int inv)
    {
        this.invites.remove(inv);
    }
    
    public boolean checkForInvite(int inv)
    {
        return this.invites.contains(inv);
    }
    
    public void setInvites(ArrayList<Integer> invites) {
        this.invites = invites;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setID(int id) {
        this.ID = id;
    }
    
    public ArrayList<Integer> getInvites() {
        return invites;
    }
    
    public String getEmail(){
        return this.email;
    }   
    public int getID(){
        return this.ID;
    }  
}