package com.paxw.weiba.weiba;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MyApplication extends Application {
    private static Application mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret
        PlatformConfig.setSinaWeibo("2160152899", "10ea99c0d1d3e07de622100e9e9209b2");
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone("1104961841", "yi0PBPyVMaKFjf4x");

    }
    public static Context getContext(){
        if (null!=mContext) {
            return mContext;
        }else{
            return null;
        }

    }
}
