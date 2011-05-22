package com.acme.collpaint.client;

import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometSerializer;

import com.acme.collpaint.client.comet.CollPaintCometListener;
import com.acme.collpaint.client.comet.CollPaintCometSerializer;
import com.acme.collpaint.client.page.CollPaintPresenter;
import com.acme.collpaint.client.page.CollPaintView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CollaborativePaint implements EntryPoint {
    
    public static final String COMET_PATH = GWT.getModuleBaseURL() + "comet";
    
	public void onModuleLoad() {
	    final CollPaintServiceAsync service = GWT.create(CollPaintService.class);
	    
	    CollPaintView view = new CollPaintView();
	    CollPaintPresenter presenter = new CollPaintPresenter(service, view);
	    
	    CometSerializer serializer = GWT.create(CollPaintCometSerializer.class);
	    CometClient client = new CometClient(COMET_PATH, serializer,
	                                         new CollPaintCometListener(presenter));
	    client.start();
	    
	    RootPanel.get().add(view);
	    
	    presenter.launch();
	    
	    
	    /* service.getUsername(new HandlingCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && !result.isEmpty()) {
                    loggedIn = true;
                    whenLoggedIn(result);
                }
            }
        }); */
	    
	    
	}
		
}
