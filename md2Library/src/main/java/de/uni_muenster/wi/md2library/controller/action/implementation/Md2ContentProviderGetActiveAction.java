package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2ContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.view.management.implementation.Md2ViewManager;

/**
 * Created by Alexander on 25.06.2017.
 */

public class Md2ContentProviderGetActiveAction extends AbstractMd2Action{


    private String target;
    private String source;

    public Md2ContentProviderGetActiveAction(String target, String source){
        super("Md2ContentProviderGetActiveAction"+target);
        this.target = target;
        this.source = source;
    }

    public void execute(){
        AbstractMd2MultiContentProvider scp = (AbstractMd2MultiContentProvider) Md2ContentProviderRegistry.getInstance().getContentMultiProvider(source);
        AbstractMd2ContentProvider tcp = (AbstractMd2ContentProvider) Md2ContentProviderRegistry.getInstance().getContentProvider(target);
        tcp.setContent(scp.getContentsList().get(scp.getCurrentIndex()));
        tcp.notfiyOnChangeHandlers();
    }

}