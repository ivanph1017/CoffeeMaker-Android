package com.eveexite.coffeemaker_android.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eveexite.coffeemaker_android.App;
import com.eveexite.coffeemaker_android.di.component.ActivityComponent;
import com.eveexite.coffeemaker_android.di.component.DaggerActivityComponent;

import butterknife.ButterKnife;

/**
 * Created by Ivan on 3/06/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(getComponent());
        setContentView(getLayoutId());
        bindViews();
        initView();
    }

    /**
     * Use this method to initialize view components. This method is called after {@link
     * BaseActivity#bindViews()}
     */
    public void initView() {
    }

    /**
     * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();

    /**
     * Setup UIComponent
     */
    protected abstract void setupComponent(ActivityComponent component);

    private ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent(this))
                .build();
    }
}
