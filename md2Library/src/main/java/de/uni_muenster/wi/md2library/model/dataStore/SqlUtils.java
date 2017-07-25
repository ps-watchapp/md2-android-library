package de.uni_muenster.wi.md2library.model.dataStore;

/**
 * Created by felix_000 on 23.07.2017.
 */

public class SqlUtils {


    public static String filterToSqlStatement(Filter filter) {
        return SqlUtils.generateStatementRecursive(filter.getFilterTree());
    }


    private static String generateStatementRecursive(Expression exp) {
        StringBuffer result = new StringBuffer();

        if (exp != null) {
            result.append("( ");
            if (exp instanceof AtomicExpression) {
                AtomicExpression aexp = (AtomicExpression) exp;
                result.append(aexp.getLeftOperand() + " " + aexp.getOperator().getOp() + " " + aexp.getRightOperand());
            } else {
                CombinedExpression cexp = (CombinedExpression) exp;
                result.append(generateStatementRecursive(cexp.getLeftExpression()) + " " +
                        cexp.getJunction().getJunction() + " " + generateStatementRecursive(cexp.getRightExpression()));

            }
            result.append(" )");
        }
        return result.toString();
    }




    public static void main (String[] args){
      Filter myFilter = new Filter();
     
      AtomicExpression aexp1 = new AtomicExpression("Weight", Operator.GREATER, "50");
        AtomicExpression aexp2 = new AtomicExpression("Name", Operator.EQUAL, "'Hans'");
        CombinedExpression mycomb1 = new CombinedExpression(aexp1,Junction.AND , aexp2);
        myFilter.setFilterTree(mycomb1);
      System.out.println(SqlUtils.filterToSqlStatement(myFilter));
    }



}


