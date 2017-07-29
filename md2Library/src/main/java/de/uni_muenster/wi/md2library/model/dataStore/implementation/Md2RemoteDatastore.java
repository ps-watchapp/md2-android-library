package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import java.util.HashMap;

import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by felix_000 on 19.05.2017.
        */

public class Md2RemoteDatastore implements Md2DataStore {

    private String webserviceURI;


    public Md2RemoteDatastore(String uri) {
        super();
        this.webserviceURI=uri;
    }



    @Override
    public Md2Entity query(String query) {
        return null;
    }

    @Override
    public long getInternalId(Md2Entity entity) {
        return 0;
    }

    @Override
    public HashMap<String, String> load(long id, String dataType) {
        return null;
    }

    @Override
    public long put(Md2Entity md2Entity) {
        return 0;
    }

    @Override
    public long put(long id, Md2Entity md2Entity) {
        return 0;
    }

    @Override
    public void remove(long id, Md2Entity md2Entity) {

    }
}
