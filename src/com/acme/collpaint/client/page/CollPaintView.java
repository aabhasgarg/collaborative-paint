/**
 * 
 */
package com.acme.collpaint.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * <dl>
 * <dt>Project:</dt> <dd>collaborative-paint</dd>
 * <dt>Package:</dt> <dd>com.acme.collpaint.client.page</dd>
 * </dl>
 *
 * <code>CollPaintView</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date May 22, 2011 6:35:02 PM 
 *
 */
public class CollPaintView extends Composite implements CollPaintPresenter.Display {
    
    interface CollPaintViewUiBinder extends UiBinder<Widget, CollPaintView> { }   
    private static CollPaintViewUiBinder uiBinder = 
                                 GWT.create(CollPaintViewUiBinder.class);

    public CollPaintView() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
    }
    
}
