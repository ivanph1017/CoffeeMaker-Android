package com.eveexite.coffeemaker_android.domain.usecase;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Ivan on 3/06/2017.
 */

public abstract class UseCaseSubscription<T> extends DisposableSubscriber<T> {

    @Override public void onComplete() {
    }

    @Override public void onError(Throwable e) {
    }

    @Override public void onNext(T t) {
    }

}
