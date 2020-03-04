package com.lloydfinch.clickinterceptor.damao;

import android.view.View;

import java.util.HashMap;

public class TimeFilterInterceptor implements IClickInterceptor {

    private long interval;

    private HashMap<View, Long> cache;

    public TimeFilterInterceptor(long interval) {
        this.interval = interval;
        this.cache = new HashMap<>();
    }

    @Override
    public boolean isInterceptor(View v) {
        long currentClick = System.currentTimeMillis();
        long lastClick;
        if (!cache.containsKey(v)) {
            cache.put(v, currentClick);
            return false;
        }

        Long aLong = cache.get(v);
        if (aLong == null){
            cache.put(v,currentClick);
            return false;
        }

        lastClick = aLong;
        if ((currentClick - lastClick)> interval) {
            cache.put(v,currentClick);
            return false;
        }

        return true;
    }
}
