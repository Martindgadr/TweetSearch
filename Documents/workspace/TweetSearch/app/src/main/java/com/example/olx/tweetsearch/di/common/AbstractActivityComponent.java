package com.example.olx.tweetsearch.di.common;

import android.support.v7.app.AppCompatActivity;

import com.example.olx.tweetsearch.di.components.ApplicationComponent;
import com.example.olx.tweetsearch.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Martin De Girolamo on 12/14/16.
 *
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface AbstractActivityComponent {
    //Exposed to sub-graphs.
    AppCompatActivity activity();
}
