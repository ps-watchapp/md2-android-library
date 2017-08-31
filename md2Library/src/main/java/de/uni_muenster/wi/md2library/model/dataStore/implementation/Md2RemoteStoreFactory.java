package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import java.net.URL;

import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2RemoteDataStoreFactory;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by Fabian on 09/07/2015.
 */
public class Md2RemoteStoreFactory extends AbstractMd2DataStoreFactory implements Md2RemoteDataStoreFactory {


    public Md2DataStore getDataStore(String uri, Md2Entity entity) {
        try{
            URL url = new URL(uri.replaceFirst("/$",""));
            return new Md2RemoteStore<>( url, entity.getClass() );
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Md2DataStore getDataStore(String uri, String entity) {
        return null;
    }
}
