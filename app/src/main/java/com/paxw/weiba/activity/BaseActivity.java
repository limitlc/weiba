package com.paxw.weiba.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lichuang on 2015/12/11.
 */
public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    protected abstract void initView();

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
