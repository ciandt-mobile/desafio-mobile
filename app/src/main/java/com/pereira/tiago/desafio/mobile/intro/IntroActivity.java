package com.pereira.tiago.desafio.mobile.intro;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.pereira.tiago.desafio.mobile.R;
import com.pereira.tiago.desafio.mobile.main.view.MainActivity;
import com.pereira.tiago.desafio.mobile.utils.Shared;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verifyIntroActivity();

        String[] neededPermissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET};

        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimaryDark)
                .buttonsColor(R.color.colorAccent)
                .image(R.drawable.ic_launcher_foreground)
                .title(getResources().getString(R.string.slide_1_title))
                .description(getResources().getString(R.string.slide_1_description))
                .build());

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryDark)
                        .buttonsColor(R.color.colorAccent)
                        .neededPermissions(neededPermissions)
                        .image(R.drawable.ic_launcher_foreground)
                        .title(getResources().getString(R.string.slide_2_title))
                        .description(getResources().getString(R.string.slide_2_description))
                        .build());

    }

    private void verifyIntroActivity(){
        if (Shared.getPrefStatus(getApplicationContext())){
            goToMain();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();

        Shared.setPrefStatus(getApplicationContext(), true);

        goToMain();
    }

    private void goToMain(){
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
