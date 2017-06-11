package com.pha.pipoca.clients;

import com.pha.pipoca.model.api.Configuration;
import com.pha.pipoca.model.api.Movie;
import com.pha.pipoca.model.api.ResponseMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public interface MovieDBClient {
    @GET("movie/upcoming")
    Call<ResponseMovies> getUpcomingMovies(@Query("api_key") String api_key, @Query("page") Integer page, @Query("language") String language);
    @GET("movie/{movie_id}")
    Call<Movie> getDetail(@Path("movie_id") int movieId, @Query("language") String language);
    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String api_key);
}
