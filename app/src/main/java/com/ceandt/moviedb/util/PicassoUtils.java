package com.ceandt.moviedb.util;

import android.content.Context;
import android.widget.ImageView;

import com.ceandt.moviedb.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class PicassoUtils {

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    public static void loadImage(final Context context, final ImageView image, final String posterPath, final int width, final int height) {
        loadImage(context, image, posterPath, false, width, height);
    }

    public static void loadImage(final Context context, final ImageView image, final String posterPath) {
        loadImage(context, image, posterPath, false, 0, 0);
    }

    public static void loadImageWithCircleTransformation(final Context context, final ImageView image, final String posterPath) {
        loadImage(context, image, posterPath, true, 0, 0);
    }

    private static void loadImage(final Context context, final ImageView image, final String posterPath, final boolean circleTransformation, final int width, final int height) {
        RequestCreator creator = Picasso.with(context)
                .load(BASE_IMAGE_URL + posterPath)
                .placeholder(R.drawable.ic_image_black_24dp);
        if (width != 0 && height != 0) {
            creator.resize(width, height);
        }
        if (circleTransformation) {
            creator.transform(new CircleTransform());
        }
        creator.into(image);
    }
}