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
    
    public enum State { STARTED, UPDATING, FINISHED }
    
    private static final long serialVersionUID = -7456299417172643586L;
    
    private String author;
    private int lineId;
    private State state = State.STARTED;
    
    private double width;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    
    public String info() {
        return  "u: " + author +
               " l: " + lineId +
               " s: " + state.name() +
               " w: " + width +
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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

}
