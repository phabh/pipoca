package com.pha.pipoca.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by phabh_000 on 6/9/2017.
 */

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<T>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    // additional methods to manipulate the items
    public void addItems(List<T> newItens) {
        items.addAll(newItens);
        notifyDataSetChanged();
    }

    public List<T> getItems()
    {
        return items;
    }

}