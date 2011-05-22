/**
 * 
 */
package com.acme.collpaint.client.page;

import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.page.CollPaintPresenter.UpdatesSender;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.page</dd>
 * </dl>
 *
 * <code>CollPaintView</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 6:35:02 PM 
 *
 */
public class CollPaintView extends Composite implements CollPaintPresenter.Display {
    
    interface CollPaintViewUiBinder extends UiBinder<Widget, CollPaintView> { }   
    private static CollPaintViewUiBinder uiBinder = 
                                 GWT.create(CollPaintViewUiBinder.class);
    
    @UiField Label loginStatus;
    @UiField Button logout;
    
    @UiField Button clearCanvas;
    @UiField FlowPanel colors;
    @UiField ListBox thickness;
    
    @UiField FlowPanel canvasHolder;
    
    Canvas canvas = null;

    public CollPaintView() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
        setupComponents();
    }
    
    protected void setupComponents() {
        
    }

    @Override
    public void disableControls() {
        
    }

    @Override
    public void drawUpdate(LineUpdate update) {
        
    }

    @Override
    public void enableControls() {
        
    }

    @Override
    public void prepareCanvas() {
        
    }

    @Override
    public void setUpdatesSender(UpdatesSender updatesSender) {
        
    }

    @Override
    public void updateLoginStatus(String username) {
        
    }
    
}
