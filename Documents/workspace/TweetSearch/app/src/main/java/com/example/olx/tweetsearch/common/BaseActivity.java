package com.example.olx.tweetsearch.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.olx.tweetsearch.R;
import com.example.olx.tweetsearch.di.components.ApplicationComponent;
import com.example.olx.tweetsearch.di.module.ActivityModule;


/**
 * Created by Martin De Girolamo on 11/22/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @SuppressWarnings("SameReturnValue")
    @LayoutRes
    private int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        this.getApplicationComponent().inject(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            if (fragment != null)
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
        }
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((TweetsSearchApplication)getApplication()).getappComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
