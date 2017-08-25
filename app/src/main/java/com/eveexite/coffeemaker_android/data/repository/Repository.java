package com.eveexite.coffeemaker_android.data.repository;

import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;

import io.reactivex.Flowable;

/**
 * Created by Ivan on 3/06/2017.
 */

public interface Repository {

    Flowable<CoffeeMaker> coffeeMaker();

    void addCoffeeMaker(CoffeeMaker coffeeMaker);

}
