package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;

/**
 * Created by Alexander on 25.06.2017.
 */

public class Md2ContentProviderRemoveActiveAction extends AbstractMd2Action{

    String contentProvider;

    public Md2ContentProviderRemoveActiveAction(String cp){
        super("Md2ContentProviderRemoveActiveAction"+cp);
        contentProvider = cp;
    }

    public void execute(){
        AbstractMd2MultiContentProvider cp = (AbstractMd2MultiContentProvider) Md2ContentProviderRegistry.getInstance().getContentMultiProvider(contentProvider);
        cp.remove(cp.getCurrentIndex());
    }

}