package com.pha.pipoca.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.view.MovieItemView;
import com.pha.pipoca.view.MovieItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Collections;
import java.util.Comparator;
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

    public void sortByPopularity()
    {
        Collections.sort(items, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.popularity < o2.popularity ? 1 : -1;
            }
        });
        notifyDataSetChanged();
    }

    public void sortByRating(){
        Collections.sort(items, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.voteAvarage < o2.voteAvarage ? 1 : -1;
            }
        });
        notifyDataSetChanged();
    }
}
