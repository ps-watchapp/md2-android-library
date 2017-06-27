package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2ContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by felix_000 on 07.05.2017.
 */

public class Md2ContentProviderAddAction extends AbstractMd2Action {


    private String contentProvider;
   private String contentProviderSource;
    private String Test="test";


    public Md2ContentProviderAddAction(String contentProvider, String contentProviderSource) {
        super("Md2ContentProviderAddAction"+contentProvider);
        this.contentProvider=contentProvider;
        this.contentProviderSource= contentProviderSource;
    }

    public void execute() {
        AbstractMd2MultiContentProvider cp =(AbstractMd2MultiContentProvider) Md2ContentProviderRegistry.getInstance().getContentMultiProvider(contentProvider);
        if(Md2ContentProviderRegistry.getInstance().getContentProvider(contentProviderSource)!=null) {
            AbstractMd2ContentProvider cps = (AbstractMd2ContentProvider) Md2ContentProviderRegistry.getInstance().getContentProvider(contentProviderSource);

            cp.add(cps.getContent());}
        else if (Md2ContentProviderRegistry.getInstance().getContentMultiProvider(contentProviderSource)!=null){
            Md2MultiContentProvider cps =  Md2ContentProviderRegistry.getInstance().getContentMultiProvider(contentProviderSource);
for(Md2Entity entity: cps.getContents()){
           cp.add(entity);}
        }
    }

}
