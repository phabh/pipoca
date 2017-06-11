package com.pha.pipoca.model.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phabh_000 on 6/11/2017.
 */

public class MovieTrailer {
    @SerializedName("id")
    public String id;

    @SerializedName("iso_639_1")
    public String iso6391;

    @SerializedName("iso_3166_1")
    public String iso31661;

    @SerializedName("key")
    public String key;

    @SerializedName("name")
    public String name;

    @SerializedName("site")
    public String site;

    @SerializedName("size")
    public Integer size;

    @SerializedName("type")
    public String type;
}
