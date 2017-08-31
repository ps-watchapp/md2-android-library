package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public class CombinedExpression extends Expression {


    Expression leftExpression;
    Expression rightExpression;
    Junction junction;

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }

    public Junction getJunction() {
        return junction;
    }

    public void setJunction(Junction junction) {
        this.junction = junction;
    }


    public CombinedExpression(Expression leftExpression,  Junction junction, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.junction = junction;
    }
}
