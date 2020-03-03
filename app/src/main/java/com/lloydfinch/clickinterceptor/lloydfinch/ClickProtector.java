package com.lloydfinch.clickinterceptor.lloydfinch;

import android.view.View;

import com.lloydfinch.clickinterceptor.R;

/**
 * Name: ClickProtector
 * Author: lloydfinch
 * Function: View点击保护器，防止短时间内重复点击
 * Date: 2020-03-03 22:07
 * Modify: lloydfinch 2020-03-03 22:07
 */
public abstract class ClickProtector implements View.OnClickListener, IClickInterceptor {

    private static int CLICK_KEY = R.id.click_id;
    private static long DEFAULT_INTERVAL = 2000L;
    /**
     * 点击的间隔时间默认是2000ms
     */
    private long interval = DEFAULT_INTERVAL;
    /**
     * 拦截的提示
     */
    private Runnable tips = null;

    public ClickProtector() {
    }

    public ClickProtector(long interval) {
        this.interval = interval;
    }

    public ClickProtector action(Runnable runnable) {
        this.tips = runnable;
        return this;
    }

    public abstract void onClickWithProtector(View view);

    @Override
    public final void onClick(View v) {
        if (interceptor(v)) {
            if (null != tips) {
                tips.run();
            }
        } else {
            onClickWithProtector(v);
            /**
             * 存储当前时间(只有点击成功才存储)
             */
            v.setTag(CLICK_KEY, System.currentTimeMillis());
        }
    }

    @Override
    public boolean interceptor(View view) {

        /**
         * 拦截的条件:  设置了点击时间 & 当前时间-点击时间<interval
         */
        Object tag = view.getTag(CLICK_KEY);

        //条件1:设置了点击时间
        boolean condition1 = null != tag;
        if (!condition1) {
            return false;
        }

        //条件2:时间间隔小于interval
        long preTime = (long) tag;
        boolean condition2 = System.currentTimeMillis() - preTime < interval;
        return condition2;
    }
}
