package com.eveexite.coffeemaker_android.data.firebase;

import android.content.Context;

import com.eveexite.coffeemaker_android.data.entity.CoffeeMakerEntity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.Flowable;

/**
 * Created by Ivan on 3/06/2017.
 */

public class FirebaseImpl {

    private final Context context;
    private DatabaseReference databaseReference;

    public FirebaseImpl(Context context) {
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();
    }

    public Flowable<CoffeeMakerEntity> coffeeMakerEntity () {
        return RxFirebaseDatabase.flowableValueEvent(databaseReference,
                DataSnapshotMapper.of(CoffeeMakerEntity.class));
    }

    public void addCoffeeMakerEntity(CoffeeMakerEntity coffeeMakerEntity) {
        databaseReference.setValue(coffeeMakerEntity);
    }

}
