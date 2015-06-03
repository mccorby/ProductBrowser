package com.mccorby.productbrowser.app.presentation;

/**
 * A contract any presenter in this app should comply to.
 *
 * Created by JAC on 03/06/2015.
 */
public interface Presenter {

    void onCreate();
    void onResume();
    void onPause();
}
