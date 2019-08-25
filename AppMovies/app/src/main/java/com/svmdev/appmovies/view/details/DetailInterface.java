package com.svmdev.appmovies.view.details;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public interface DetailInterface {

    Context context();
    Activity activity();
    ImageView image();
    TextView title();
    TextView date();
    TextView duration();
    TextView genres();
    TextView overview();
    ProgressBar progress();

}
