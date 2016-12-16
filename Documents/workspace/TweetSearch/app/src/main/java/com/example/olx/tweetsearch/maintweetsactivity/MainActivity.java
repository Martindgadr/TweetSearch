package com.example.olx.tweetsearch.maintweetsactivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.olx.tweetsearch.R;
import com.example.olx.tweetsearch.common.BaseActivity;
import com.example.olx.tweetsearch.di.common.HasComponent;
import com.example.olx.tweetsearch.di.components.DaggerMainFragmentComponent;
import com.example.olx.tweetsearch.di.components.MainFragmentComponent;

public class MainActivity extends BaseActivity implements HasComponent<MainFragmentComponent> {
    private MainFragmentComponent mainFragmentComponent;

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);

        mainFragmentComponent = DaggerMainFragmentComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
    }

    @Override
    public MainFragmentComponent getComponent() {
        return mainFragmentComponent;
        }
}
