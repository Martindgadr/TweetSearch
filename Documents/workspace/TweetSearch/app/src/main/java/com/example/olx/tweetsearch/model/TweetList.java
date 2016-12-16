package com.example.olx.tweetsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Martin De Girolamo on 12/16/16.
 */

public class TweetList implements Parcelable{
    @SerializedName("statuses")
    public ArrayList<Tweet> tweets;

    public TweetList(Parcel parcel) {
        super();
        tweets =  parcel.readArrayList(null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(tweets);
    }

    public static Creator<TweetList> CREATOR = new Creator<TweetList>() {

        @Override
        public TweetList createFromParcel(Parcel source) {
            return new TweetList(source);
        }

        @Override
        public TweetList[] newArray(int size) {
            return new TweetList[size];
        }
    };
}
