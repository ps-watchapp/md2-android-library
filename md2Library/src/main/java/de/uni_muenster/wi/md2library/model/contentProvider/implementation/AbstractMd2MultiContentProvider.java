package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.Filter;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

public abstract class AbstractMd2MultiContentProvider implements Md2MultiContentProvider {


    private Collection<Md2Entity> entities;

    private String key;
    private Md2DataStore dataStore;
    private int currentIndex;
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
        this.entities.clear();
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
            this.dataStore.put(entity);
        }
    }


    public void saveAsManyRel(Md2Entity parentEntity) {

    }

    public void load() {

    }

    public void load(Filter condition) {

    }

    public void remove() {
    }

    public void remove(int i) {
        ((ArrayList<Md2Entity>) entities).remove(i);
        notifyAllAdapters();
    }

    public Md2Type getValue(int entityIndex, String attribute) {
        return (((ArrayList<Md2Entity>) entities).get(entityIndex).get(attribute));
    }

    ;

    public abstract void setValue(int entityIndex, String name, Md2Type value);

}

