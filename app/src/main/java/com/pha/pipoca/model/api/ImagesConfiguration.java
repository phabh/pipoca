package com.pha.pipoca.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public class ImagesConfiguration {
    @SerializedName("base_url")
    public String baseUrl;
    @SerializedName("secure_base_url")
    public String secureBaseUrl;
    @SerializedName("backdrop_sizes")
    public String[] backdropSizes;
    @SerializedName("logo_sizes")
    public String[] logoSizes;
    @SerializedName("poster_sizes")
    public String[] posterSizes;
    @SerializedName("profile_sizes")
    public String[] profileSizes;
    @SerializedName("still_sizes")
    public String[] stillSizes;
}
