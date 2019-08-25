package com.svmdev.appmovies.help;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.svmdev.appmovies.R;

public class ImageLoader {

    public static void load(Context context, String imageUrl, ImageView picture){
        Picasso.with(context).load(imageUrl)
                .placeholder(R.drawable.download)
                .error(R.drawable.download)
                .into(picture, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

}
