package com.example.olx.tweetsearch.di.components;


import com.example.olx.tweetsearch.di.common.AbstractActivityComponent;
import com.example.olx.tweetsearch.di.common.PerActivity;
import com.example.olx.tweetsearch.di.module.ActivityModule;
import com.example.olx.tweetsearch.maintweetsactivity.MainFragment;

import dagger.Component;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface MainFragmentComponent extends AbstractActivityComponent {
    void inject(MainFragment mainFragment);
//    void inject(PetTabFragment petTabFragment);
}
