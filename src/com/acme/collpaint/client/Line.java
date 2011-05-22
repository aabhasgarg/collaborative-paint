/**
 * 
 */
package com.acme.collpaint.client;

import java.io.Serializable;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>Line</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 10:00:28 PM 
 *
 */
public class Line implements Serializable {
    
    private static final long serialVersionUID = -2646135435876703799L;
    
    private String author;
    private int lineId;

    private double thickness;
    
    private double red;
    private double green;
    private double blue;
    
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    
    public String info() {
        return  "u: " + author +
               " l: " + lineId +
               " w: " + thickness +
               " cr: " + red +
               " cb: " + blue +
               " cg: " + green +               
               " sx: " + startX +
               " sy: " + startY +
               " ex: " + endX +
               " ey: " + endY;
    }    
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double width) {
        this.thickness = width;
    }

    public double getStartX() {
        return startX;
    }
    
    public double getStartY() {
        return startY;
    }

    public void setStart(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEnd(double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
    }
    
    public double getRed() { 
        return red;
    }
    
    public double getBlue() { 
        return blue;
    }    
    
    public double getGreen() { 
        return green;
    }

    
    public void setColor(double red, double blue, double green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    
    public CssColor getColor() {
        return CssColor.make((int)(red * 255), 
                             (int)(green * 255), 
                             (int)(blue * 255));        
    }
    
    private void draw(Context2d context, int ctxWidth, int ctxHeight) {
        context.moveTo(startX * ctxWidth, startY * ctxHeight);
        context.setStrokeStyle(getColor());
        context.setLineWidth(thickness * 10);
        context.lineTo(endX * ctxWidth, endY * ctxHeight);
    }
    
    public static void draw(Context2d context, Line line,
                            int ctxWidth, int ctxHeight) {
        line.draw(context, ctxWidth, ctxHeight);     
    }

}
