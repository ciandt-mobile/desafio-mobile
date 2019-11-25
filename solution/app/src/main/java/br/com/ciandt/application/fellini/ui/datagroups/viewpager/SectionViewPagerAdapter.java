package br.com.ciandt.application.fellini.ui.datagroups.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import br.com.ciandt.application.fellini.service.MovieRepository;
import br.com.ciandt.application.fellini.ui.fragment.MovieSectionFragment;

public class SectionViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "sectionfragment";

    private Bundle args;
    private MovieSectionFragment fragment;

    public SectionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        args = new Bundle();
        fragment = new MovieSectionFragment();

        switch (i) {

            case 0:
                args.putString("sort", MovieRepository.POPULAR);
                fragment.setArguments(args);
                return fragment;

            case 1:
                args.putString("sort", MovieRepository.UPCOMING);
                fragment.setArguments(args);
                return fragment;

            case 2:
                args.putString("sort", MovieRepository.NOW_PLAYING);
                fragment.setArguments(args);
                return fragment;

            case 3:
                args.putString("sort", MovieRepository.TOP_RATED);
                fragment.setArguments(args);
                return fragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "Popular";

            case 1:
                return "Upcoming";

            case 2:
                return "Now Playing";

            case 3:
                return "Top Rated";

            default:
                return null;
        }
    }
}
