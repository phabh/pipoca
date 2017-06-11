package com.pha.pipoca.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pha.pipoca.R;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.model.api.ApiConstants;
import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.ImagesPathBuilder;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.MovieTrailer;
import com.pha.pipoca.model.api.ResponseMovieTrailer;
import com.pha.pipoca.view.TrailerItemView;
import com.pha.pipoca.view.TrailerItemView_;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    @ViewById(R.id.release_dates)
    TextView releaseDates;

    @ViewById(R.id.vote_average)
    RatingBar voteAverage;

    @ViewById(R.id.trailers_loading)
    ProgressBar trailersLoading;

    @ViewById(R.id.trailer_container)
    LinearLayout trailerContainer;

    private Configuration configuration;
    private String posterPath;


    @AfterInject
    public void afterInject()
    {
        configuration = MovieDBService.getInstance().getConfiguration();
        posterPath = ImagesPathBuilder.getPoster(configuration, movie, 1);

    }

    @Background
    void callApi() {
        ResponseMovieTrailer responseMovieTrailer = null;
        try {
            responseMovieTrailer = MovieDBService.getInstance()
                    .getClient()
                    .getTrailers( movie.id, ApiConstants.API_KEY, null )
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showTrailers(responseMovieTrailer.results);
    }

    @UiThread
    void showTrailers(MovieTrailer[] trailers) {
        for (MovieTrailer trailer : trailers) {
            TrailerItemView trailerItemView = TrailerItemView_.build(getContext());
            trailerItemView.bind(trailer);
            trailerContainer.addView(trailerItemView);
        }
        trailersLoading.setVisibility(View.INVISIBLE);
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
        trailerContainer.removeAllViews();
        voteAverage.setNumStars(10);
        voteAverage.setMax(10);
        voteAverage.setRating((float)movie.voteAvarage);

        releaseDates.setText(movie.releaseDate);

        callApi();
    }

}
