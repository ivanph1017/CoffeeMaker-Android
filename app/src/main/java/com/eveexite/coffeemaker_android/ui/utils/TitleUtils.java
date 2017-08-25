package com.eveexite.coffeemaker_android.ui.utils;

import android.graphics.Paint;

/**
 * Created by ivan on 6/13/17.
 */

public class TitleUtils {

    public static float textHeight(Paint paint) {
        return (paint.ascent() + paint.descent()) / 2;
    }

}
