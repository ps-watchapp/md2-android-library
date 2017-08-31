package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public class Filter {


   Expression filterTree;


    public Filter(Expression filterTree) {
        this.filterTree = filterTree;
    }

    public Filter() {
    }

    public Expression getFilterTree() {
        return filterTree;
    }

    public void setFilterTree(Expression filterTree) {
        this.filterTree = filterTree;
    }
}
