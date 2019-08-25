package com.svmdev.appmovies.view.upcomming;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.svmdev.appmovies.R;
import com.svmdev.appmovies.data.model.Cast;
import com.svmdev.appmovies.help.Variables;
import com.svmdev.appmovies.view.adapter.MovieAdapter;
import com.svmdev.appmovies.view.details.DetailActivity;

import java.text.ParseException;


public class UpcommingFragment extends Fragment implements UpcommingInterface {

    private ListView upcommingList;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private UpcommingPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcomming, container, false);

        upcommingList = view.findViewById(R.id.upcomming_list);
        progressBar = view.findViewById(R.id.upcomming_progressbar_loading);
        mPresenter = new UpcommingPresenter(this);

        initData();
        return view;
    }

    public void initData(){
        if (Variables.upcommingList.isEmpty()) {
            mPresenter.loadList(1);
        }
        this.setListAdapter();
    }

    public void setListAdapter(){
        adapter = new MovieAdapter(this.getContext(),Variables.upcommingList);
        upcommingList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        upcommingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        dicionario.putInt("movie_id",Variables.upcommingList.get(pos).getId());
        insertPosterInCast(pos);
        Intent request = new Intent(getActivity(), DetailActivity.class);
        request.putExtras(dicionario);
        startActivityForResult(request, 1);
    }

    private void insertPosterInCast(int pos){
        Variables.movieCast.clear();
        Cast moviePoster = new Cast(Variables.upcommingList.get(pos).getPoster());
        Variables.movieCast.add(moviePoster);
    }

    public static UpcommingFragment newInstance(){
        return new UpcommingFragment();
    }

    @Override
    public Context context() {
        return this.getContext();
    }

    @Override
    public ListView listView() {
        return this.upcommingList;
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