package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2LocalStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by felix_000 on 19.05.2017.
 */


public abstract class AbstractMd2OrmLiteDatastore<T extends  Md2Entity> implements Md2LocalStore<T>{


    Dao<T , Integer> myDao;

    @Override
    public Md2Entity query(String query) {
        return null;
    }

@Override
    public  long getInternalId(T entity) {
        try {
            getMyDao().extractId(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public HashMap<String, String> load(long id, String dataType) {
        return null;
    }

@Override
    public  long put(T md2Entity) {
        try {
            getMyDao().createOrUpdate(md2Entity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
return md2Entity.getId();
    }

@Override
    public long put(long id, T md2Entity) {
        try {
            getMyDao().createOrUpdate(md2Entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
return md2Entity.getId();
    }


public abstract Dao<T,Integer> getMyDao();



    @Override
    public void remove(long id, T md2Entity) {
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
