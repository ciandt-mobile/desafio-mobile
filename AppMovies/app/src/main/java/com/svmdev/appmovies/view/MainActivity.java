package com.svmdev.appmovies.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import com.svmdev.appmovies.R;
import com.svmdev.appmovies.help.Variables;
import com.svmdev.appmovies.view.popular.PopularFragment;
import com.svmdev.appmovies.view.upcomming.UpcommingFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    public static Fragment popularFragment;
    public static Fragment upcommingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Variables.init();

        navigationView = findViewById(R.id.menu_navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        popularFragment = PopularFragment.newInstance();
        openFragment(popularFragment);
        openFragment(popularFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    if(popularFragment == null){
                        popularFragment = PopularFragment.newInstance();
                    }
                    openFragment(popularFragment);
                    break;
                case R.id.navigation_upcomming:
                    if(upcommingFragment == null){
                        upcommingFragment = UpcommingFragment.newInstance();
                    }
                    openFragment(upcommingFragment);
                    break;
            }
            return true;
        }
    };

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.menu_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onActivityResult(int codigo, int resultado, Intent dados) {
        try {
            if(resultado == Activity.RESULT_OK){


            }
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(), "Atenção: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
