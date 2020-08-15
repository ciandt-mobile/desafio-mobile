package com.ciet.leogg.filmes;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navbar);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_container);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        AppRequestQueue.getInstance().addToRequestQueue(new StringRequest(Request.Method.GET,"http://www.google.com/",new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.d("ColdStart","OK");
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ColdStart","Error",error);
            }
        }));
    }
}
