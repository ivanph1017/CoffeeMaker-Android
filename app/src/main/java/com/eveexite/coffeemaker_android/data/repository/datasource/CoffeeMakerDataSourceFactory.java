package com.eveexite.coffeemaker_android.data.repository.datasource;

import android.content.Context;

import com.eveexite.coffeemaker_android.di.annotations.PerApp;
import com.eveexite.coffeemaker_android.data.firebase.FirebaseImpl;

import javax.inject.Inject;

/**
 * Created by Ivan on 3/06/2017.
 */

@PerApp
public class CoffeeMakerDataSourceFactory {

    private final Context context;

    @Inject
    CoffeeMakerDataSourceFactory(Context context) {
        this.context = context;
    }

    public CoffeeMakerDataSource createDataSource() {
        return new CoffeeMakerDataSource(new FirebaseImpl(context));
    }
}
