/**
 * 
 */
package com.acme.collpaint.client;

import java.io.Serializable;

import com.acme.collpaint.client.comet.CometSessionsSupport.MessageWithAuthor;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>LineUpdate</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:20:09 PM 
 *
 */
public class LineUpdate implements Serializable, MessageWithAuthor {
    
    public enum State { DRAWING, FINISHED };
    
    private static final long serialVersionUID = -7456299417172643586L;
    
    private String author;
    private int lineId;
    private State state = State.DRAWING;
    
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
               " s: " + state.name() +
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public void setStartY(double startY) {
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

}
