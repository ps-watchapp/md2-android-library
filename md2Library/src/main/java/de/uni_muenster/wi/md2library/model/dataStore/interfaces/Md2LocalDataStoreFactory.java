package de.uni_muenster.wi.md2library.model.dataStore.interfaces;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by Fabian on 21.09.2015.
 */
public interface Md2LocalDataStoreFactory extends Md2DataStoreFactory {

    /**
     * Gets data store.
     *
     * @param entity the entity
     * @return the data store
     */
    <T extends Md2Entity> Md2LocalStore<T> getDataStore(String entity);
}
