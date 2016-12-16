package com.example.olx.tweetsearch.common.interfaces;

/**
 * Created by Martin De Girolamo on 3/18/16.
 */
public interface BasePresenter<T> {
    void init(T view);
    void destroy();
}
