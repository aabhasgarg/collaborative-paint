/**
 * 
 */
package com.acme.collpaint.client.comet;

import java.io.Serializable;
import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.Window;
import net.zschech.gwt.comet.client.CometListener;

import com.acme.collpaint.client.LineUpdate;

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
        Log.debug("connected [heartbeat: " + heartbeat + "]");
    }

    @Override
    public void onDisconnected() {
        Log.debug("disconnected");
    }

    @Override
    public void onError(Throwable exception, boolean connected) {
        Log.error("error [connected: " + connected+ "; exception: " + exception.getMessage() + "]");        
    }

    @Override
    public void onHeartbeat() {
        Log.debug("heartbeat");
    }

    @Override
    public void onMessage(List<? extends Serializable> messages) {
        for (Serializable message: messages) {
            if (message instanceof LineUpdate) {
                LineUpdate update = (LineUpdate)message;
                Window.alert("Received line update " + update.info());
                Log.debug("Received line update: " + update.info());
            } else {
                Log.debug("Received message: " + message.toString());
            }
        }
        
    }

    @Override
    public void onRefresh() {
        Log.debug("refresh");
    }

}
