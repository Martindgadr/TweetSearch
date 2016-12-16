package com.example.olx.tweetsearch.maintweetsactivity;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.olx.tweetsearch.Integration.ApiConstants;
import com.example.olx.tweetsearch.Integration.interfaces.TweeterService;
import com.example.olx.tweetsearch.common.Constants;
import com.example.olx.tweetsearch.di.common.PerActivity;
import com.example.olx.tweetsearch.maintweetsactivity.view.TweetView;
import com.example.olx.tweetsearch.model.TweetList;
import com.example.olx.tweetsearch.model.TwitterTokenType;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.olx.tweetsearch.common.Utils.getBase64String;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

@PerActivity
public class MainPresenterImpl implements MainPresenter {
    private TweetView mTweetView;

    @Inject SharedPreferences sharedPref;

    @Inject
    public MainPresenterImpl() {
    }

    @Override
    public void init(TweetView view) {
        mTweetView = view;
    }

    @Override
    public void destroy() {
        mTweetView = null;
    }

    @Override
    public void getAuthToken() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.TWITTER_SEARCH_URL)
                .build();

        TweeterService tweeterService = retrofit.create(TweeterService.class);
        try {
            Observable<TwitterTokenType> twitterTokenTypeObservable = tweeterService.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials");
            twitterTokenTypeObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TwitterTokenType>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (mTweetView != null) {
                                mTweetView.errorOAuth();
                            }
                        }

                        @Override
                        public void onNext(TwitterTokenType twitterTokenType) {
                            sharedPref.edit().putString(Constants.ACCESS_TOKEN, twitterTokenType.accessToken).apply();
                            sharedPref.edit().putString(Constants.ACCESS_TOKEN_TYPE, twitterTokenType.tokenType).apply();
                            if (mTweetView != null) {
                                mTweetView.onAuthGetted();
                            }
                        }
                    });
        } catch (UnsupportedEncodingException e){
            Log.d("Error", e.toString());
        }

    }

    @Override
    public void getTweetsByWord(String token, String word) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConstants.TWITTER_SEARCH_URL)
                .build();

        TweeterService tweeterService = retrofit.create(TweeterService.class);
        Observable<TweetList> tweetsObservable = tweeterService.getTweetsByWord("Bearer " + token, word);

        tweetsObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TweetList>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   mTweetView.errorGettingTweets();
                               }

                               @Override
                               public void onNext(TweetList tweetList) {
                                   mTweetView.showTweetsGetted(tweetList);
                               }
                           });
    }
}
