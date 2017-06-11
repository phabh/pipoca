package com.pha.pipoca.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pha.pipoca.R;
import com.pha.pipoca.adapters.MovieAdaper;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.fragments.MovieDetailFragment;
import com.pha.pipoca.fragments.MovieDetailFragment_;
import com.pha.pipoca.model.api.ApiConstants;
import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.ResponseMovies;
import com.pha.pipoca.utils.ItemClickSupport;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ItemClick;
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

    @Bean
    MovieAdaper movieAdaper;

    MovieDetailFragment movieDetailFragment;
    ItemClickSupport itemClickSupport;

    @AfterViews
    public void afterViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdaper);
        backgroundSetup();
        itemClickSupport = ItemClickSupport.addTo(recyclerView);
        itemClickSupport.setOnItemClickListener(this);
    }

    @AfterInject
    public void afterInject(){

    }

    @UiThread
    public void updateData(List<Movie> newList)
    {
        movieAdaper.addItems(newList);
        movieAdaper.notifyDataSetChanged();

    }

    @Background
    public void backgroundSetup() {
        try {
            MovieDBService.getInstance().getClient().getConfiguration(ApiConstants.API_KEY).enqueue(new Callback<Configuration>() {
                @Override
                public void onResponse(Call<Configuration> call, Response<Configuration> response) {
                    int statusCode = response.code();
                    MovieDBService.getInstance().setConfiguration(response.body());
                }

                @Override
                public void onFailure(Call<Configuration> call, Throwable t) {
                    // Log error here since request failed
                }
            });
            MovieDBService.getInstance().getClient().getConfiguration(ApiConstants.API_KEY).execute();



            MovieDBService.getInstance().getClient().getUpcomingMovies(ApiConstants.API_KEY,null,null).enqueue(new Callback<ResponseMovies>() {
                @Override
                public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                    int statusCode = response.code();
                    updateData(response.body().results);
                }

                @Override
                public void onFailure(Call<ResponseMovies> call, Throwable t) {
                    // Log error here since request failed
                }
            });
            MovieDBService.getInstance().getClient().getUpcomingMovies(ApiConstants.API_KEY,null,null).execute();



        } catch (IOException exception) {

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
        movieAdaper.sortByRating();
    }

    @OptionsItem(R.id.action_order_pop)
    void orderByPopularity(){
        movieAdaper.sortByPopularity();
    }

}
