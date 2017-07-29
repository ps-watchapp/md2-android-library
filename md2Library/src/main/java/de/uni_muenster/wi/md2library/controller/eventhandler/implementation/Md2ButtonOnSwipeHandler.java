package de.uni_muenster.wi.md2library.controller.eventhandler.implementation;

import android.view.View;

import de.uni_muenster.wi.md2library.controller.action.interfaces.Md2Action;

/**
 * EventHandler für Swipe-Events, leitet diese an leftswipehandler / rightswipehandler weiter,
 * da ein Button immer nur einen OnTouchListener gleichzeitig halten kann / wurde nicht geswiped, wird
 * das TouchEvent als Klick registriert und an den OnClickHandler weitergeleitet
 * Created by Alexander on 12.05.2017.
 */

public class Md2ButtonOnSwipeHandler extends AbstractMd2OnSwipeHandler implements View.OnTouchListener {

    private Md2OnLeftSwipeHandler lsh;
    private Md2OnRightSwipeHandler rsh;

    public Md2OnLeftSwipeHandler getLeftSwipeHandler(){
        return lsh;
    }

    public Md2OnRightSwipeHandler getRightSwipeHandler(){
        return rsh;
    }

    /**
     * Instantiates a new Md 2 on left swipe handler.
     *
     */
    public Md2ButtonOnSwipeHandler() {
        super();
        lsh = new Md2OnLeftSwipeHandler();
        rsh = new Md2OnRightSwipeHandler();
    }

    @Override
    public boolean onSwipeRight() {
        System.out.println("swiped right");
        this.rsh.onSwipeRight();
        return false;
    }

    @Override
    public boolean onSwipeLeft() {
        System.out.println("swiped left");
        this.lsh.onSwipeLeft();
        return false;
    }


    public void registerAction(Md2Action action, boolean right){
        if (right){
            rsh.registerAction(action);
        } else {
            lsh.registerAction(action);
        }
    }

}
