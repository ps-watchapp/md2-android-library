package de.uni_muenster.wi.md2library.model.contentProvider.interfaces;

import java.util.List;

import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by felix_000 on 03.06.2017.
 */

public interface IContentProvider {
    void overwriteContent(List<Md2Entity> content);
    /**
     * Overwrite.
     */
    void update();


    void load();

    /**
     * Save.
     */
    void save();

    /**
     * Remove.
     */
    void remove();

    public void updateContent(List<Md2Entity> updates);

    public void purgeContent(List<Md2Entity> updates);

    void reset();

    void resetLocal();

}
