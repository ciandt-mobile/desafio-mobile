package br.com.ciandt.application.fellini.ui.datagroups;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import br.com.ciandt.application.fellini.R;

public abstract class GeneralAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private List<T> items;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public GeneralAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return setViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        onBindData(viewHolder, items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindData(RecyclerView.ViewHolder viewHolder, T item);

}
