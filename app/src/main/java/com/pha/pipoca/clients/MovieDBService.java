package com.pha.pipoca.clients;

import com.pha.pipoca.model.api.ApiConstants;
import com.pha.pipoca.model.api.Configuration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public class MovieDBService {

    private static MovieDBService instance;
    private MovieDBClient movieDBClient;
    private Configuration configuration;

    private MovieDBService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieDBClient = retrofit.create(MovieDBClient.class);
    }

    public static MovieDBService getInstance() {
        if( instance == null) {
            instance = new MovieDBService();
        }
        return instance;
    }

    public MovieDBClient getClient() {
        return movieDBClient;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
