package com.ciet.leogg.filmes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.ciet.leogg.filmes.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setLifecycleOwner(this);
        bottomNavigationView = findViewById(R.id.navbar);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment_container);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}
