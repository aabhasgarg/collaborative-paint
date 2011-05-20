/**
 * 
 */
package com.acme.collpaint.client.comet;

import com.acme.collpaint.client.LineUpdate;

import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.comet</dd>
 * </dl>
 *
 * <code>CollPaintCometSerializer</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 20, 2011 11:37:39 PM 
 *
 */
@SerialTypes({ LineUpdate.class })
public abstract class CollPaintCometSerializer extends CometSerializer {
}
