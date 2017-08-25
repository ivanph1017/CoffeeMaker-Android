package com.eveexite.coffeemaker_android.di.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ivan on 6/1/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}
