package com.paxw.weiba;

import android.app.Application;
import android.content.Context;

import com.paxw.weiba.bean.WeiBo;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MyApplication extends Application {
    private static Application mContext;
    private String TAG = "weiba";
    public WeiBo weibo;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;


    }
    public static Context getContext(){
        if (null!=mContext) {
            return mContext;
        }else{
            return null;
        }

    }
}
