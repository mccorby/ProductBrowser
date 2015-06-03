package com.mccorby.productbrowser.app.domain;

import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;

import java.util.concurrent.Executor;

/**
 * The commands represented by the interactors are executed by the composed Executor.
 * The executor object is provided by injection in the constructor.
 *
 * Created by JAC on 03/06/2015.
 */
public class InteractorInvokerImpl implements InteractorInvoker {

    private final Executor mExecutor;

    public InteractorInvokerImpl(Executor executor) {
        this.mExecutor = executor;
    }

    @Override
    public void execute(final Interactor interactor) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                interactor.execute();
            }
        });
    }
}
