package com.example.olx.tweetsearch.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

@Module
public class ApplicationModule {
    private Application mApp;
    private static final String PREFERENCES_FILE = "tweets_sharedset";

    public ApplicationModule(Application mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPref(){
        return mApp.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

}
