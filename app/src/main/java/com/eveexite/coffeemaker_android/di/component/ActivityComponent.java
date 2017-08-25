package com.eveexite.coffeemaker_android.di.component;

import com.eveexite.coffeemaker_android.di.annotations.PerActivity;
import com.eveexite.coffeemaker_android.ui.view.MainActivity;

import dagger.Component;

/**
 * Created by Ivan on 3/06/2017.
 */

@PerActivity
@Component(dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
