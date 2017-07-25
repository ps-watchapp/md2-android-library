package de.uni_muenster.wi.md2library.model.dataStore.implementation;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.uni_muenster.wi.md2library.model.dataStore.Filter;
import de.uni_muenster.wi.md2library.model.dataStore.SqlUtils;
import de.uni_muenster.wi.md2library.model.type.interfaces.Md2Entity;

/**
 * Created by Felix on 22.05.2017.
 */

public class Md2RemoteStore<T extends Md2Entity> extends AbstractMd2DataStore {
    private URL baseURL;
    final Class<T> typeParameterClass;




    public Md2RemoteStore(URL uRL,Class<T> typeParameterClass){
        this.baseURL=uRL;
        this.typeParameterClass = typeParameterClass;

    }

    /**
     * Execute query in data store.
     *
     * @param filter the query
     * @return the md 2 entity
     */
    @Override
    public void query(Filter filter) {
        String url = baseURL+"/"+typeParameterClass.getSimpleName().toLowerCase()+"?filter="+ SqlUtils.filterToSqlStatement(filter);
        //String url = "http://watchapp.uni-muenster.de:8080/citizenApp.backend/service/address/1";
        System.out.println("DO Query:");
        System.out.println(url);
// Request a string response
        JSONArray jsonArray = new JSONArray();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,url,jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        // Result handling
                        System.out.println("Returned LOAD:"+response.toString());
                        if (null == response.toString()) {
                            //return null;
                        }else {
                            Gson gson = new Gson();
                            System.out.println(response.toString());
                            List<T> mD2List = new ArrayList<T>();
                            for (int i = 0; i < response.length(); i++) {
                                try{
                                mD2List.add(i,gson.fromJson(response.getString(i), typeParameterClass));
                                }catch (Exception e){

                                }
                            }
                            //Md2Entity md2Object = (Md2Entity) new Gson().fromJson(response.toString(),dataType);
                            System.out.println(mD2List.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                System.out.println(error.getMessage());
                error.printStackTrace();

            }

        });
        VolleyQueue.getInstance(null).getRequestQueue().add(arrayRequest);

    }
    /**
     * Try to get internal id. I.e., look for entity in database that has the same values as the entity.
     *
     * @param entity the entity
     * @return the internal id
     */
    @Override
    public void getInternalId(Md2Entity entity) {

    }



    /**
     * Load entity.
     *
     * @param id       the id
     * @param dataType the data type
     * @return the hash map
     */

    public void load(long id, final Class dataType) {
        String url = baseURL+"/"+dataType.getSimpleName().toLowerCase()+"/"+id;
        //String url = "http://watchapp.uni-muenster.de:8080/citizenApp.backend/service/address/1";
        System.out.println("DO LOAD:");
        System.out.println(url);
// Request a string response
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,url,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Result handling
                        System.out.println("Returned LOAD:"+response.toString());
                        Md2Entity md2Object = (Md2Entity) new Gson().fromJson(response.toString(),dataType);
                        System.out.println(md2Object.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                System.out.println(error.getMessage());
                error.printStackTrace();

            }
            
        });
       VolleyQueue.getInstance(null).getRequestQueue().add(objectRequest);


    }
    /**
     * Put long.
     *
     * @param md2Entity the md 2 entity
     * @return the long
     */
    @Override
    public void put(Md2Entity md2Entity) {
        this.put(0,md2Entity);
    }
    /**
     * Put entity.
     *
     * @param id        the id
     * @param md2Entity the md 2 entity
     * @return the long
     */
    @Override
    public void put(long id, Md2Entity md2Entity) {
        md2Entity.setId(id);
        System.out.println("DO PUT");
        List<Md2Entity> list = new ArrayList<Md2Entity>();
        list.add(0,md2Entity);
        String json = new Gson().toJson(md2Entity);
        System.out.println(json);
        json = new Gson().toJson(list);
        System.out.println(json);
// Request a string response
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        //URL url;
        String url = baseURL+"/"+md2Entity.getClass().getSimpleName().toLowerCase();
        System.out.println(url);
        try{
            //url = new URL("http://watchapp.uni-muenster.de:8080/citizenApp.backend/service/complaint");
            // jsonObject = new JSONObject(json);
            jsonArray = new JSONArray(json);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,url,jsonArray,         new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        VolleyLog.v("Response:%n %s", response.toString(4));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", new String(error.networkResponse.data));
                    System.out.println(error.getMessage());
                    error.printStackTrace();
                }
            });
            // JsonObjectRequest stringRequest = new StringRequest(Request.Method.POST, url,json,

            VolleyQueue.getInstance(null).getRequestQueue().add(jsonArrayRequest);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    /**
     * Remove entity.
     *
     * @param id        the id
     * @param md2Entity the md 2 entity
     */
    @Override
    public void remove(long id, Class md2Entity) {
        String url = baseURL+"/"+md2Entity.getSimpleName().toLowerCase()+"?id="+id;
        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        System.out.println(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error.

                    }
                }
        );
        VolleyQueue.getInstance(null).getRequestQueue().add(dr);
    }
}
