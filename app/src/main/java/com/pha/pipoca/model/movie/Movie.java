package com.pha.pipoca.model.movie;

import java.util.List;

/**
 * Created by phabh_000 on 6/9/2017.
 */

public class Movie {
    private String name;
    private int rating;
    private int popularity;
    private String description;
    private List<Media> photos;
    private List<Media> videos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Media> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Media> photos) {
        this.photos = photos;
    }

    public List<Media> getVideos() {
        return videos;
    }

    public void setVideos(List<Media> videos) {
        this.videos = videos;
    }
}
