/**
 * 
 */
package com.acme.collpaint.client;

import java.io.Serializable;

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
public class LineUpdate implements Serializable {
    
    public enum State { STARTED, UPDATING, FINISHED }
    
    private static final long serialVersionUID = -7456299417172643586L;
    
    private int userId;
    private int lineId;
    private State state = State.STARTED;
    
    private float width;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    
    public String info() {
        return  "u: " + userId +
               " l: " + lineId +
               " s: " + state.name() +
               " w: " + width +
               " sx: " + startX +
               " sy: " + startY +
               " ex: " + endX +
               " ey: " + endY;
     }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

}
