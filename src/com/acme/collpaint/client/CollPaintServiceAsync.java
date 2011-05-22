/**
 * 
 */
package com.acme.collpaint.client;

import com.acme.collpaint.client.comet.CometSessionsSupport.MessageWithAuthor;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>CollPaintServiceAsync</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:54:00 PM 
 *
 */
public interface CollPaintServiceAsync {

    public void updateLine(double startX, double startY,
                           double endX, double endY,
                           AsyncCallback<Void> callback);
    
    public void getUsername(AsyncCallback<String> callback);
    public void login(String username, AsyncCallback<Void> callback);
    public void logout(String username, AsyncCallback<Void> callback);
    public void sendToAll(MessageWithAuthor message, AsyncCallback<Void> callback);

}
