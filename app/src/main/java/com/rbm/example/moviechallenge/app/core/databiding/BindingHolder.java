package com.rbm.example.moviechallenge.app.core.databiding;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T binding;

    public BindingHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
