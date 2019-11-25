package br.com.ciandt.application.fellini.ui.abstractactivity;

import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class AbstractActivity extends AppCompatActivity {

    /**
     * Sets the Window of App with no Limits
     */
    protected void enableWindowNoLimits() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

}
