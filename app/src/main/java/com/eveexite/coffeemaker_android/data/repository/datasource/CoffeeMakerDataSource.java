package com.eveexite.coffeemaker_android.data.repository.datasource;

import com.eveexite.coffeemaker_android.data.entity.CoffeeMakerEntity;
import com.eveexite.coffeemaker_android.data.firebase.FirebaseImpl;

import io.reactivex.Flowable;

/**
 * Created by Ivan on 3/06/2017.
 */

public class CoffeeMakerDataSource implements Datasource{

    private final FirebaseImpl firebaseImpl;

    public CoffeeMakerDataSource(FirebaseImpl firebaseImpl) {
        this.firebaseImpl = firebaseImpl;
    }

    @Override
    public Flowable<CoffeeMakerEntity> coffeeMakerEntity() {
        return firebaseImpl.coffeeMakerEntity();
    }

    @Override
    public void addCoffeeMakerEntity(CoffeeMakerEntity coffeeMakerEntity) {
        firebaseImpl.addCoffeeMakerEntity(coffeeMakerEntity);
    }
}
