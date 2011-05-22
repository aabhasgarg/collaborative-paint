package com.acme.collpaint.client;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;

import com.acme.collpaint.client.comet.CollPaintCometListener;
import com.acme.collpaint.client.comet.CollPaintCometSerializer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CollaborativePaint implements EntryPoint {
    
    public static final String COMET_PATH = GWT.getModuleBaseURL() + "comet";
    
    private final CollPaintServiceAsync service = GWT.create(CollPaintService.class);
    
	public void onModuleLoad() {
	    CometSerializer serializer = GWT.create(CollPaintCometSerializer.class);
	    CometClient client = new CometClient(COMET_PATH, serializer,
	                                         new CollPaintCometListener());
	    client.start();	    	    
	    
	    final String username = Window.prompt("Login", "Enter Username");
	    if (username == null || username.isEmpty()) {
	        Window.alert("You _must_ log in");
	        return;
	    }
	    
	    service.login(username, new AsyncCallback<Void>() {
	        
            @Override
            public void onSuccess(Void result) {
                whenLoggedIn(username);
            }
            
            @Override
            public void onFailure(Throwable caught) { }
	        
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
                new AsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) { }
                    
                    @Override
                    public void onFailure(Throwable caught) { }
                });	    
	}
}
