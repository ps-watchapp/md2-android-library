package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;
import de.uni_muenster.wi.md2library.model.dataStore.interfaces.Md2DataStore;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.controller.action.implementation.WhereCondition;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

public abstract class AbstractMd2MultiContentProvider implements Md2MultiContentProvider{


private Collection<Md2Entity> entities;

private String key;
private Md2DataStore dataStore;
	private int currentIndex;


	public void setCurrentIndex(int i){
		this.currentIndex = i;
	}

	public int getCurrentIndex(){
		return currentIndex;
	}

public AbstractMd2MultiContentProvider(String key) {
	super();
	this.entities=new ArrayList<Md2Entity>();
	this.setKey(key);
	currentIndex = 0;
}

public AbstractMd2MultiContentProvider(String key, Md2DataStore store){
super();
	this.entities=new ArrayList<Md2Entity>();
	this.key=key;
	this.dataStore=store;
	currentIndex = 0;
}

public void add( Md2Entity entity ){
entities.add(entity);
}


public void addAll(List<Md2Entity> list){entities.addAll(list);}

public void remove(WhereCondition conditions){

}

//TODO Merge
//public void remove(int i){
//	((ArrayList<Md2Entity>)entities).remove(i);
//}

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

	public ArrayList<Md2Entity> getContentsList(){
		return ((ArrayList<Md2Entity>) entities);
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

	public void remove(){}

	public void remove(int i){
		((ArrayList<Md2Entity>)entities).remove(i);
	}

	public Md2Type getValue(int entityIndex, String attribute){
		return (((ArrayList<Md2Entity>) entities).get(entityIndex).get(attribute));
	};
	public abstract void setValue(int entityIndex, String name, Md2Type value);

}

