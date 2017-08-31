package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.AtomicExpression;
import de.uni_muenster.wi.md2library.model.dataStore.CombinedExpression;
import de.uni_muenster.wi.md2library.model.dataStore.Expression;
import de.uni_muenster.wi.md2library.model.dataStore.Filter;
import de.uni_muenster.wi.md2library.model.dataStore.Junction;
import de.uni_muenster.wi.md2library.model.dataStore.Operator;
import de.uni_muenster.wi.md2library.model.dataStore.SqlUtils;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2LocalStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by felix_000 on 19.05.2017.
 */


public abstract class AbstractMd2OrmLiteDatastore<T extends  Md2Entity> extends  AbstractMd2DataStore<T> implements Md2LocalStore<T> {


    Dao<T , Integer> myDao;
    //protected Md2ContentProvider contentProvider;
     SimpleDateFormat simpleDateFormat;


    @Override
    public void query(Filter filter) {
        if(filter!=null){
        try {
            QueryBuilder<T,Integer > queryBuilder = getMyDao().queryBuilder();
            Where<T, Integer> where = queryBuilder.where();
            this.whereBuilder(filter.getFilterTree(),where);
            PreparedQuery query = queryBuilder.prepare();
            System.out.println(query.getStatement());
           contentProvider.overwriteContent(getMyDao().query(query));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}

    public void query(Filter filter, Timestamp modifiedDate) {
    /*AtomicExpression atomicExpression;
    Filter myFilter;
        if (modifiedDate!= null){
        atomicExpression=   new AtomicExpression("MODIFIED_TIMESTAMP", Operator.GREATER, "'"+simpleDateFormat.format(modifiedDate)+"'");
    }else {
        if (filter==null) filter=new Filter();
        query(filter);
        return;
    }
        if(filter!=null){
        myFilter = new Filter( new CombinedExpression(filter.getFilterTree(), Junction.AND,atomicExpression ));
    }
        else{
        myFilter = new Filter(atomicExpression);
    }*/
        this.query(filter);}


public abstract Where<T, Integer> whereBuilder(Expression exp, Where<T, Integer> where );
  /*  {
    if (exp != null) {

        if (exp instanceof AtomicExpression) {
            AtomicExpression aexp = (AtomicExpression) exp;
          switch (aexp.getOperator()) {
              case EQUAL:
                  try {
                      where.eq((aexp).getLeftOperand(), aexp.getRightOperand());
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  break;
              case GREATEREQUAL:
                  try {
                      where.ge((aexp).getLeftOperand(), aexp.getRightOperand());
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  break;
              case LESSEQUAL:
                  try {
                      where.le((aexp).getLeftOperand(), aexp.getRightOperand());
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  break;
              case LESS:
                  try {
                      where.lt((aexp).getLeftOperand(), aexp.getRightOperand());
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  break;
              case GREATER:
                  try {
                      where.gt((aexp).getLeftOperand(), aexp.getRightOperand());
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  break;

          }
        } else {
            CombinedExpression cexp = (CombinedExpression) exp;
            whereBuilder(cexp.getLeftExpression(),where);
            whereBuilder(cexp.getRightExpression(),where);
            switch (cexp.getJunction()) {
                case AND:
                    where.and(2);
                    break;
                case OR:
                    where.or(2);
                    break;
            }

        }

    }
    return where;
}*/

@Override
    public  void getInternalId(Md2Entity entity) {
        try {
            getMyDao().extractId((T) entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // return 0;
    }

    @Override
    public void load(long id, Class dataType) {
        try {
            T result= getMyDao().queryForId((int)(id));
            List<Md2Entity> list= new ArrayList<Md2Entity>();
            list.add(result);
            contentProvider.overwriteContent(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

@Override
    public  void put(Md2Entity md2Entity) {
        try {
            if(md2Entity.getId()>0){
            getMyDao().update((T) md2Entity);}
            else{
              getMyDao().create((T)md2Entity) ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
//return md2Entity.getId();
    }

@Override
    public void put(long id, Md2Entity md2Entity) {
        try {
            if(md2Entity.getId()>0){
                getMyDao().update((T) md2Entity);}
            else{
                getMyDao().create((T)md2Entity) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    //return md2Entity.getId();
    }


public abstract Dao<T,Integer> getMyDao();



    @Override
    public void remove(long id, Class md2Entity) {
        try {
            getMyDao().deleteById((int)id);
          //  myDao.delete(md2Entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







public List<T> loadAll(){
    List<T> all= new ArrayList<T>();
        try {
           all=  getMyDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }


}
