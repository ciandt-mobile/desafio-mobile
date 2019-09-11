package com.rangeldor.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private List<Movie.Result> results;
    private Context context;
    private static ClickListener clickListener;
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public RecyclerViewHomeAdapter(List<Movie.Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_recycler_home,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapter.RecyclerViewHolder viewHolder, int i) {

        String strMovieThumb = IMAGE_URL + results.get(i).getPosterPath ();

        Picasso.get().load(strMovieThumb).placeholder(R.drawable.ic_circle).into(viewHolder.homeThumb);

        String strMovieName = results.get(i).getTitle ();
       // viewHolder.categoryName.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.homeThumb)
        ImageView homeThumb;
      //  @BindView(R.id.categoryName)
      //  TextView categoryName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view , int position);
    }
}
