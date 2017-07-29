package de.uni_muenster.wi.md2library.controller.action.implementation;

import de.uni_muenster.wi.md2library.controller.action.interfaces.ICondition;

/**
 * Created by felix_000 on 07.05.2017.
 */

public class WhereCondition implements ICondition{


    private WhereCondition leftSide;
    private WhereCondition rightSide;
    private Operators operator;


    public WhereCondition getRightSide() {
        return rightSide;
    }

    public void setRightSide(WhereCondition rightSide) {
        this.rightSide = rightSide;
    }

    public WhereCondition getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(WhereCondition leftSide) {
        this.leftSide = leftSide;
    }



    public Operators getOperator() {
        return operator;
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }







}
