package com.example.olx.tweetsearch.di.components;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.olx.tweetsearch.common.BaseActivity;
import com.example.olx.tweetsearch.di.module.ApplicationModule;
import com.example.olx.tweetsearch.maintweetsactivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);

    // for subchilds
    Context context();
    SharedPreferences sharedPreferences();
}
