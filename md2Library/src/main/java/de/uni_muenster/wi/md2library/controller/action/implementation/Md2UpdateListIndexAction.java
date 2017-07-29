package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.controller.action.interfaces.Md2Action;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;

/**
 * Created by Alexander on 20.06.2017.
 */

public class Md2UpdateListIndexAction extends AbstractMd2Action {

    private Md2MultiContentProvider provider;
    private int index;

    public Md2UpdateListIndexAction(String adapter, int i, Md2MultiContentProvider p){
        super("Md2UpdateListIndexAction"+adapter+i);
        provider = p;
        index = i;
    }

    @Override
    public void execute(){
        provider.setCurrentIndex(index);
    }

}