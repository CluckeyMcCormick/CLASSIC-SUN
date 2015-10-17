/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.event;

/**
 * This class represents a response from the server when we undertake
 * certain actions. 
 * 
 * @author nicolas.fredrickson1
 */
public class ServerResponse {
    
    private String message;
    private boolean success;
    
    public ServerResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public boolean getSuccess() {
        return this.success;
    }
}
