package com.lloydfinch.clickinterceptor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lloydfinch.clickinterceptor.lloydfinch.ClickProtector;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testClick(findViewById(R.id.btn_click));
    }


    /**
     * 添加点击事件
     *
     * @param view 需要添加点击事件的目标
     */
    private void testClick(View view) {
        if (view == null) {
            return;
        }

        view.setOnClickListener(new ClickProtector() {
            @Override
            public void onClickWithProtector(View view) {
                toastShort("发送成功");
                logE("" + System.currentTimeMillis());
            }
        }.action(new Runnable() {
            @Override
            public void run() {
                toastShort("稍后再试");
            }
        }));
    }

    private void toastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void logE(String msg) {
        Log.e(TAG, "logE: " + msg);
    }
}
