package de.uni_muenster.wi.md2library.view.widgets.implementation;

import android.content.Context;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.util.AttributeSet;

import de.uni_muenster.wi.md2library.controller.eventhandler.implementation.Md2OnChangedHandler;
import de.uni_muenster.wi.md2library.controller.eventhandler.implementation.Md2OnClickHandler;
import de.uni_muenster.wi.md2library.view.widgets.interfaces.Md2Container;
import de.uni_muenster.wi.md2library.view.widgets.interfaces.Md2Widget;

/**
 * Implementation of ActionDrawer element in MD2-DSL
 * <p/>
 * Created by MWaves on 5/12/2017.
 *
 * @author Martin Yankov
 * @version 1.0
 * @since 1.0
 */

public class Md2ActionDrawer extends WearableActionDrawer implements Md2Container {


    /**
     * The widget id
     */
    protected int widgetId;

    /**
     * Instantiates a new Md2 Action Drawer
     * @param context the context
     */

    public Md2ActionDrawer(Context context) {
        super(context);
        init();
    }

    /**
     * Instantiates a new Md2 Action Drawer
     *
     * @param context the context
     * @param attrs the attributes
     */
    public Md2ActionDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Instantiates a new MD2 Action Drawer
     *
     * @param context the context
     * @param attrs the attributes
     * @param defStyleAttr the definition style attribute
     */
    public Md2ActionDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Instantiates a new Md2 Action Drawer
     *
     * @param actionDrawer actionDrawer to be copied
     */

    private Md2ActionDrawer(Md2ActionDrawer actionDrawer) {
        super(actionDrawer.getContext());
        this.setDisabled(actionDrawer.isDisabled());
        this.setWidgetId(actionDrawer.getWidgetId());
    }

    /**
     * Init with default values
     */

    protected void init() {
        this.setOnChangedHandler(new Md2OnChangedHandler());
        this.setOnClickHandler(new Md2OnClickHandler());
        this.widgetId = -1;
    }

    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.setEnabled(!disabled);
    }

    @Override
    public int getWidgetId() {
        return this.widgetId;
    }

    @Override
    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    @Override
    public Md2OnClickHandler getOnClickHandler() {
        return null;
    }

    @Override
    public void setOnClickHandler(Md2OnClickHandler onClickHandler) {

    }

    @Override
    public Md2OnChangedHandler getOnChangedHandler() {
        return null;
    }

    @Override
    public void setOnChangedHandler(Md2OnChangedHandler onChangedHandler) {

    }

    @Override
    public Md2Widget copy() {
        return new Md2ActionDrawer(this);
    }

    @Override
    public boolean equals(Object otherWidget) {
        if(otherWidget == null)
            return false;

        if (!(otherWidget instanceof Md2ActionDrawer))
            return false;

        Md2ActionDrawer otherMdActionDrawer = (Md2ActionDrawer) otherWidget;

        return this.getWidgetId() == otherMdActionDrawer.getWidgetId();
    }
}