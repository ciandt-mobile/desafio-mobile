package com.ciet.leogg.filmes.api;

import android.graphics.Bitmap;
import androidx.collection.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.ciet.leogg.filmes.App;

public class AppRequestQueue {
    private static AppRequestQueue instance;
    private final RequestQueue requestQueue;
    private final ImageLoader imageLoader;

    private AppRequestQueue() {
        requestQueue = Volley.newRequestQueue(App.instance().getApplicationContext());
        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized AppRequestQueue getInstance(){
        if(instance == null){
            instance = new AppRequestQueue();
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        requestQueue.add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
