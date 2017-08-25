package com.eveexite.coffeemaker_android.data.repository.datasource.mapper;

import com.eveexite.coffeemaker_android.di.annotations.PerApp;
import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;
import com.eveexite.coffeemaker_android.data.entity.CoffeeMakerEntity;

import javax.inject.Inject;

/**
 * Created by Ivan on 3/06/2017.
 */

@PerApp
public class CoffeeMakerToCoffeeMakerEntityMapper extends Mapper<CoffeeMaker, CoffeeMakerEntity> {

    @Inject
    CoffeeMakerToCoffeeMakerEntityMapper () {
    }

    @Override
    public CoffeeMakerEntity map(CoffeeMaker value) {
        CoffeeMakerEntity coffeeMakerEntity = new CoffeeMakerEntity();
        coffeeMakerEntity.setCoffeeMakerReady(value.getCoffeeMakerReady());
        coffeeMakerEntity.setCoffeeReady(value.getCoffeeReady());
        coffeeMakerEntity.setWaterLevel(value.getWaterLevel());
        coffeeMakerEntity.setTurnOn(value.getTurnOn());
        coffeeMakerEntity.setTimer(value.getTimer());
        coffeeMakerEntity.setTimerSleep(value.getTimerSleep());
        return coffeeMakerEntity;
    }

    @Override
    public CoffeeMaker reverseMap(CoffeeMakerEntity value) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.setCoffeeMakerReady(value.getCoffeeMakerReady());
        coffeeMaker.setCoffeeReady(value.getCoffeeReady());
        coffeeMaker.setTimer(value.getTimer());
        coffeeMaker.setTurnOn(value.getTurnOn());
        coffeeMaker.setWaterLevel(value.getWaterLevel());
        coffeeMaker.setTimerSleep(value.getTimerSleep());
        return coffeeMaker;
    }
}
