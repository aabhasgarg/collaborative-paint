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
    
    private State state = State.DRAWING;
    private Line source;
    
    public LineUpdate() {
        
    }
    
    public LineUpdate(Line source) { 
        this.source = source;
    }
    
    public String info() {
        return source.info() +
               " s: " + state.name();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getAuthor() {
        return source.getAuthor();
    }

    public void setAuthor(String author) {
        source.setAuthor(author);
    }

}
