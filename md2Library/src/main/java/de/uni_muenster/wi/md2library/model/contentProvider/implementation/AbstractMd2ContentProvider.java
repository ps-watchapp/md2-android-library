package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import com.google.common.base.Predicates;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import de.uni_muenster.wi.md2library.controller.eventhandler.implementation.Md2OnAttributeChangedHandler;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.Filter;
import de.uni_muenster.wi.md2library.model.dataStore.implementation.AbstractMd2OrmLiteDatastore;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

/**
 * Abstract super class for content providers.
 * Concrete sub-classes are generated.
 * Represents the ContentProvider element in MD2-DSL
 * <p/>
 * Created on 10/07/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractMd2ContentProvider implements Md2ContentProvider {
    protected Filter filter;
    /**
     * The Key.
     */
    protected String key;
    /**
     * The SyncTimestamp
     *
     */
    protected Timestamp syncTimestamp;

    /**
     * The Content.
     */
    protected Md2Entity content;
    /**
     * The Backup.
     */
    protected Md2Entity backup;
    /**
     * The Md 2 data store.
     */
    protected Md2DataStore md2DataStore;
    /**
     * The Attribute changed event handlers.
     */
//protected HashMap<String, Md2Type> observedAttributes;
    protected HashMap<String, Md2OnAttributeChangedHandler> attributeChangedEventHandlers;
    /**
     * The Internal id.
     */
    protected long internalId;
    /**
     * The Exists in data store.
     */
    protected boolean existsInDataStore;

    /**
     * Instantiates a new Abstract md 2 content provider.
     *
     * @param key          the key
     * @param content      the content
     * @param md2DataStore the md 2 data store
     */
    public AbstractMd2ContentProvider(String key, Md2Entity content, Md2DataStore md2DataStore) {
        this.content = content;

        if (content != null) {
            this.backup = (Md2Entity) content.clone();
        }
        attributeChangedEventHandlers = new HashMap<>();
        //observedAttributes = new HashMap<>();
        this.md2DataStore = md2DataStore;
        md2DataStore.setContentProvider(this);
        this.existsInDataStore = false;
        internalId = -1;
        this.load();
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    /**
     * Gets internal id.
     *
     * @return the internal id
     */
    protected long getInternalId() {
        return internalId;
    }

    /**
     * Sets internal id.
     *
     * @param internalId the internal id
     */
    protected void setInternalId(long internalId) {
        this.internalId = internalId;
    }

    @Override
    public Md2Entity getContent() {
        return content;
    }

    @Override
    public void setContent(Md2Entity content) {
        if (content != null) {
            this.content = content;
            this.backup = (Md2Entity) content.clone();
            this.internalId = -1;
            this.load();
        }
    }
    @Override
    public void overwriteContent(List<Md2Entity> content){
        if ((content != null)&&(content.isEmpty()==false)) {
            this.content = content.get(0);
        }
        callAllHandlers();
    }

    public void updateContent(List<Md2Entity> updates) {

        if ((updates != null)&&(updates.isEmpty() == false)) {

            for (Md2Entity entity1 : updates) {
                Timestamp updateTimestamp = entity1.getModifiedDate();

                if(updateTimestamp.after(content.getModifiedDate())){
                    this.content = entity1;
                }
            }
        }


    }
    public void purgeContent(List<Md2Entity> deleted){
        if ((deleted != null) && (deleted.isEmpty() == false)) {

            for(Md2Entity entity2 : deleted) {

                Timestamp contentTimestamp = entity2.getModifiedDate();

                if (contentTimestamp.after(syncTimestamp)) {
                    this.content = null;
                }
            }
        }
    }
    private void callAllHandlers(){
        for (String key: this.attributeChangedEventHandlers.keySet()){
            Md2OnAttributeChangedHandler handler = getOnAttributeChangedHandler(key);
            handler.onChange(key);
        }
    }
    @Override
    public void registerAttributeOnChangeHandler(String attribute, Md2OnAttributeChangedHandler onAttributeChangedHandler) {
        //observedAttributes.put(attribute, this.getValue(attribute));
        attributeChangedEventHandlers.put(attribute, onAttributeChangedHandler);
    }

    @Override
    public void unregisterAttributeOnChangeHandler(String attribute) {
        //this.observedAttributes.remove(attribute);
        this.attributeChangedEventHandlers.remove(attribute);
    }

    @Override
    public Md2OnAttributeChangedHandler getOnAttributeChangedHandler(String attribute) {
        return this.attributeChangedEventHandlers.get(attribute);
    }

    @Override
    public Md2Type getValue(String attribute) {
        if (content != null) {
            return this.content.get(attribute);
        }
        return null;
    }

    @Override
    public void setValue(String attribute, Md2Type value) {
        if (content == null) {
            return;
        }

        // set only if value is different to current value
        if ((this.getValue(attribute) == null && value != null) || !this.getValue(attribute).equals(value)) {
            this.content.set(attribute, value);
            Md2OnAttributeChangedHandler handler = this.attributeChangedEventHandlers.get(attribute);
            if (handler != null) {
                handler.onChange(attribute);
            }
        }
    }

    @Override
    public void reset() {
        newEntity();
    }

    @Override
    public void load() {
//        if (content == null | md2DataStore == null)
//            return;
//
//        // case: entity has id
//        if (this.content.getId() > 0) {
//            existsInDataStore = true;
//            internalId = this.content.getId();
//            return;
//        }
//
//        // case: entity has no id
//       // long id = md2DataStore.getInternalId(this.content);
//        long id = -1;
//        if (id == -1) {
//            existsInDataStore = false;
//            internalId = -1;
//        } else {
//            existsInDataStore = true;
//            internalId = id;
//            this.content.setId(id);
//
//        }
        this.syncTimestamp = new Timestamp(System.currentTimeMillis());
        md2DataStore.query(this.filter);
    }

    @Override
    public void save() {
        if (content == null || md2DataStore == null)
            return;
        if (content.getId()>0)
            md2DataStore.put(content.getId(), this.content);
        else {
            md2DataStore.put(this.content);
        }
        this.backup = (Md2Entity) content.clone();
    }
    @Override
    public void update(){
        Timestamp oldStamp = syncTimestamp;
        this.syncTimestamp = new Timestamp(System.currentTimeMillis());
//if(!(this.md2DataStore instanceof AbstractMd2OrmLiteDatastore)){
        md2DataStore.query(this.filter, oldStamp);
    //}

    }
    @Override
    public void remove() {
        if (content == null || md2DataStore == null) return;

        md2DataStore.remove(internalId, content.getClass());

    }

    public void newEntity(){
        //konkrete Implementierung im Generator
    }


    public void notfiyOnChangeHandlers(){
        Collection<Md2OnAttributeChangedHandler> coll = attributeChangedEventHandlers.values();
        for(Md2OnAttributeChangedHandler c : coll){
            c.execute();
        }
    }

    public void notifyChangeHandler(String name){
        Md2OnAttributeChangedHandler handler = this.attributeChangedEventHandlers.get(name);
        if (handler != null) {
            handler.onChange(name);
        }
    }

    @Override
    public void resetLocal(){
        newEntity();
    }

}
