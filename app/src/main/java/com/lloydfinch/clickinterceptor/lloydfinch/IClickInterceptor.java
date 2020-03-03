package com.lloydfinch.clickinterceptor.lloydfinch;

import android.view.View;

/**
 * Name: ClickInterceptor
 * Author: lloydfinch
 * Function: View点击拦截器
 * Date: 2020-03-03 22:21
 * Modify: lloydfinch 2020-03-03 22:21
 */
public interface IClickInterceptor {

    boolean interceptor(View view);
}
