package de.uni_muenster.wi.md2library.controller.action.implementation;

/**
 * Created by Alexander on 05.07.2017.
 */

import android.support.v7.widget.RecyclerView;

public class Md2RefreshListAction extends AbstractMd2Action {

    private RecyclerView.Adapter la;

    public Md2RefreshListAction(RecyclerView.Adapter listAdapter){
        super(listAdapter.toString());
        la = listAdapter;
    }

    @Override
    public void execute(){
        la.notifyDataSetChanged();
    }




}
