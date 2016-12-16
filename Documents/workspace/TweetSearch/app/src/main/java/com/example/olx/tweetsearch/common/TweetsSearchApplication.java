package com.example.olx.tweetsearch.common;


import android.app.Application;

import com.example.olx.tweetsearch.di.components.ApplicationComponent;
import com.example.olx.tweetsearch.di.components.DaggerApplicationComponent;
import com.example.olx.tweetsearch.di.module.ApplicationModule;

/**
 * Created by Martin De Girolamo on 11/22/16.
 */

public class TweetsSearchApplication extends Application {
    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger Injection
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getappComponent() {
        return appComponent;
    }
}
