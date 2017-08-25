package com.eveexite.coffeemaker_android.di.component;

/**
 * Created by ivan on 6/1/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.eveexite.coffeemaker_android.App;
import com.eveexite.coffeemaker_android.di.annotations.PerApp;
import com.eveexite.coffeemaker_android.data.repository.CoffeeMakerRepository;
import com.eveexite.coffeemaker_android.di.module.AppModule;


import javax.inject.Named;

import dagger.Component;
import io.reactivex.Scheduler;

@PerApp
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    // App Module

    App getApplication();

    Context getContext();

    Resources getResources();

    SharedPreferences getSharedPreferences();

    CoffeeMakerRepository getCoffeeMakerRepository();

    @Named("executor_thread")
    Scheduler getExecutorThread();

    @Named("ui_thread")
    Scheduler getUiThread();

}
