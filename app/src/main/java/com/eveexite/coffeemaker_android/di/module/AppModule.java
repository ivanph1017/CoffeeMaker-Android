package com.eveexite.coffeemaker_android.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.eveexite.coffeemaker_android.App;
import com.eveexite.coffeemaker_android.di.annotations.PerApp;
import com.eveexite.coffeemaker_android.data.repository.CoffeeMakerRepository;
import com.eveexite.coffeemaker_android.data.repository.Repository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivan on 6/1/17.
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @PerApp
    App provideApp() {
        return app;
    }

    @Provides
    @PerApp
    Context provideContext() {
        return app;
    }

    @Provides
    @PerApp
    Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @PerApp
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @PerApp
    Repository provideRepository(CoffeeMakerRepository coffeeMakerRepository) {
        return coffeeMakerRepository;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

}
