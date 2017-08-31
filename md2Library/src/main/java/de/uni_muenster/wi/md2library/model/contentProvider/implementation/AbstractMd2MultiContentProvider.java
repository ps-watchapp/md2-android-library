package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import android.support.v7.widget.RecyclerView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.Filter;
import de.uni_muenster.wi.md2library.model.dataStore.implementation.AbstractMd2OrmLiteDatastore;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

public abstract class AbstractMd2MultiContentProvider implements Md2MultiContentProvider {


    private Collection<Md2Entity> entities;
    protected Timestamp syncTimestamp;
    private String key;
    private Md2DataStore dataStore;
    private int currentIndex;
    protected Filter filter;
    private HashMap<String, RecyclerView.Adapter> adapters = new HashMap<String, RecyclerView.Adapter>();

    public void addAdapter(RecyclerView.Adapter adapter, String key){
        if(!adapters.containsKey(key)){
            adapters.put(key, adapter);
        } else {
            adapters.remove(key);
            adapters.put(key, adapter);
        }
    }

    public void notifyAllAdapters(){
        Collection<RecyclerView.Adapter> col = adapters.values();
        for (RecyclerView.Adapter rec : col) {
            rec.notifyDataSetChanged();
        }
    }

    public void setCurrentIndex(int i) {
        this.currentIndex = i;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public AbstractMd2MultiContentProvider(String key) {
        super();
        this.entities = new ArrayList<Md2Entity>();
        this.setKey(key);
        currentIndex = 0;
    }

    public AbstractMd2MultiContentProvider(String key, Md2DataStore store) {
        super();
        this.entities = new ArrayList<Md2Entity>();
        this.key = key;
        this.dataStore = store;
        store.setContentProvider(this);
        currentIndex = 0;
    }

    public void add(Md2Entity entity) {
        entities.add(entity);
        notifyAllAdapters();
    }


    public void addAll(List<Md2Entity> list) {
        entities.addAll(list);
        notifyAllAdapters();
    }

    public void remove(Filter conditions) {

    }

    //TODO Merge
    //public void remove(int i){
    //	((ArrayList<Md2Entity>)entities).remove(i);
    //}

    public void removeAll() {
        for(Md2Entity e : entities){
            this.dataStore.remove(e.getId(), e.getClass());
        }
        this.entities.clear();
        currentIndex = 0;
        notifyAllAdapters();
    }


    public Md2Entity get(Filter conditions) {
        return null;

    }

    public Collection<Md2Entity> getContents() {
        return entities;

    }

    public ArrayList<Md2Entity> getContentsList() {
        return ((ArrayList<Md2Entity>) entities);
    }

    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public void save() {
        for (Md2Entity entity : entities) {
            if (entity.getId()>0L) {
                this.dataStore.put(entity.getId(), entity);
            }else {
                this.dataStore.put(entity);
            }
        }
    }


    public void saveAsManyRel(Md2Entity parentEntity) {

    }

    public void load() {
        dataStore.query(this.filter);
    }


    public void load(Filter condition) {
            dataStore.query(condition);
    }


    public void remove() {
        dataStore.remove(((Md2Entity)((ArrayList)this.entities).get(currentIndex)).getId(),((ArrayList)this.entities).get(currentIndex).getClass() );
        ((ArrayList<Md2Entity>) entities).remove(currentIndex);
        currentIndex = 0;
        notifyAllAdapters();
    }

    public void remove(int i) {
        dataStore.remove(((Md2Entity)((ArrayList)this.entities).get(i)).getId(),((ArrayList)this.entities).get(i).getClass() );
        ((ArrayList<Md2Entity>) entities).remove(i);
        notifyAllAdapters();
    }

    public Md2Type getValue(int entityIndex, String attribute) {
        return (((ArrayList<Md2Entity>) entities).get(entityIndex).get(attribute));
    }

    ;

    public abstract void setValue(int entityIndex, String name, Md2Type value);

    public void setValueForAll(String name, Md2Type value){
        for(int i = 0; i < entities.size(); i++){
            setValue(i, name, value);
        }
    }

    @Override
    public void overwriteContent(List<Md2Entity> content){
        if ((content != null)&&(content.isEmpty()==false)) {
            this.entities = new ArrayList<>(content);
        }
       notifyAllAdapters();
    }

    @Override
    public void updateContent(List<Md2Entity> updates) {

        if ((updates != null)&&(updates.isEmpty() == false)) {

            for (Md2Entity entityUpdate : updates) {
                boolean found = false;
                for(int i =0;i<entities.size();i++){
                    if(((ArrayList<Md2Entity>) entities).get(i).getId()==0L){
                        if(entityUpdate.equals(((ArrayList<Md2Entity>) entities).get(i))){
                            ((ArrayList<Md2Entity>) entities).set(i,entityUpdate);
                            found=true;
                        }
                    }else{
                        if (((ArrayList<Md2Entity>) entities).get(i).getId()==entityUpdate.getId()){
                            ((ArrayList<Md2Entity>) entities).set(i,entityUpdate);
                            found=true;
                        }
                    }
                }
                if (!found)this.add(entityUpdate);
            }
            notifyAllAdapters();
        }
    }

    @Override
    public void purgeContent(List<Md2Entity> deleted){
        if ((deleted != null) && (deleted.isEmpty() == false)) {

            for(Md2Entity entityDeleted : deleted) {
                for(int i =0;i<entities.size();i++){
                    if((entityDeleted.equals(((ArrayList<Md2Entity>) entities).get(i)) &&entityDeleted.getModifiedDate().after(syncTimestamp))||(((ArrayList<Md2Entity>) entities).get(i).getId()==entityDeleted.getId()))  {
                        ((ArrayList<Md2Entity>) entities).remove(i);
                    }
                }

            }
            notifyAllAdapters();
        }
    }

    @Override
    public void update(){
        Timestamp oldStamp = syncTimestamp;
        this.syncTimestamp = new Timestamp(System.currentTimeMillis());

        if(this.filter == null){
            System.out.println("Filter null");
        }
        else {
           // if(!(this.dataStore instanceof AbstractMd2OrmLiteDatastore)) {
                dataStore.query(this.filter, oldStamp);
          //  }
            }

    }

    public void reset(){
        removeAll();
    }

    public void resetLocal(){
        entities.clear();
        notifyAllAdapters();
    }

}




