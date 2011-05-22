/**
 * 
 */
package com.acme.collpaint.client;

import com.acme.collpaint.client.comet.CometSessionException;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>CollPaintException</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:17:41 PM 
 *
 */
public class CollPaintException extends CometSessionException {
    
    public CollPaintException() { this(""); }
    
    public CollPaintException(String message) { super(message); }

}
