package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.controller.action.implementation.WhereCondition;

public abstract class AbstractMd2MultiContentProvider implements Md2MultiContentProvider{


private Collection<Md2Entity> entities;

private String key;
private Md2DataStore dataStore;

public AbstractMd2MultiContentProvider(String key) {
	super();
	//this.entities=new ArrayList<Md2Entity>();
	this.setKey(key);
}

public AbstractMd2MultiContentProvider(String key, Md2DataStore store){
super();
	this.key=key;
	this.dataStore=store;
}

public void add( Md2Entity entity ){
entities.add(entity);
}


public void addAll(List<Md2Entity> list){entities.addAll(list);}

public void remove(WhereCondition conditions){

}

public void removeAll(){
this.entities.clear();
}

public Md2Entity evaluateWhereCondition(WhereCondition condition){

	return null;
}



public Md2Entity get(WhereCondition conditions){
return evaluateWhereCondition(conditions);

}

public Collection<Md2Entity> getContents(){
	return entities;

}

public String getKey() {
	return key;
}



public void setKey(String key) {
	this.key = key;
}



public void save(){
	for (Md2Entity entity: entities) {
	this.dataStore.put(entity);
	}
}



public void saveAsManyRel(Md2Entity parentEntity){

}

public void load(){

}

	public void load(WhereCondition condition){

	}


	public void remove(long id, Md2Entity entity){
	dataStore.remove(id,entity);
	}



	public void remove(){}
}

