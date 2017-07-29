package de.uni_muenster.wi.md2library.model.contentProvider.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

/**
 * Created by felix_000 on 07.05.2017.
 */

public interface Md2MultiContentProvider  extends IContentProvider{


 public Collection<Md2Entity> getContents();

public void setCurrentIndex(int i);

 public ArrayList<Md2Entity> getContentsList();

 public  Md2Type getValue(int entityIndex, String attribute);
 public  void setValue(int entityIndex, String name, Md2Type value);

//TODO: Merge
 //public int getCurrentIndex();
 //public void setCurrentIndex(int i);

 //public ArrayList<Md2Entity> getContentsList();


}
