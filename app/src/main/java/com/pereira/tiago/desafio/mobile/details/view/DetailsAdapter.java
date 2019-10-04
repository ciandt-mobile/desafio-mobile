package com.pereira.tiago.desafio.mobile.details.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.base.Config;
import com.pereira.tiago.desafio.mobile.databasemodels.CastMovie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsHolder> {

    private List<CastMovie> mCastMovies;

    public DetailsAdapter(List<CastMovie> castMovies) {
        this.mCastMovies = castMovies;
    }

    @NonNull
    @Override
    public DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsHolder holder, int position) {

        CastMovie castMovie = mCastMovies.get(position);

        holder.pbImg.setVisibility(View.VISIBLE);

        Picasso.get()
                .load(Config.BASE_URL_IMG + castMovie.getProfilePath())
                .error(R.drawable.ic_error)
                .into(holder.imgCast, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (holder.pbImg != null){
                            holder.pbImg.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        if (holder.pbImg != null){
                            holder.pbImg.setVisibility(View.GONE);
                        }
                    }
                });

        holder.txtName.setText(castMovie.getName());
        holder.txtCharacter.setText(castMovie.getCharacter());

    }

    @Override
    public int getItemCount() {
        return mCastMovies.size();
    }
}
