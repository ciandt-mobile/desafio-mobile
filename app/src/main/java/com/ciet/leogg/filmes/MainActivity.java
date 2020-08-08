package com.ciet.leogg.filmes;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.api.AppRequestQueue;
import com.ciet.leogg.filmes.view.*;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                        .add(R.id.activity_frame, HomeFragment.newInstance())
                        .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.navigation_popular:{
                replaceFragment(PopularFragment.newInstance());
                break;
            }
            case R.id.navigation_upcoming:{
                replaceFragment(UpcomingFragment.newInstance());
                break;
            }
        }
        return true;
    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.activity_frame, fragment)
            .addToBackStack(null)
            .commit();
    }
}
