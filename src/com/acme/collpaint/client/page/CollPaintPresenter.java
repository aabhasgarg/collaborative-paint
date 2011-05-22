/**
 * 
 */
package com.acme.collpaint.client.page;

import com.acme.collpaint.client.CollPaintServiceAsync;
import com.acme.collpaint.client.Line;
import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.LineUpdate.State;
import com.acme.collpaint.client.comet.CollPaintCometListener.CollPaintEventsReceiver;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
        void updateLoginStatus(String username);
        HasClickHandlers getLogoutButton();
        
        void enableControls(boolean enable);
        boolean prepareCanvas();
        
        void setUpdatesSender(UpdatesSender sender);
        void drawUpdate(LineUpdate update);
    }
    
    public interface UpdatesSender {
        void lineFinished(Line line);
        void lineUpdated(Line line);
    }
    
    private final CollPaintServiceAsync service;
    private final Display view;
    
    public CollPaintPresenter(CollPaintServiceAsync service,
                              Display view) {
        this.service = service;
        this.view = view;
    }

    public void launch() {
        view.enableControls(false);        
        view.updateLoginStatus(null);
        //view.prepareCanvas();
        
        // TODO: restore session if it exists
        
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
        
        view.getLogoutButton().addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                service.logout(username, new EmptyCallback<Void>());
            }
            
        });
    }
    
    protected void whenLoggedIn(final String username) {
        Window.addCloseHandler(new CloseHandler<Window>() {
            @Override
            public void onClose(CloseEvent<Window> event) {
                service.logout(username, new EmptyCallback<Void>());
            }
        });
        
        view.updateLoginStatus(username);
        view.setUpdatesSender(new UpdatesSender() {

            @Override
            public void lineUpdated(Line line) {
                LineUpdate update = new LineUpdate(line, State.DRAWING);
                sendLineUpdate(update);
            }

            @Override
            public void lineFinished(Line line) {
                LineUpdate update = new LineUpdate(line, State.FINISHED);
                sendLineUpdate(update);
            }
            
        });
        if (view.prepareCanvas()) {
            view.enableControls(true);
        }
    }
    
    @Override
    public void onLineUpdated(LineUpdate update) {
        view.drawUpdate(update);
        /* Window.alert("Received line update " + update.info()); */
        Log.debug("Received line update: " + update.info());
    }

    protected void sendLineUpdate(LineUpdate data) {
        if (data == null || (data.getSource() == null)) {
            Log.warn("Tried to send null update");
            return;
        }
        Log.debug("Sending " + data.info());
        service.updateLine(data, new EmptyCallback<Void>());
    }
    
    protected abstract class HandlingCallback<T> implements AsyncCallback<T> {
        @Override public void onFailure(Throwable caught) { handle(caught); };
    }
    
    protected class EmptyCallback<T> extends HandlingCallback<T> {
        @Override public void onSuccess(T result) { };
    }    
    
    protected void handle(Throwable error) {
        Log.error(error.getMessage());
        Window.alert(error.getMessage());
    }    

}
