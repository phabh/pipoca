package com.pha.pipoca.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pha.pipoca.R;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.model.api.ImagesPathBuilder;
import com.pha.pipoca.model.api.Movie;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by phabh_000 on 6/9/2017.
 */
@EViewGroup(R.layout.movie_list_item)
public class MovieItemView extends RelativeLayout {

    @ViewById(R.id.movieButton)
    ImageView imageButton;

    public MovieItemView(Context context) {
        super(context);
    }

    public void bind(Movie movie)
    {
        String posterImage = ImagesPathBuilder.getPoster(MovieDBService.getInstance().getConfiguration(), movie, 2);

        Picasso.with(this.getContext()).setIndicatorsEnabled(true);

        Picasso.with(this.getContext())
                .load(posterImage)
                .into(imageButton);
    }
}
