package com.pha.pipoca.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pha.pipoca.R;
import com.pha.pipoca.adapters.MovieAdaper;
import com.pha.pipoca.clients.MovieDBService;
import com.pha.pipoca.model.api.ApiConstants;
import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.ResponseMovies;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.recyclerview_main_movies)
    RecyclerView recyclerView;

    @Bean
    MovieAdaper movieAdaper;



    @AfterViews
    public void afterViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdaper);
        backgroundSetup();
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
            Configuration configuration;
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


}
