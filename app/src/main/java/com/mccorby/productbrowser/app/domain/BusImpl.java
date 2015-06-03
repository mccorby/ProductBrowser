package com.mccorby.productbrowser.app.domain;

import com.mccorby.productbrowser.domain.abstractions.Bus;

import de.greenrobot.event.EventBus;

/**
 * Created by JAC on 03/06/2015.
 */
public class BusImpl implements Bus {

    private EventBus mEventBus;

    public BusImpl(EventBus eventBus) {
        this.mEventBus = eventBus;
    }

    @Override
    public void post(Object object) {
        mEventBus.post(object);
    }

    @Override
    public void register(Object object) {
        mEventBus.register(object);
    }

    @Override
    public void unregister(Object object) {
        mEventBus.unregister(object);
    }
}
