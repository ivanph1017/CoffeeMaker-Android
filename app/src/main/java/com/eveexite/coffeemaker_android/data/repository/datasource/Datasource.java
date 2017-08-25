package com.eveexite.coffeemaker_android.data.repository.datasource;

import com.eveexite.coffeemaker_android.data.entity.CoffeeMakerEntity;

import io.reactivex.Flowable;

/**
 * Created by Ivan on 3/06/2017.
 */

public interface Datasource {

    Flowable<CoffeeMakerEntity> coffeeMakerEntity();

    void addCoffeeMakerEntity(CoffeeMakerEntity coffeeMakerEntity);

}
