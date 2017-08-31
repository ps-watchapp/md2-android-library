package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public enum Operator {



    EQUAL("="),
    LESS("<"),
    GREATER(">"),
    LESSEQUAL("=<"),
    GREATEREQUAL("=>");

    private String op;


    Operator(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }
}
