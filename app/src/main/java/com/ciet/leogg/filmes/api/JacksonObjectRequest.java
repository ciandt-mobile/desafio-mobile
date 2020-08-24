package com.ciet.leogg.filmes.api;

import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.*;

public class JacksonObjectRequest<T> extends Request<T> {

    private final Response.Listener<T> listener;
    private final Class<?> modelClass;
    private final ObjectMapper mapper;

    public JacksonObjectRequest(String url, Response.Listener<T> listener, Class<?> modelClass){
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
            T mappedObject = mapper.readValue(response.data,mapper.getTypeFactory().constructType(modelClass));
            return Response.success(mappedObject,HttpHeaderParser.parseCacheHeaders(response));
        }catch (Exception e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        Log.d("VOLLEY","Volley Error Parsed.",volleyError);
        return super.parseNetworkError(volleyError);
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/json");
        header.put("Accept","application/json");
        header.put("Connection","Keep-Alive");
        header.put("Keep-Alive","timeout=5, max=1000");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        header.put("Date", dateFormat.format(Calendar.getInstance().getTime()));
        return header;
    }
}
