package de.uni_muenster.wi.md2library.model.type.implementation;

import java.util.List;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Type;

/**
 * Created by felix_000 on 07.05.2017.
 */

public class Md2List<T extends Md2Type> implements Md2Type {

    List<T> contents;


    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }


    public Md2List(List<T> contents) {
        this.contents = contents;
    }

    @Override
    public Md2Type clone() {
        return null;
    }

    @Override
    public Md2String getString() {
        return null;
    }

    @Override
    public boolean equals(Md2Type value) {
        return false;
    }



    public List<T> getPlatformValue(){
       return contents;
    }
}
