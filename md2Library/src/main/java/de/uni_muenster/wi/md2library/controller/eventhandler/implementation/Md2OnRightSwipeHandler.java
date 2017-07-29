package de.uni_muenster.wi.md2library.controller.eventhandler.implementation;

import android.content.Context;
import android.view.View;

/**
 * Event handler for right swipe events
 * Related to ElementEventType onRightSwipe in MD2-DSL
 * <p/>
 * Created on 11/08/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
//angepasst für funktionierende Swipe-Implementierung 12.05.
public class Md2OnRightSwipeHandler extends AbstractMd2WidgetEventHandler {

    /**
     * Instantiates a new Md2 on right swipe handler.
     *
     */
    public Md2OnRightSwipeHandler() {
        super();
    }


    public boolean onSwipeRight() {
        this.execute();
        return false;
    }

}