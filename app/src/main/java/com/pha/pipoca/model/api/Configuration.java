package com.pha.pipoca.model.api;

import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phabh_000 on 6/10/2017.
 */

public class Configuration{
    @SerializedName("images")
    public ImagesConfiguration images;
    @SerializedName("change_keys")
    public String[] changeKeys;
}
