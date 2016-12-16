package com.example.olx.tweetsearch.maintweetsactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olx.tweetsearch.R;
import com.example.olx.tweetsearch.adapters.TweetListAdapter;
import com.example.olx.tweetsearch.adapters.callback.OnTweetClickListener;
import com.example.olx.tweetsearch.common.BaseFragment;
import com.example.olx.tweetsearch.common.Constants;
import com.example.olx.tweetsearch.di.components.MainFragmentComponent;
import com.example.olx.tweetsearch.maintweetsactivity.view.TweetView;
import com.example.olx.tweetsearch.model.TweetList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Martin De Girolamo on 11/22/16.
 */

@SuppressWarnings("WeakerAccess")
public class MainFragment extends BaseFragment implements TweetView{
    @Bind(R.id.tweets_recycler_view) RecyclerView mTweetsRecyclerView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.btn_search) ImageButton mBtnSearch;
    @Bind(R.id.search_word) EditText mSearchWord;
    @Bind(R.id.CenterHintText) TextView mHintText;

    @Inject MainPresenterImpl mPresenter;
    @Inject TweetListAdapter mTweetsAdapter;
    @Inject SharedPreferences mSharedPref;

    private TweetList mTweetListMain;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tweets_fragment, container, false);

        // Injecting objects.
        this.getComponent(MainFragmentComponent.class).inject(this);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mPresenter.init(this);
        mBtnSearch.setEnabled(false);
        mPresenter.getAuthToken();
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        if (savedInstanceState != null) {
            mTweetListMain = savedInstanceState.getParcelable("tweets_list");
        }

        mTweetsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTweetsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTweetsRecyclerView.setAdapter(mTweetsAdapter);
        mTweetsAdapter.setOnItemClickListener(onTweetClickListener);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSearchWord.toString().isEmpty()) {
                    mPresenter.getTweetsByWord(mSharedPref.getString(Constants.ACCESS_TOKEN, ""), mSearchWord.getText().toString());
                    mProgressBar.setVisibility(ProgressBar.VISIBLE);
                } else {
                    Toast.makeText(getContext(), R.string.completeSearchField, Toast.LENGTH_SHORT).show();
                }

                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        if (mTweetListMain != null){
            mTweetsAdapter.setTweets(mTweetListMain);
            mTweetsAdapter.notifyDataSetChanged();
            mHintText.setVisibility(View.INVISIBLE);
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("tweets_list", mTweetListMain);
    }


    private OnTweetClickListener onTweetClickListener = new OnTweetClickListener() {
        @Override
        public void onTweetClicked(String id) {

        }
    };

    @Override
    public void errorGettingTweets() {
        Toast.makeText(getContext(), R.string.errorService, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showTweetsGetted(TweetList tweetList) {
        if (tweetList != null){
            mTweetListMain = tweetList;
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            mHintText.setVisibility(View.INVISIBLE);
            mTweetsAdapter.setTweets(tweetList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.destroy();
    }

    @Override
    public void onAuthGetted() {
        mBtnSearch.setEnabled(true);
    }

    @Override
    public void errorOAuth() {
        Toast.makeText(getContext(), R.string.errorOauth, Toast.LENGTH_SHORT).show();
    }
}
