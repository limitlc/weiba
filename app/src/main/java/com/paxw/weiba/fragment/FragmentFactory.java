package com.paxw.weiba.fragment;

import java.util.HashMap;

/**
 * Created by lichuang on 2015/12/29.
 *
 *
 * fragment工厂
 *
 */
public class FragmentFactory {
    /**
    * 首页
    * */
    public final static  int  FRAGMENTHOME = 0x0000;
    /**
     * 消息
     */
    public final  static int FRAGMENTMESSAGE = 0x0001;
    /**
     * 发现
     */
    public final static int FRAGMENTFIND = 0x0002;
    /**
     * 个人中心
     */
    public final static int  FRAGMENTSELF = 0x0003;
    /**
     * 存放fragment 的集合----------------内存缓存
     */
    private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
    public static BaseFragment get(int tag){
        // 从缓存里面取出fragment的数据
        BaseFragment fragment = mFragments.get(tag);
        if (fragment == null) {
            switch (tag){
            case FRAGMENTHOME:
                fragment = new FragmentHome();
                break;
            case FRAGMENTMESSAGE:
                fragment  = new FragmentMessage();
                break;
            case FRAGMENTFIND:
                fragment  = new FragmentFind();
                break;
            case FRAGMENTSELF:
                fragment  = new FragmentSelf();
                break;
            }
            mFragments.put(tag, fragment);
        }


        return fragment;
    }


}
