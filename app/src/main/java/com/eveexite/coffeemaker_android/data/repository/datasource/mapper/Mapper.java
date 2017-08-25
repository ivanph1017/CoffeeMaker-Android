package com.eveexite.coffeemaker_android.data.repository.datasource.mapper;

/**
 * Created by Ivan on 3/06/2017.
 */

public abstract class Mapper<T1, T2> {

    public abstract T2 map(T1 value);

    public abstract T1 reverseMap(T2 value);

}
