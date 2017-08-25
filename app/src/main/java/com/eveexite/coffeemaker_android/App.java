package com.eveexite.coffeemaker_android;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.eveexite.coffeemaker_android.di.component.AppComponent;
import com.eveexite.coffeemaker_android.di.component.DaggerAppComponent;
import com.eveexite.coffeemaker_android.di.module.AppModule;

/**
 * Created by ivan on 6/1/17.
 */

public class App extends MultiDexApplication {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
    }

    public void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

}
