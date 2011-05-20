/**
 * 
 */
package com.acme.collpaint.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client</dd>
 * </dl>
 *
 * <code>CollPaintService</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:47:55 PM 
 *
 */
@RemoteServiceRelativePath("service")
public interface CollPaintService extends RemoteService {
    
    public void updateLine(double startX, double startY,
                           double endX, double endY) throws CollPaintException;
    
}
