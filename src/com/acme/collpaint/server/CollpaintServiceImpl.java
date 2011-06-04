/**
 * 
 */
package com.acme.collpaint.server;

import com.acme.collpaint.client.CollPaintException;
import com.acme.collpaint.client.CollPaintService;
import com.acme.collpaint.client.LineUpdate;
import com.acme.collpaint.client.comet.CometSessionException;

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
public class CollpaintServiceImpl extends ServletWithCometSessions 
                                  implements CollPaintService {    

    @Override
    public void updateLine(LineUpdate data)
            throws CometSessionException, CollPaintException {
        sendToAll(data);
    }

}
