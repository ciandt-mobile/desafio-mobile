package com.svmdev.appmovies.view.popular;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.svmdev.appmovies.R;
import com.svmdev.appmovies.data.model.Cast;
import com.svmdev.appmovies.help.Variables;
import com.svmdev.appmovies.view.adapter.MovieAdapter;
import com.svmdev.appmovies.view.details.DetailActivity;

import java.text.ParseException;

public class PopularFragment extends Fragment implements PopularInterface {

    private ListView popularList;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private PopularPresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        popularList = view.findViewById(R.id.popular_list);
        progressBar = view.findViewById(R.id.popular_progressbar_loading);
        mPresenter = new PopularPresenter(this);
        initData();

        return view;
    }

    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    public void initData(){
        if (Variables.popularList.isEmpty()) {
            mPresenter.loadList( 1);
        }
        this.setListAdapter();
    }

    public void setListAdapter(){
        adapter = new MovieAdapter(this.getContext(),Variables.popularList);
        popularList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        popularList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    loadDetais(position);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadDetais(int pos) throws ParseException {
        Bundle dicionario = new Bundle();
        dicionario.putInt("movie_id",Variables.popularList.get(pos).getId());
        insertPosterInCast(pos);
        Intent request = new Intent(getActivity(), DetailActivity.class);
        request.putExtras(dicionario);
        startActivityForResult(request, 1);
    }

    private void insertPosterInCast(int pos){
        Variables.movieCast.clear();
        Cast moviePoster = new Cast(Variables.popularList.get(pos).getPoster());
        Variables.movieCast.add(moviePoster);
    }


    @Override
    public Context context() {
        return this.getContext();
    }

    @Override
    public Activity activity() {
        return this.getActivity();
    }

    @Override
    public ListView listView() {
        return this.popularList;
    }

    @Override
    public MovieAdapter adapter() {
        return this.adapter;
    }

    @Override
    public ProgressBar progress() {
        return this.progressBar;
    }

}
