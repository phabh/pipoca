package com.pha.pipoca.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pha.pipoca.model.movie.Movie;
import com.pha.pipoca.view.MovieItemView;
import com.pha.pipoca.view.MovieItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by phabh_000 on 6/9/2017.
 */

@EBean
public class MovieAdaper extends RecyclerViewAdapterBase<Movie, MovieItemView> {

    @RootContext
    Context context;

    @Override
    protected MovieItemView onCreateItemView(ViewGroup parent, int viewType) {
        return MovieItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<MovieItemView> viewHolder, int position) {
        MovieItemView view = viewHolder.getView();
        Movie movie = items.get(position);

        view.bind(movie);
    }

}
