package com.eveexite.coffeemaker_android.data.repository;

import android.content.Context;

import com.eveexite.coffeemaker_android.data.repository.datasource.mapper.CoffeeMakerToCoffeeMakerEntityMapper_Factory;
import com.eveexite.coffeemaker_android.di.annotations.PerApp;
import com.eveexite.coffeemaker_android.data.repository.datasource.CoffeeMakerDataSourceFactory;
import com.eveexite.coffeemaker_android.data.repository.datasource.Datasource;
import com.eveexite.coffeemaker_android.data.repository.datasource.mapper.CoffeeMakerToCoffeeMakerEntityMapper;
import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;
import com.eveexite.coffeemaker_android.data.entity.CoffeeMakerEntity;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Ivan on 3/06/2017.
 */

@PerApp
public class CoffeeMakerRepository implements Repository{

    private final CoffeeMakerToCoffeeMakerEntityMapper coffeeMakerToCoffeeMakerEntityMapper;
    private final Datasource datasource;

    @Inject
    CoffeeMakerRepository(@NonNull CoffeeMakerDataSourceFactory coffeeMakerDataSourceFactory,
                                 @NonNull CoffeeMakerToCoffeeMakerEntityMapper coffeeMakerToCoffeeMakerEntityMapper) {
        this.coffeeMakerToCoffeeMakerEntityMapper = coffeeMakerToCoffeeMakerEntityMapper;
        this.datasource = coffeeMakerDataSourceFactory.createDataSource();
    }

    @Override
    public Flowable<CoffeeMaker> coffeeMaker() {
        return datasource.coffeeMakerEntity().map(coffeeMakerEntity ->
                coffeeMakerToCoffeeMakerEntityMapper.reverseMap(coffeeMakerEntity));
    }

    @Override
    public void addCoffeeMaker(CoffeeMaker coffeeMaker) {
        datasource.addCoffeeMakerEntity(coffeeMakerToCoffeeMakerEntityMapper.map(coffeeMaker));
    }
}
