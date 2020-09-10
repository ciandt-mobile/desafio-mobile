package com.ciet.leogg.filmes.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.volley.toolbox.NetworkImageView;
import com.ciet.leogg.filmes.R;
import com.ciet.leogg.filmes.api.AppRequestQueue;

public class MyNetworkImageView extends NetworkImageView {

    public MyNetworkImageView(Context context) {
        super(context);
    }

    public MyNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MyNetworkImageView, defStyle, 0);

        int defaultImageResId = attributes.getResourceId(R.styleable.MyNetworkImageView_defaultImageResId, 0);
        if (defaultImageResId > 0) {
            setDefaultImageResId(defaultImageResId);
        }

        int errorImageResId = attributes.getResourceId(R.styleable.MyNetworkImageView_errorImageResId, 0);
        if (errorImageResId > 0) {
            setErrorImageResId(errorImageResId);
        }

        String imageUrl = attributes.getString(R.styleable.MyNetworkImageView_imageUrl);
        if(imageUrl != null){
            setImageUrl(imageUrl);
        }
    }

    public void setImageUrl(String url) {
        super.setImageUrl(url, AppRequestQueue.getInstance().getImageLoader());
    }
}
