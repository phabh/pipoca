package com.pha.pipoca.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pha.pipoca.R;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.ImagesPathBuilder;
import com.pha.pipoca.model.api.Movie;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_movie_detail)
public class MovieDetailFragment extends Fragment {
    private static final String PARAM_MOVIE = "paramMovie";

    @FragmentArg(PARAM_MOVIE)
    Movie movie;

    @ViewById(R.id.movie_poster)
    ImageView moviePoster;

    @ViewById(R.id.movie_overview)
    TextView movieOverview;

    @ViewById(R.id.movie_name)
    TextView movieName;

    @ViewById(R.id.trailers_loading)
    ProgressBar trailersLoading;

    private Configuration configuration;
    private String posterPath;

    @AfterInject
    public void afterInject()
    {
        configuration = MovieDBService.getInstance().getConfiguration();
        posterPath = ImagesPathBuilder.getPoster(configuration, movie, 1);
    }

    @AfterViews
    public void afterViews(){
        bind();
    }

    public void bind()
    {
        Picasso.with(getContext()).setIndicatorsEnabled(true);
        Picasso.with(getContext()).load(posterPath).into(moviePoster);

        movieName.setText(movie.originalTitle);
        movieOverview.setText(movie.overview);
        trailersLoading.setVisibility(View.VISIBLE);

    }

}
