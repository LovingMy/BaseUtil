package com.che.base_util;

import android.content.Context;
import android.util.TypedValue;

/**
 * 尺寸工具类
 */
public class DensityUtil {

    private static Context context;

    public static void init(Context _context) {
        context = _context;
    }

    public static int getScreenWidth() {
        int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static int getScreenHeight() {
        int heigh = context.getResources().getDisplayMetrics().heightPixels;
        return heigh;
    }

    public static float dp2px(float dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(float spVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public static float px2sp(float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
} 
