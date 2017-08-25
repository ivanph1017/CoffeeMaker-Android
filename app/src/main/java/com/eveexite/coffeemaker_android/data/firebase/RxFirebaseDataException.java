package com.eveexite.coffeemaker_android.data.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

/**
 * Created by ivan on 6/1/17.
 */

public class RxFirebaseDataException extends Exception {

    protected DatabaseError error;

    public RxFirebaseDataException(@NonNull DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }

    @Override
    public String toString() {
        return "RxFirebaseDataException{" +
                "error=" + error +
                '}';
    }

}
