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
    @UiField ListBox thicknessBox;
    
    @UiField FlowPanel canvasHolder;
    
    private Canvas canvas = null;
    private UpdatesSender sender = null;
    
    public enum Thickness { NORMAL(0.6),
                            THIN(0.4),
                            FAT(0.8),
                            VERY_THIN(0.2),
                            VERY_FAT(1.0);    
        public final double value;        
        private Thickness(double value) { this.value = value; } 
    };
    
    public enum Color { BLACK(0.0, 0.0, 0.0),
                        WHITE(1.0, 1.0, 1.0),
                        RED(1.0, 0.0, 0.0), 
                        BLUE(0.0, 1.0, 0.0),
                        GREEN(0.0, 0.0, 0.1),
                        LIGHT_GRAY(0.33, 0.33, 0.33),
                        DARK_GRAY(0.66, 0.66, 0.66);    
        public final double r;
        public final double g;
        public final double b;
        private Color(double r, double g, double b) { 
                    this.r = r; this.g = g; this.b = b; }
    };    

    public CollPaintView() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
        setupComponents();
    }
    
    protected void setupComponents() {
        updateLoginStatus(null);        
        enableControls(false);
        
        for (Thickness thickness: Thickness.values()) {
            thicknessBox.addItem(thickness.name() + " (" + thickness.value + ")", 
                                 thickness.name());
        }
        
        for (Color color: Color.values()) {
            final Button colorButton = new Button();
            colorButton.setEnabled(false);
            colorButton.setText(color.name()); // TODO: set background color
            colors.add(colorButton);
        }
    }

    @Override
    public void enableControls(boolean enable) {
        clearCanvas.setEnabled(enable);
        thicknessBox.setEnabled(enable);
        for (Widget child: colors) {
            if (child instanceof Button) ((Button) child).setEnabled(enable); 
        }
    }

    @Override
    public void prepareCanvas() {
        
    }
    
    @Override
    public void drawUpdate(LineUpdate update) {
        
    }    

    @Override
    public void setUpdatesSender(UpdatesSender sender) {
        this.sender = sender;
    }

    @Override
    public void updateLoginStatus(String username) {
        loginStatus.setText(username != null ? ("logged in as " + username) : "not logged in");
        logout.setEnabled(username != null);
    }
    
}
