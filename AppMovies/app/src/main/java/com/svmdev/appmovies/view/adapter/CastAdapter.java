package com.svmdev.appmovies.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.svmdev.appmovies.R;
import com.svmdev.appmovies.data.model.Cast;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {

    ArrayList<Cast> castList;
    Context context;


    public CastAdapter(ArrayList<Cast> horizontalList, Context context) {
        this.castList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView persoName;
        TextView actorName;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.adapt_cast_pic);
            persoName = view.findViewById(R.id.adapt_cast_char);
            actorName = view.findViewById(R.id.adapt_cast_actor);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cast, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.persoName.setText(castList.get(position).getCharacter());
        if(castList.get(position).getCharacter() == null){
            holder.persoName.setBackgroundResource(R.drawable.bb_t);
        }

        holder.actorName.setText(castList.get(position).getName());
        if(castList.get(position).getName() == null){
            holder.actorName.setBackgroundResource(R.drawable.bb_t);
        }

        if (!castList.get(position).getProfilePath().isEmpty()) {
            Picasso.with(context).load(castList.get(position).getProfilePath())
                    .noFade()
                    .resize(256, 350)
                    .error(R.drawable.progressbar)
                    .into(holder.image, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
        } else {
            switch (castList.get(position).getGender()) {
                case 1:
                    holder.image.setImageResource(R.drawable.female);
                    break;
                case 2:
                    holder.image.setImageResource(R.drawable.male);
                    break;
                default:
                    holder.image.setImageResource(R.drawable.udf);
            }
        }

    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

}