package com.ciet.leogg.filmes.api;

import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.List;

public class JacksonListRequest<T> extends Request<T> {

    private final Response.Listener<T> listener;
    private final Class<?> modelClass;
    private final ObjectMapper mapper;

    public JacksonListRequest(String url, Response.Listener<T> listener, Class<?> modelClass){
        super(Method.GET, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY","Volley Error on Jackson List Request.",error);
            }
        });
        this.listener = listener;
        this.modelClass = modelClass;
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            JsonNode node = mapper.readTree(response.data);
            JsonNode responseNode = mapper.createArrayNode();
            //Get the first array node inside json object
            boolean notArray = true;
            for(Iterator<JsonNode> i = node.iterator(); i.hasNext()&&notArray;){
                responseNode = i.next();
                notArray = !responseNode.isArray();
            }
            //Map it to a list of the desired model, unmarshalling all at once.
            //This can be used to get a list of Movies, Cast and Genre, which is enough for the project.
            T mappedList = mapper.readValue(mapper.treeAsTokens(responseNode),mapper.getTypeFactory()
                    .constructCollectionType(List.class,modelClass));
            return Response.success(mappedList,HttpHeaderParser.parseCacheHeaders(response));
        }catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
}
