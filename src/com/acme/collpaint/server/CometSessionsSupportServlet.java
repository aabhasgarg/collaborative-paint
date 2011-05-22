/**
 * 
 */
package com.acme.collpaint.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;

import com.acme.collpaint.client.comet.CometSessionException;
import com.acme.collpaint.client.comet.CometSessionsSupport;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.server</dd>
 * </dl>
 *
 * <code>CometSessionsSupportServlet</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 2:28:54 PM 
 *
 */
public abstract class CometSessionsSupportServlet extends RemoteServiceServlet implements CometSessionsSupport {

    private ConcurrentMap<String, CometSession> users = new ConcurrentHashMap<String, CometSession>();

    @Override
    public String getUsername() throws CometSessionException {
        HttpSession httpSession = getSession();
        if (httpSession == null) return null;
          
        return (String) httpSession.getAttribute("username");
    }

    @Override
    public void login(String username) throws CometSessionException {
        HttpSession httpSession = getSessionOrCreate();
        CometSession cometSession = getCometSessionOrCreate(httpSession);
        
        httpSession.setAttribute("username", username);
        
        if (users.putIfAbsent(username, cometSession) != null) {
            // some one else has already logged in with this user name 
            httpSession.invalidate();
            throw new CometSessionException(username, 
                    "User: " + username + " already logged in");
        }
        
        Log.info(username + " is logged in");
    }


    @Override
    public void logout(String username) throws CometSessionException {
        HttpSession httpSession = getSession();
        if (httpSession == null) {
            throw new CometSessionException(username, 
                    "User: " + username + " is not logged in: no http session");
        }
        
        CometSession cometSession = getCometSession(httpSession);
        if (cometSession == null) {
            throw new CometSessionException(username,
                    "User: " + username + " is not logged in: no comet session");
        }
        
        if (!username.equals(httpSession.getAttribute("username"))) {
            throw new CometSessionException(username, 
                    "User: " + username + " is not logged in on this session");
        }
        
        users.remove(username, cometSession);
        httpSession.invalidate();
        
        Log.info(username + " is logged out");        
    }
    
    @Override
    public void sendToAll(MessageWithAuthor message) throws CometSessionException {
        HttpSession httpSession = getSession();
        if (httpSession == null) {
            throw new CometSessionException("not logged in: no http session");
        }
        
        String username = (String) httpSession.getAttribute("username");
        if (username == null) {
             throw new CometSessionException("not logged in: no http session username");
        }
        
        message.setAuthor(username);
        
        for (CometSession session : users.values()) {
            session.enqueue(message);
        }
        
        Log.debug("message from " + username + " has been sent to all");        
    }
    
    protected HttpSession getSession() {
        return getThreadLocalRequest().getSession(false);
    }    
    
    protected HttpSession getSessionOrCreate() {
        return getThreadLocalRequest().getSession();
    }
    
    /* protected CometSession getCometSession() {
        return CometServlet.getCometSession(getSession(), false);
    } */
    
    protected CometSession getCometSession(HttpSession base) {
        return CometServlet.getCometSession(base, false);
    }
    
    /* protected CometSession getCometSessionOrCreate() {
        return CometServlet.getCometSession(getSessionOrCreate());
    } */
    
    protected CometSession getCometSessionOrCreate(HttpSession base) {
        return CometServlet.getCometSession(base);
    }    
    
}
