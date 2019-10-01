package com.pereira.tiago.desafio.mobile.main.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pereira.tiago.desafio.mobile.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainHolder extends RecyclerView.ViewHolder {

    ImageView imgPoster;
    TextView txtName;
    TextView txtDate;

    public MainHolder(@NonNull View itemView) {
        super(itemView);

        imgPoster = itemView.findViewById(R.id.imgPoster);
        txtName = itemView.findViewById(R.id.txtName);
        txtDate = itemView.findViewById(R.id.txtDate);
    }
}
