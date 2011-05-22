/**
 * 
 */
package com.acme.collpaint.client.comet;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.comet</dd>
 * </dl>
 *
 * <code>CometSessionException</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 2:32:34 PM 
 *
 */
public class CometSessionException extends Exception {

    private String username;
    
    public CometSessionException() { this(""); }
    
    public CometSessionException(String message) {
        this(null, message);
    }
    
    public CometSessionException(String username, String message) {
        super(message);
        this.username = username;
    }    
    
    public String getUsername() { return username; }
    
    @Override
    public String getMessage() { return "[" + username + "] " + super.getMessage(); }
    
}
