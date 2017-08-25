package com.eveexite.coffeemaker_android.domain.usecase;


import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Ivan on 3/06/2017.
 */

public abstract class UseCase<T> {

    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    public UseCase(Scheduler executorThread, Scheduler uiThread) {
        this.executorThread = executorThread;
        this.uiThread = uiThread;
        compositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableSubscriber disposableSubscriber) {

        if (disposableSubscriber == null) {
            throw new IllegalArgumentException("disposableObserver must not be null");
        }

        final Flowable<T> flowable=
                this.createFlowableUseCase().subscribeOn(executorThread).observeOn(uiThread);

        DisposableSubscriber subscriber = flowable.subscribeWith(disposableSubscriber);
        compositeDisposable.add(subscriber);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Flowable<T> createFlowableUseCase();

    public abstract void addUseCase(Object object);

}
