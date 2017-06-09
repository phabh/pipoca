package com.pha.pipoca.view;

import android.content.Context;
import android.widget.RelativeLayout;

import com.pha.pipoca.R;
import com.pha.pipoca.model.movie.Movie;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by phabh_000 on 6/9/2017.
 */
@EViewGroup(R.layout.movie_list_item)
public class MovieItemView extends RelativeLayout {

    public MovieItemView(Context context) {
        super(context);
    }

    public void bind(Movie movie)
    {

    }
}
