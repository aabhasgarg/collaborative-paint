/**
 * 
 */
package com.acme.collpaint.server;

import javax.servlet.http.HttpSession;

import net.zschech.gwt.comet.server.CometServlet;
import net.zschech.gwt.comet.server.CometSession;

import com.acme.collpaint.client.CollPaintException;
import com.acme.collpaint.client.CollPaintService;
import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.LineUpdate.State;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.server</dd>
 * </dl>
 *
 * <code>CollpaintServiceImpl</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:51:15 PM 
 *
 */
public class CollpaintServiceImpl extends RemoteServiceServlet 
                                  implements CollPaintService {

    @Override
    public void updateLine(int startX, int startY, int endX, int endY)
            throws CollPaintException {
        // Get or create the HTTP session for the browser
        HttpSession httpSession = getThreadLocalRequest().getSession();
        // Get or create the Comet session for the browser
        CometSession cometSession = CometServlet.getCometSession(httpSession);
        
        final LineUpdate lineUpdate = new LineUpdate();
        lineUpdate.state = State.STARTED;
        lineUpdate.startX = 12;
        lineUpdate.startY = 20;
        lineUpdate.endX = 12;
        lineUpdate.endY = 30;
        
        cometSession.enqueue(lineUpdate);
    }

}
