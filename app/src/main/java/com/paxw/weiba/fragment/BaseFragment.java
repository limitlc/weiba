package com.paxw.weiba.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by lichuang on 2015/12/29.
 */
public abstract class BaseFragment extends Fragment{
    protected Context context;

    public Activity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        context = getActivity();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Activity已经初始化完毕了, 当前需要初始化Fragment的数据了
        initData();
    }

    /**
     * 初始化Fragment的布局
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据, 子类覆盖此方法, 来实现自己的数据初始化操作
     */
    public void initData() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View view = initView();

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
