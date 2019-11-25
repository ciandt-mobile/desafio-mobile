package br.com.ciandt.application.fellini.ui.datagroups;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    public GenericViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T t);
}
