package de.uni_muenster.wi.md2library.controller.eventhandler.implementation;

import android.content.Context;
import android.view.View;

/**
 * Event handler for left swipe events
 * Related to ElementEventType onLeftSwipe in MD2-DSL
 * <p/>
 * Created on 11/08/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
//angepasst für funktionierende Swipe-Implementierung 12.05.
public class Md2OnLeftSwipeHandler extends AbstractMd2WidgetEventHandler{

    /**
     * Instantiates a new Md 2 on left swipe handler.
     *
     */
    public Md2OnLeftSwipeHandler() {
        super();
    }

    public boolean onSwipeLeft() {
        this.execute();
        return false;
    }

}