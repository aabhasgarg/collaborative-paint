/**
 * 
 */
package com.acme.collpaint.client.page;

import com.acme.collpaint.client.CollPaintServiceAsync;
import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.comet.CollPaintCometListener.CollPaintEventsReceiver;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.page</dd>
 * </dl>
 *
 * <code>CollPaintPresenter</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 6:34:46 PM 
 *
 */
public class CollPaintPresenter implements CollPaintEventsReceiver {
    
    public interface Display {
        
    }
    
    private final CollPaintServiceAsync service;
    private final Display view;
    
    public CollPaintPresenter(CollPaintServiceAsync service,
                              Display view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void onLineUpdated(LineUpdate update) {
        Window.alert("Received line update " + update.info());
        Log.debug("Received line update: " + update.info());        
    }

    public void launch() {
        final String username = Window.prompt("Login", "Enter Username");
        if (username == null || username.isEmpty()) {
            Window.alert("You _must_ log in");
            return;
        }
        
        service.login(username, new HandlingCallback<Void>() {          
            @Override public void onSuccess(Void result) {
                whenLoggedIn(username);
            }           
        });
    }
    
    protected void whenLoggedIn(String username) {
        Button sendButton = new Button("Send line update",
                new ClickHandler() {                    
                    @Override
                    public void onClick(ClickEvent event) {
                        tryToUpdateLine(0.4, 0.3, 0.5, 0.6);
                    }
                });
        RootPanel.get().add(sendButton);        
    }
    
    protected void tryToUpdateLine(double startX, double startY, double endX, double endY) {
        service.updateLine(startX, startY, endX, endY, 
                new HandlingCallback<Void>() {
                    @Override public void onSuccess(Void result) { }                    
                });     
    }
    
    protected abstract class HandlingCallback<T> implements AsyncCallback<T> {
        @Override public void onFailure(Throwable caught) { handle(caught); };
    }
    
    protected void handle(Throwable error) {
        Log.error(error.getMessage());
        Window.alert(error.getMessage());
    }    

}
