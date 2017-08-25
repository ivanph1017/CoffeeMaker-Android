package com.eveexite.coffeemaker_android.data.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.ObservableEmitter;

/**
 * Created by ivan on 6/1/17.
 */

public class RxHandler<T> implements OnSuccessListener<T>, OnFailureListener, OnCompleteListener<T> {

    private final ObservableEmitter<? super T> observableEmitter;

    private RxHandler(ObservableEmitter<? super T> observableEmitter) {
        this.observableEmitter = observableEmitter;
    }

    public static <T> void assignOnTask(ObservableEmitter<? super T> observableEmitter, Task<T> task) {
        RxHandler handler = new RxHandler(observableEmitter);
        task.addOnSuccessListener(handler);
        task.addOnFailureListener(handler);
        try {
            task.addOnCompleteListener(handler);
        } catch (Throwable t) {
            // ignore
        }
    }

    @Override
    public void onSuccess(T res) {
        observableEmitter.onNext(res);
    }

    @Override
    public void onComplete(@NonNull Task<T> task) {
        observableEmitter.onComplete();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        observableEmitter.onError(e);
    }

}

