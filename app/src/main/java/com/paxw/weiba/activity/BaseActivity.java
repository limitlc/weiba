package com.paxw.weiba.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.paxw.weiba.utils.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by lichuang on 2015/12/11.
 */
public abstract class BaseActivity extends FragmentActivity {


    protected Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
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
