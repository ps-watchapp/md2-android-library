package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.model.contentProvider.implementation.AbstractMd2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.implementation.Md2ContentProviderRegistry;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;

/**
 * Created by felix_000 on 07.05.2017.
 */

public class Md2ContentProviderRemoveAction {

    private String contentProvider;
    private WhereCondition conditions;

    public Md2ContentProviderRemoveAction(String contentProvider) {
        this.contentProvider=contentProvider;
    }

    public void execute() {
        AbstractMd2MultiContentProvider cp =(AbstractMd2MultiContentProvider) Md2ContentProviderRegistry.getInstance().getContentMultiProvider(contentProvider);
        cp.remove(conditions);
    }

}
