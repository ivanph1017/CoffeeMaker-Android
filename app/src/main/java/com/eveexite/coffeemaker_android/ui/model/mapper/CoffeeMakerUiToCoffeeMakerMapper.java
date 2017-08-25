package com.eveexite.coffeemaker_android.ui.model.mapper;

import com.eveexite.coffeemaker_android.data.repository.datasource.mapper.Mapper;
import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;
import com.eveexite.coffeemaker_android.ui.model.CoffeeMakerUiModel;

import javax.inject.Inject;

/**
 * Created by Ivan on 3/06/2017.
 */

public class CoffeeMakerUiToCoffeeMakerMapper extends Mapper<CoffeeMakerUiModel, CoffeeMaker> {

    @Inject
    CoffeeMakerUiToCoffeeMakerMapper() {
    }

    @Override
    public CoffeeMaker map(CoffeeMakerUiModel value) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.setCoffeeMakerReady(value.getCoffeeMakerReady());
        coffeeMaker.setCoffeeReady(value.getCoffeeReady());
        coffeeMaker.setTimer(value.getTimer());
        coffeeMaker.setTurnOn(value.getTurnOn());
        coffeeMaker.setWaterLevel(value.getWaterLevel());
        coffeeMaker.setTimerSleep(value.getTimerSleep());
        return coffeeMaker;
    }

    @Override
    public CoffeeMakerUiModel reverseMap(CoffeeMaker value) {
        CoffeeMakerUiModel coffeeMakerUiModel = new CoffeeMakerUiModel();
        coffeeMakerUiModel.setWaterLevel(value.getWaterLevel());
        coffeeMakerUiModel.setTurnOn(value.getTurnOn());
        coffeeMakerUiModel.setCoffeeMakerReady(value.getCoffeeMakerReady());
        coffeeMakerUiModel.setCoffeeReady(value.getCoffeeReady());
        coffeeMakerUiModel.setTimer(value.getTimer());
        coffeeMakerUiModel.setTimerSleep(value.getTimerSleep());
        return coffeeMakerUiModel;
    }
}
