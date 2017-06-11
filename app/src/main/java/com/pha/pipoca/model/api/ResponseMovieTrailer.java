package com.pha.pipoca.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phabh_000 on 6/11/2017.
 */

public class ResponseMovieTrailer {
    @SerializedName("id")
    public Integer id;

    @SerializedName("results")
    public MovieTrailer[] results;
}
