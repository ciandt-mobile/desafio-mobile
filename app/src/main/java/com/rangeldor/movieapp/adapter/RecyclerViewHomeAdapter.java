package com.rangeldor.movieapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rangeldor.movieapp.R;
import com.rangeldor.movieapp.api.MovieClient;
import com.rangeldor.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private List<Movie.Result> results;
    private Context context;
    private static ClickListener clickListener;

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

        String strMovieThumb = MovieClient.BASE_IMAGE_URL + results.get(i).getPosterPath ();
        Picasso.get().load(strMovieThumb).placeholder(R.drawable.ic_circle).into(viewHolder.homeThumb);

        String strMovieTitle = results.get(i).getTitle ();
        viewHolder.titleName.setText(strMovieTitle);

        String strMovieReleaseDate = results.get(i).getReleaseDate();

        @SuppressLint("SimpleDateFormat")
        String dataFormatada = getFormattedDate ( strMovieReleaseDate );

        viewHolder.releaseDate.setText(dataFormatada);
    }

    private String getFormattedDate(String strMovieReleaseDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataEntrada = null;
        try {
            dataEntrada = sdf.parse(strMovieReleaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(dataEntrada);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.homeThumb)
        ImageView homeThumb;

        @BindView(R.id.titleName)
        TextView titleName;

        @BindView(R.id.releaseDate)
        TextView releaseDate;

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
