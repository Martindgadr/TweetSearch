package com.example.olx.tweetsearch.di.module;

import android.support.v7.app.AppCompatActivity;

import com.example.olx.tweetsearch.di.common.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity){
        this.activity = activity;
    }

    @Provides @PerActivity
    AppCompatActivity activity(){
        return activity;
    }

}
