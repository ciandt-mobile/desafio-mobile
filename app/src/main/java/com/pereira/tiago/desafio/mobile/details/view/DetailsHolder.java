package com.pereira.tiago.desafio.mobile.details.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pereira.tiago.desafio.mobile.R;

public class DetailsHolder extends RecyclerView.ViewHolder {

    ProgressBar pbImg;
    ImageView imgCast;
    TextView txtName, txtCharacter;

    public DetailsHolder(@NonNull View itemView) {
        super(itemView);

        pbImg = itemView.findViewById(R.id.pbImg);
        imgCast = itemView.findViewById(R.id.imgCast);
        txtName = itemView.findViewById(R.id.txtName);
        txtCharacter = itemView.findViewById(R.id.txtCharacter);

    }
}
