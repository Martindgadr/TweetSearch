package com.example.olx.tweetsearch.maintweetsactivity.view;

import com.example.olx.tweetsearch.model.Tweet;
import com.example.olx.tweetsearch.model.TweetList;

import java.util.List;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public interface TweetView {
    void showTweetsGetted(TweetList tweetList);
    void onAuthGetted();
    void errorGettingTweets();
    void errorOAuth();
}
