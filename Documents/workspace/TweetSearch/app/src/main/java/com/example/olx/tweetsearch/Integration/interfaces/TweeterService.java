package com.example.olx.tweetsearch.Integration.interfaces;

import com.example.olx.tweetsearch.Integration.ApiConstants;
import com.example.olx.tweetsearch.model.TweetList;
import com.example.olx.tweetsearch.model.TwitterTokenType;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public interface TweeterService {
    @GET(ApiConstants.TWITTER_HASHTAG_SEARCH_CODE )
    Observable <TweetList> getTweetsByWord(@Header("Authorization") String authorization, @Query("q") String wordSearch);

    @FormUrlEncoded
    @POST("/oauth2/token")
    Observable <TwitterTokenType> getToken (@Header("Authorization") String authorization, @Field("grant_type") String grantType);
}
