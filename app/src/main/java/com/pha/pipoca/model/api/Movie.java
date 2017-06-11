package com.pha.pipoca.model.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public class Movie implements Serializable {
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    public Object belongsToCollection;
    @SerializedName("buget")
    public int budget;
    @SerializedName("genres")
    public Object[] genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_languages")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("production_companies")
    public Object[] productionCompanies;
    @SerializedName("production_countries")
    public Object[] productionCountries;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public int revenue;
    @SerializedName("runtime")
    public int runtime;
    @SerializedName("spoken_languages")
    public Object[] spokenLanguages;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagLine;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double voteAvarage;
    @SerializedName("vote_count")
    public int voteCount;
}
