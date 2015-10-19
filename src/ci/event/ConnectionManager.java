/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.sql.Connection;

/**
 * Issues Connection objects for our database
 * 
 * @author cluckeymccormick
 */
public class ConnectionManager {
    //So I (Nick) am not sure what to do here
    //whoever is handling the database will have to handle this
    
    //However, I think the link below may be helpful:
    //http://stackoverflow.com/questions/6567839/using-a-singleton-class-for-database-connection
    
    //The answer's suggestion is pretty helpful.
    
    public ConnectionManager(){
        //idk what goes here
    }
    
    public Connection getConnection(){
        //Should return a new Connection as per the above link
        //But maybe not - I'm not implementing this!
        return null;
    }
}
