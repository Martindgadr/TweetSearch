package com.example.olx.tweetsearch.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olx.tweetsearch.R;
import com.example.olx.tweetsearch.adapters.callback.OnTweetClickListener;
import com.example.olx.tweetsearch.di.common.PerActivity;
import com.example.olx.tweetsearch.model.Tweet;
import com.example.olx.tweetsearch.model.TweetList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Martin De Girolamo on 11/22/16.
 */

@PerActivity
public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.TweetsViewHolder> {
    private OnTweetClickListener onTweetClickListener;
    private TweetList tweetList2;
    private Context mContext;
    private final LayoutInflater layoutInflater;

    @Inject
    public TweetListAdapter(Context context) {
        layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public TweetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.recyclerlist_tweet, parent, false);
        return new TweetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetsViewHolder holder, int position) {
        final Tweet tweet = tweetList2.tweets.get(position);

        holder.mUserName.setText(tweet.user.name);
        Picasso.with(mContext).load(tweet.user.profileImageUrl)
                .placeholder(R.drawable.nousuario)
                .into(holder.mUserImg, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });

        holder.mTweetContent.setText(tweet.text);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTweetClickListener.onTweetClicked(tweet.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (tweetList2 != null) ? tweetList2.tweets.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setTweets(TweetList tweets){
        tweetList2 = tweets;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnTweetClickListener onTweetItemClickListener) {
        onTweetClickListener = onTweetItemClickListener;
    }

    static class TweetsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_user_name) TextView mUserName;
        @Bind(R.id.tweet_content) TextView mTweetContent;
        @Bind(R.id.item_img_user) ImageView mUserImg;
        @Bind(R.id.tweetCardView) CardView mCardView;

         TweetsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
