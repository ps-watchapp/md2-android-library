package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Felix on 23.05.2017.
 */

public class VolleyQueue extends Application {
    private static  VolleyQueue ourInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    public static synchronized VolleyQueue getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new VolleyQueue(context);
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    private VolleyQueue(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }
}
