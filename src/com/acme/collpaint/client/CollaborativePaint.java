package com.acme.collpaint.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CollaborativePaint implements EntryPoint {
	
	public void onModuleLoad() {
    	RootLayoutPanel.get().add(new Label("Hello, Collaborators!"));
	}
}
