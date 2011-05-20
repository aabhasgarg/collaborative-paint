/**
 * 
 */
package com.acme.collpaint.client.comet;

import java.io.Serializable;
import java.util.List;

import com.acme.collpaint.client.LineUpdate;
import com.allen_sauer.gwt.log.client.Log;

import net.zschech.gwt.comet.client.CometListener;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.comet</dd>
 * </dl>
 *
 * <code>CollPaintCometListener</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:04:00 PM 
 *
 */
public class CollPaintCometListener implements CometListener {

    @Override
    public void onConnected(int heartbeat) {
        
    }

    @Override
    public void onDisconnected() {
        
    }

    @Override
    public void onError(Throwable exception, boolean connected) {
        
    }

    @Override
    public void onHeartbeat() {
        
    }

    @Override
    public void onMessage(List<? extends Serializable> messages) {
        for (Serializable message: messages) {
            if (message instanceof LineUpdate) {
                Log.debug("Received line update: " + ((LineUpdate)message).info());
            } else {
                Log.debug("Received message: " + message.toString());
            }
        }
        
    }

    @Override
    public void onRefresh() {
        
    }

}
