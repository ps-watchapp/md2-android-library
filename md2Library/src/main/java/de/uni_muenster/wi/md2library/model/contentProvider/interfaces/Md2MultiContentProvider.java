package de.uni_muenster.wi.md2library.model.contentProvider.interfaces;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

/**
 * Created by felix_000 on 07.05.2017.
 */

public interface Md2MultiContentProvider  extends IContentProvider{

public void addAdapter(RecyclerView.Adapter adapter, String key);

 public void notifyAllAdapters();

 public Collection<Md2Entity> getContents();

public void setCurrentIndex(int i);

 public ArrayList<Md2Entity> getContentsList();

 public  Md2Type getValue(int entityIndex, String attribute);
 public  void setValue(int entityIndex, String name, Md2Type value);
 public void setValueForAll(String name, Md2Type value);
public void reset();

//TODO: Merge
 //public int getCurrentIndex();
 //public void setCurrentIndex(int i);

 //public ArrayList<Md2Entity> getContentsList();


}
