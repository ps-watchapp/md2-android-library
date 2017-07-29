package de.uni_muenster.wi.md2library.controller.eventhandler.implementation;

import android.view.MotionEvent;
import android.view.View;

import de.uni_muenster.wi.md2library.controller.action.interfaces.Md2Action;

/**
 * Event handler for onLongClick events.
 * Related to ElementEventType onLongClick in MD2-DSL.
 * Implements the interface View.OnLongClickListener and View.OnTouchListener
 * <p/>
 * Created on 11/05/2017
 *
 * @author Fernando Koch
 * @version 1.0
 * @since 1.0
 */
public class Md2OnLongClickHandler extends AbstractMd2WidgetEventHandler implements View.OnLongClickListener, View.OnTouchListener {

    /**
     * Instantiates a new Md 2 on long click handler.
     */
    public Md2OnLongClickHandler() {
        super();
    }

    @Override
    public boolean onLongClick(View v) {
        System.out.println("Longclicked");
        this.execute();
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                this.execute();
                return true;
        }
        return false;
    }

    @Override
    public void execute(){
        super.execute();
        System.out.println("execute");
    }


    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("MD2OnLongClickHandler: #Actions = " + getActions().size() + "; ");
        for (Md2Action action : getActions()) {
            result.append(action.getActionSignature() + "; ");
        }
        return result.toString();
    }
}
