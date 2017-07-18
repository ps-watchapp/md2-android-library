package de.uni_muenster.wi.md2library.model.dataStore.interfaces;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Interface definition for a data store.
 * <p/>
 * Created on 14/08/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public interface Md2DataStore {

    /**
     * Execute query in data store.
     *
     * @param query the query
     * @return the md 2 entity
     */
    void query(String query);

    /**
     * Try to get internal id. I.e., look for entity in database that has the same values as the entity.
     *
     * @param entity the entity
     * @return the internal id
     */
    void getInternalId(Md2Entity entity);

    /**
     * Load entity.
     *
     * @param id       the id
     * @param dataType the data type
     * @return the hash map
     */
    void load(long id, Class dataType);

    /**
     * Put long.
     *
     * @param md2Entity the md 2 entity
     * @return the long
     */
    void put(Md2Entity md2Entity);

    /**
     * Put entity.
     *
     * @param id        the id
     * @param md2Entity the md 2 entity
     * @return the long
     */
    void put(long id, Md2Entity md2Entity);

    /**
     * Remove entity.
     *
     * @param id        the id
     * @param md2Entity the md 2 entity
     */
    void remove(long id, Class md2Entity);
}
     */
    void remove(long id, T md2Entity);
}
