/**
 * 
 */
package com.acme.collpaint.client.page;

import java.util.HashMap;
import java.util.Map;

import com.acme.collpaint.client.Line;
import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.page.CollPaintPresenter.UpdatesSender;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
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
    
    private final Map<Color, Button> colorButtons = new HashMap<Color, Button>();
    private Color curColor = Color.BLACK;
    private Thickness curThickness = Thickness.NORMAL;
    
    private Canvas canvas = null;
    private final CssColor redrawColor = CssColor.make("rgba(200,200,200,0.6)");
    
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
        
        thicknessBox.addChangeHandler(new ChangeHandler() {
            @Override public void onChange(ChangeEvent event) {
                curThickness = 
                    Thickness.valueOf(
                            thicknessBox.getValue(thicknessBox.getSelectedIndex()));
            }
        });
        
        for (final Color color: Color.values()) {
            final Button colorButton = new Button();
            colorButton.setEnabled(false);
            colorButton.setText(color.name()); // TODO: set background color
            colorButtons.put(color, colorButton);
            colors.add(colorButton);
            
            colorButton.addClickHandler(new ClickHandler() {
                @Override public void onClick(ClickEvent event) {
                    colorButtons.get(curColor).setEnabled(true);
                    curColor = color;
                    colorButtons.get(curColor).setEnabled(false);
                }
            });
        }
    }
    
    @Override
    public void updateLoginStatus(String username) {
        loginStatus.setText(username != null ? ("logged in as " + username) : "not logged in");
        logout.setEnabled(username != null);
    }

    @Override
    public void enableControls(boolean enable) {
        clearCanvas.setEnabled(enable);
        thicknessBox.setEnabled(enable);
        for (Button button: colorButtons.values()) {
            button.setEnabled(enable); 
        }
        
        if (enable) colorButtons.get(curColor).setEnabled(false);        
    }
    
    @Override
    public void prepareCanvas() {
        canvas = Canvas.createIfSupported();
        
        if (canvas == null) {
            canvasHolder.add(new Label("Sorry, your browser doesn't support the HTML5 Canvas element"));
            return;
        }
        
        Window.addResizeHandler(new ResizeHandler() {
            @Override public void onResize(ResizeEvent event) {
                updateCanvasSize();                
            }
        });
        
        canvasHolder.add(canvas);

        updateCanvasSize();
        
        canvas.addMouseDownHandler(new MouseDownHandler() {
            @Override public void onMouseDown(MouseDownEvent event) {
                sender.lineUpdated(
                    startNewLine(event.getRelativeX(canvas.getElement()),
                                 event.getRelativeY(canvas.getElement()))
                );
            }
        });
        
        canvas.addMouseMoveHandler(new MouseMoveHandler() {
            @Override public void onMouseMove(MouseMoveEvent event) {
                sender.lineUpdated(
                    updateCurrentLine(event.getRelativeX(canvas.getElement()),
                                      event.getRelativeY(canvas.getElement()))
                );
            }
        });
        
        canvas.addMouseUpHandler(new MouseUpHandler() {
            @Override public void onMouseUp(MouseUpEvent event) {
                sender.lineFinished(
                    finishCurrentLine(event.getRelativeX(canvas.getElement()),
                                      event.getRelativeY(canvas.getElement()))    
                );
            }
        });
        
    }
    
    protected void updateCanvasSize() {
        int canvasWidth = Window.getClientWidth(); // canvasHolder.getOffsetWidth()
        int canvasHeight = (int)(Window.getClientHeight() * 0.65); // 65% of height        
        
        canvas.setWidth(canvasWidth + "px");
        canvas.setCoordinateSpaceWidth(canvasWidth);        
        canvas.setHeight(canvasHeight + "px");
        canvas.setCoordinateSpaceHeight(canvasHeight);
        
        redrawCanvas(canvasWidth, canvasHeight);
    }
    
    /* protected void redrawCanvas() {
        redrawCanvas(Window.getClientWidth(),, 
                    (int)(Window.getClientHeight() * 0.65));
    } */
    
    protected void redrawCanvas(int width, int height) {
        final Context2d context = canvas.getContext2d();
        
        context.setFillStyle(redrawColor);
        context.fillRect(0, 0, width, height);        
    }
    
    @Override
    public void drawUpdate(LineUpdate update) {
        
    }    

    @Override
    public void setUpdatesSender(UpdatesSender sender) {
        this.sender = sender;
    }
    
    @Override
    public HasClickHandlers getLogoutButton() { return logout; }
    
    private Line startNewLine(int endX, int endY) {
        // TODO Auto-generated method stub
        return null;
    }

    private Line updateCurrentLine(int endX, int endY) {
        // TODO Auto-generated method stub
        return null;
    }

    private Line finishCurrentLine(int endX, int endY) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
