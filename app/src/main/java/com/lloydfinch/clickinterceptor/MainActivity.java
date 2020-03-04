package com.lloydfinch.clickinterceptor;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lloydfinch.clickinterceptor.damao.IClickInterceptor;
import com.lloydfinch.clickinterceptor.damao.TimeFilterInterceptor;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private IClickInterceptor interceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        interceptor = new TimeFilterInterceptor(TimeUnit.SECONDS.toMillis(2));
    }

    public void onClick1(View view) {
        if (interceptor.isInterceptor(view)) {
            Log.e(TAG, "onInterceptor1" );
            return;
        }
        Log.e(TAG, "onClick1: " );
    }

    public void onClick2(View view) {
        if (interceptor.isInterceptor(view)) {
            Log.e(TAG, "onInterceptor2" );
            return;
        }
        Log.e(TAG, "onClick2: " );
    }


}
