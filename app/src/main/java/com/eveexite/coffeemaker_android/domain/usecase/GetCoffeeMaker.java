package com.eveexite.coffeemaker_android.domain.usecase;

import android.content.Context;

import com.eveexite.coffeemaker_android.data.repository.CoffeeMakerRepository;
import com.eveexite.coffeemaker_android.domain.model.CoffeeMaker;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

/**
 * Created by Ivan on 3/06/2017.
 */

public class GetCoffeeMaker extends UseCase<CoffeeMaker>{

    private final CoffeeMakerRepository coffeeMakerRepository;

    @Inject
    GetCoffeeMaker(@Named("executor_thread") Scheduler executorThread,
                          @Named("ui_thread") Scheduler uiThread,
                          CoffeeMakerRepository coffeeMakerRepository) {
        super(executorThread, uiThread);
        this.coffeeMakerRepository = coffeeMakerRepository;
    }

    @Override
    protected Flowable<CoffeeMaker> createFlowableUseCase() {
        return coffeeMakerRepository.coffeeMaker();
    }

    @Override
    public void addUseCase(Object object) {
        CoffeeMaker coffeeMaker = (CoffeeMaker) object;
        coffeeMakerRepository.addCoffeeMaker(coffeeMaker);
    }

}
