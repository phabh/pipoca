package com.pha.pipoca.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pha.pipoca.R;
import com.pha.pipoca.adapters.MovieAdaper;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.fragments.MovieDetailFragment;
import com.pha.pipoca.fragments.MovieDetailFragment_;
import com.pha.pipoca.model.api.ApiConstants;
import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.ResponseMovies;
import com.pha.pipoca.utils.EndlessRecyclerViewScrollListener;
import com.pha.pipoca.utils.ItemClickSupport;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_order_movies)
public class MainActivity extends AppCompatActivity implements ItemClickSupport.OnItemClickListener {

    @ViewById(R.id.recyclerview_main_movies)
    RecyclerView recyclerView;

    @ViewById(R.id.fragment_container)
    FrameLayout frameLayout;

    @ViewById(R.id.movies_loading)
    ProgressBar moviesLoading;

    @Bean
    MovieAdaper movieAdaper;

    private ItemClickSupport itemClickSupport;
    private EndlessRecyclerViewScrollListener scrollListener;

    @AfterViews
    public void afterViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdaper);
        moviesLoading.setVisibility(View.VISIBLE);
        backgroundSetup(1);
        itemClickSupport = ItemClickSupport.addTo(recyclerView);
        itemClickSupport.setOnItemClickListener(this);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                moviesLoading.setVisibility(View.VISIBLE);
                backgroundSetup(page+1);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @UiThread
    public void updateData(List<Movie> newList)
    {
        if( newList.size() > 0 ) {
            movieAdaper.addItems(newList);
            movieAdaper.notifyDataSetChanged();
        }
        moviesLoading.setVisibility(View.INVISIBLE);
    }

    @Background
    public void backgroundSetup(int page) {
        try {
            Configuration configuration = MovieDBService
                    .getInstance()
                    .getClient()
                    .getConfiguration(ApiConstants.API_KEY)
                    .execute()
                    .body();
            MovieDBService.getInstance().setConfiguration(configuration);

            ResponseMovies responseMovies =  MovieDBService
                    .getInstance()
                    .getClient()
                    .getUpcomingMovies( ApiConstants.API_KEY, page, null)
                    .execute()
                    .body();
            updateData(responseMovies.results);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        Movie movie = movieAdaper.getItems().get(position);

        MovieDetailFragment movieDetailFragment = MovieDetailFragment_.builder().movie(movie).build();
        android.support.v4.app.FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(frameLayout.getId(), movieDetailFragment,"detail")
                .addToBackStack("detail").commit();
    }

    @OptionsItem(R.id.action_order_rating)
    void orderByRating() {
        Toast.makeText(this,"Sorting by Rating", Toast.LENGTH_SHORT).show();
        movieAdaper.sortByRating();
    }

    @OptionsItem(R.id.action_order_pop)
    void orderByPopularity(){
        Toast.makeText(this,"Sorting by Popularity", Toast.LENGTH_SHORT).show();
        movieAdaper.sortByPopularity();
    }

}
