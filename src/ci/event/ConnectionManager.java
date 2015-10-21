/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Issues Connection objects for our database
 *
 * @author cluckeymccormick&TimothyHolcombe
 */
public class ConnectionManager {
    //So I (Nick) am not sure what to do here
    //whoever is handling the database will have to handle this

    //However, I think the link below may be helpful:
    //http://stackoverflow.com/questions/6567839/using-a-singleton-class-for-database-connection
    //The answer's suggestion is pretty helpful.
    
    private static Connection connect; //Heroku postgres database still-beyond-3432 :: database
    
    public ConnectionManager() {
        //idk what goes here
        try {
            this.connect = makeConnection();

        } catch (SQLException e) {
            System.err.println("Connection get failed, sqlexception.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.err.println("Connection get failed, URISyntax.");
            e.printStackTrace();
        }
        //catch(ClassNotFoundException e){
        //   System.err.println("Connection get failed, URISyntax.");
        //   e.printStackTrace();
        //}
    }

    private Connection makeConnection() throws URISyntaxException, SQLException {
        //Link to current using database Heroku postgres database still-beyond-3432 :: database, changeable
        String username = "cqdweicgxchvez";
        String host = "ec2-54-204-7-145.compute-1.amazonaws.com";
        int port = 5432;
        String password="9AVseAwDl4RzCHDs3hS_-fz6Yl";
        String path = "/demgp20hpcaetd";
        
        
        String dbUrl = "jdbc:postgresql://" + host + ':' + port + path + "?user=" + username + "&password="+password+"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        return DriverManager.getConnection(dbUrl);
    }
    
    public Connection makeConnection(String username,String host,int port,String password,String path) throws URISyntaxException, SQLException {
        //Dynamic link to  database
        String dbUrl = "jdbc:postgresql://" + host + ':' + port + path + "?user=" + username + "&password="+password+"&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        return DriverManager.getConnection(dbUrl);
    }
    
    public void setConnection(Connection con){
        this.connect=con;
    }
    
    public Connection getConnection(){
        return this.connect;
    }
    
}
