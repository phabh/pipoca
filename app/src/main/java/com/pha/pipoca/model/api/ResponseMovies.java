package com.pha.pipoca.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phabh_000 on 6/11/2017.
 */

public class ResponseMovies {
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<Movie> results;
    @SerializedName("dates")
    public Object dates;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("total_results")
    public int totalResults;
}
