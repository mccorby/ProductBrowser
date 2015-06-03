package com.mccorby.productbrowser.abstractions;

/**
 * Created by JAC on 03/06/2015.
 */
public interface Bus {

    void post(Object object);
    void register(Object object);
    void unregister(Object object);
}
