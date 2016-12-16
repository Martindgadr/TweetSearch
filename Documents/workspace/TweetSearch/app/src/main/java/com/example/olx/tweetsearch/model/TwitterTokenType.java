package com.example.olx.tweetsearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public class TwitterTokenType {
    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;
}
