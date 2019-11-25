package br.com.ciandt.application.fellini.ui.activity;

import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.List;

import br.com.ciandt.application.fellini.R;
import br.com.ciandt.application.fellini.domain.movie.Movie;
import br.com.ciandt.application.fellini.service.MovieRepository;
import br.com.ciandt.application.fellini.service.callbacks.OnGettingMoviesCallback;
import br.com.ciandt.application.fellini.ui.datagroups.OnClickListener;
import br.com.ciandt.application.fellini.ui.datagroups.viewpager.SectionViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainactivity";

    private ViewPager mainViewPager;
    private TabLayout mainTabLayout;
    private SectionViewPagerAdapter sectionViewPagerAdapter;

    private MovieRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = MovieRepository.getInstance();

        mainViewPager = findViewById(R.id.main_viewpager);
        mainTabLayout = findViewById(R.id.main_tablayout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainTabLayout.setupWithViewPager(mainViewPager);
        sectionViewPagerAdapter = new SectionViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(sectionViewPagerAdapter);
        mainViewPager.setOffscreenPageLimit(4);

    }


}
