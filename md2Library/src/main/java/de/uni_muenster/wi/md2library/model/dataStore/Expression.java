package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public abstract class Expression {
    private boolean negated;

    public boolean isNegated() {
        return negated;
    }

    public void setNegated(boolean negated) {
        this.negated = negated;
    }
}
