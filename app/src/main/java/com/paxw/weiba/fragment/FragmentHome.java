package com.paxw.weiba.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.paxw.weiba.R;
import com.paxw.weiba.utils.AccessTokenKeeper;
import com.paxw.weiba.utils.Logs;
import com.paxw.weiba.utils.WeiBoConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;

import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by lichuang on 2015/12/29.
 */
public class FragmentHome extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private ListView mainList;

    private BGARefreshLayout rlHomeRefresh;
    private View view;
    private boolean isFirstTime = true;
    ;

    @Override
    public View initView() {

        if (view == null) {
            view = View.inflate(mActivity, R.layout.fragment_home, null);
            mainList =(ListView) view.findViewById(R.id.main_list);
            rlHomeRefresh = (BGARefreshLayout) view.findViewById(R.id.rl_home_refresh);
            rlHomeRefresh.setDelegate(this);

        }
        // 缓存的rootView需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        registerBrocastReceiver();


        // 如果是第一次加载这个页面
        if (isFirstTime) {
            showData();
        }
        return view;
    }

    private void showData() {
        requestWEIBOAPI();




    }

    private void registerBrocastReceiver() {
        //
    }

    private void requestWEIBOAPI(){
        StatusesAPI statuse = new StatusesAPI(mActivity, WeiBoConstants.APP_KEY,AccessTokenKeeper.readAccessToken(mActivity));
        statuse.publicTimeline(50 ,1,false,new RequestListener(){

            @Override
            public void onComplete(String response) {
                if (!TextUtils.isEmpty(response)) {
                    // 调用 User#parse 将JSON串解析成User对象
                    Logs.e(response);
                    Status status = Status.parse(response);
                    if (null!= status){



                    }


                }
            }

            @Override
            public void onWeiboException(WeiboException e) {

            }
        });

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        return false;
    }
}
