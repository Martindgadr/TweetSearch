package com.example.olx.tweetsearch.common;

import android.support.v4.app.Fragment;

import com.example.olx.tweetsearch.di.common.HasComponent;

/**
 * Created by Martin De Girolamo on 11/22/16.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
