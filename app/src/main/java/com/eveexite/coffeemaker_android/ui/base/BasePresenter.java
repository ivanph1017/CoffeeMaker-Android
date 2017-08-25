package com.eveexite.coffeemaker_android.ui.base;

/**
 * Created by Ivan on 3/06/2017.
 */

public abstract class BasePresenter<T> {

    protected T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {

    }

    /**
     * This interface will be to call methods from an Activity or Fragment
     */
    public interface BaseView {

        void showMessage(String title, String message);

    }
}
