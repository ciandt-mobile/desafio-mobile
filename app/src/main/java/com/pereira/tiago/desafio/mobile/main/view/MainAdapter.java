package com.pereira.tiago.desafio.mobile.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.base.BaseViewHolder;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.databasemodels.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Movie> movieList;

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public MainAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MainHolder(LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_movie, parent, false));

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == movieList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public void addItems(List<Movie> postItems) {
        movieList.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        movieList.add(new Movie());
        notifyItemInserted(movieList.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = movieList.size() - 1;
        Movie item = getItem(position);
        if (item != null) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        movieList.clear();
        notifyDataSetChanged();
    }

    Movie getItem(int position) {
        return movieList.get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        ImageView imgPoster;
        TextView txtName;
        TextView txtDate;
        ProgressBar pbImg;
        CardView card_view;

        ViewHolder(View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
            pbImg = itemView.findViewById(R.id.pbImg);
        }

        protected void clear() { }

        public void onBind(int position) {
            super.onBind(position);
            Movie item = movieList.get(position);

            txtName.setText(item.getTitle());

            String[] aux = item.getRelease_date().split("-");
            txtDate.setText(aux[2] + "/" + aux[1] + "/" + aux[0]);

            pbImg.setVisibility(View.VISIBLE);

            Picasso.get()
                .load(Config.BASE_URL_IMG + item.getPoster_path())
                .error(R.drawable.ic_launcher_background)
                .into(imgPoster, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (pbImg != null){
                            pbImg.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        if (pbImg != null){
                            pbImg.setVisibility(View.GONE);
                        }
                    }
                });

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ir para segunda activity
                }
            });
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() { }
    }
}
