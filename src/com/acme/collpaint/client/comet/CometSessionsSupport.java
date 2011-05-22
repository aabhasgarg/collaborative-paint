/**
 * 
 */
package com.acme.collpaint.client.comet;

import java.io.Serializable;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>CometSessionsSupport</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 2:30:08 PM 
 *
 */
public interface CometSessionsSupport {
    
    public interface MessageWithAuthor extends Serializable {
        public void setAuthor(String username);
        public String getAuthor();
    }

    public String getUsername() throws CometSessionException;
    public void login(String username) throws CometSessionException;
    public void logout(String username) throws CometSessionException;
    public void sendToAll(MessageWithAuthor message) throws CometSessionException; 
    
}
