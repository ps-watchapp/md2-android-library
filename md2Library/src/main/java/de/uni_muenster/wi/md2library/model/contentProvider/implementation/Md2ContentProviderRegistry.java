package de.uni_muenster.wi.md2library.model.contentProvider.implementation;

import java.util.HashMap;
import java.util.Map;

import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2ContentProvider;
import de.uni_muenster.wi.md2library.model.contentProvider.interfaces.Md2MultiContentProvider;

/**
 * Content provider registry is used to store references to concrete content providers
 * <p/>
 * Created on 12/08/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public class Md2ContentProviderRegistry {
    private static Md2ContentProviderRegistry instance;
    /**
     * The Content providers.
     */
    Map<String, Md2ContentProvider> contentProviders;
    Map<String, Md2MultiContentProvider> multiContentProvider;

    private Md2ContentProviderRegistry() {
        contentProviders = new HashMap<>();
        multiContentProvider = new HashMap<>();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized Md2ContentProviderRegistry getInstance() {
        if (Md2ContentProviderRegistry.instance == null) {
            Md2ContentProviderRegistry.instance = new Md2ContentProviderRegistry();
        }
        return Md2ContentProviderRegistry.instance;
    }

    /**
     * Add.
     *
     * @param key             the key
     * @param contentProvider the content provider
     */
    public void add(String key, Md2ContentProvider contentProvider) {
        contentProviders.put(key, contentProvider);
    }

    /**
     * Gets content provider.
     *
     * @param key the key
     * @return the content provider
     */
    public Md2MultiContentProvider getContentMultiProvider(String key) {
        return multiContentProvider.get(key);
    }



    public void addMultiContentProvider(String key, Md2MultiContentProvider contentProvider) {
        multiContentProvider.put(key, contentProvider);
    }

    /**
     * Gets content provider.
     *
     * @param key the key
     * @return the content provider
     */
    public Md2ContentProvider getContentProvider(String key) {
        return contentProviders.get(key);
    }


    public Map<String, Md2ContentProvider> getContentProviders() {
        return contentProviders;
    }

    public void setContentProviders(Map<String, Md2ContentProvider> contentProviders) {
        this.contentProviders = contentProviders;
    }

    public Map<String, Md2MultiContentProvider> getMultiContentProvider() {
        return multiContentProvider;
    }

    public void setMultiContentProvider(Map<String, Md2MultiContentProvider> multiContentProvider) {
        this.multiContentProvider = multiContentProvider;
    }
}
