package com.example.olx.tweetsearch.maintweetsactivity;

import com.example.olx.tweetsearch.common.interfaces.BasePresenter;
import com.example.olx.tweetsearch.maintweetsactivity.view.TweetView;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public interface MainPresenter extends BasePresenter<TweetView> {
    void getAuthToken();
    void getTweetsByWord(String token, String word);
}
