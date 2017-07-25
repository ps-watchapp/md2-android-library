package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public enum Junction {

    AND("AND"),
    OR("OR");

    private String junction;


    Junction(String junction) {
        this.junction = junction;
    }


    public String getJunction() {
        return junction;
    }
}
