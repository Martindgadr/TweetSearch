package com.example.olx.tweetsearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public class TwitterUser {

    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_image_url")
    public String profileImageUrl;

}
