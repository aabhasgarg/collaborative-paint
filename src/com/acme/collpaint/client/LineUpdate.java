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
    
    public int userId;
    public int lineId;
    public State state;
    
    public float width;
    public float startX;
    public float startY;
    public float endX;
    public float endY;
    
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

}
