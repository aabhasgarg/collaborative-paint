package com.acme.collpaint.client;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;

import com.acme.collpaint.client.comet.CollPaintCometListener;
import com.acme.collpaint.client.comet.CollPaintCometSerializer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CollaborativePaint implements EntryPoint {
	
	public void onModuleLoad() {
	    CometSerializer serializer = GWT.create(CollPaintCometSerializer.class);
	    CometClient client = new CometClient("/collpaint/comet", serializer,
	                                         new CollPaintCometListener());
	    client.start();
	    
	    Button sendButton = new Button("Send line update",
	            new ClickHandler() {
                    
                    @Override
                    public void onClick(ClickEvent event) {
                        // TODO Auto-generated method stub
                    }
                });
	    RootPanel.get().add(sendButton);        
	}
}
