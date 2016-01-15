package com.paxw.weiba.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.paxw.weiba.R;
import com.paxw.weiba.utils.AccessTokenKeeper;
import com.paxw.weiba.utils.Logs;
import com.paxw.weiba.utils.SharedPreferenceUtil;
import com.paxw.weiba.utils.WeiBoConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.openapi.models.User;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;



/**
 * Created by lichuang on 2015/12/29.
 */
public class FragmentSelf extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private TextView tvUsername; // 用户名
    private ImageView ivPhoto; // 头像
    private TextView tvScreenName; // 昵称
    private TextView tvLocation; // 位置
    private TextView tvDescription; // 描述
    private TextView tvFollows; // 关注
    private TextView tvFriends; // 粉丝
    private TextView tvStatus; // 最新微博
    private TextView tvFavorites; // 收藏
    private ImageView ivSex; // 性别标识

    private LinearLayout llFollows;
    private LinearLayout llStatus;
    private LinearLayout llFriends;
    private LinearLayout llFavorites;


    private BGARefreshLayout refreshLayout;

    /** 最新微博内容 **/
    private TextView tvName;
    private TextView tvStatusText;
    private TextView tvCreateAt;
    private TextView tvSource;
    private View rootView;
    @Override
    public View initView() {
        rootView = View.inflate(mActivity, R.layout.frg_selfinfo, null);
        findView(rootView);
        return rootView;
    }
    private void findView(View parent) {
        initRefreshLayout(parent);

        tvUsername = (TextView) parent.findViewById(R.id.main_content_title_text);
        tvUsername.setText(getString(R.string.self_center));
        parent.findViewById(R.id.main_content_title_newweibo).setVisibility(View.GONE);
        ivPhoto = (ImageView) parent.findViewById(R.id.photo);
        tvScreenName = (TextView) parent.findViewById(R.id.name);
        tvLocation = (TextView) parent.findViewById(R.id.tv_address);
        tvDescription = (TextView) parent.findViewById(R.id.description);
        tvStatus = (TextView) parent.findViewById(R.id.tv_selfinfoStatusCount);
        tvFollows = (TextView) parent
                .findViewById(R.id.tv_selfinfoFollowsCount);
        tvFriends = (TextView) parent
                .findViewById(R.id.tv_selfinfoFriendsCount);
        tvFavorites = (TextView) parent
                .findViewById(R.id.tv_selfinfoFavouritesCount);
        llFollows = (LinearLayout) parent.findViewById(R.id.ll_follows);
        llStatus = (LinearLayout) parent.findViewById(R.id.ll_twitter);
        llFriends = (LinearLayout) parent.findViewById(R.id.ll_friends);
        llFavorites = (LinearLayout) parent.findViewById(R.id.ll_favorites);
        tvName = (TextView) parent.findViewById(R.id.tv_name);
        tvCreateAt = (TextView) parent.findViewById(R.id.tv_created_at);
        tvStatusText = (TextView) parent.findViewById(R.id.tv_text);
        tvSource = (TextView) parent.findViewById(R.id.tv_source);
        ivSex = (ImageView) parent.findViewById(R.id.sex);

    }

    private void initRefreshLayout(View parent) {
        refreshLayout = (BGARefreshLayout) parent.findViewById(R.id.rl_self_refresh);
        refreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(mActivity, true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.mytransparent);
        stickinessRefreshViewHolder.setRotateImage(R.drawable.spinner_black_48);
        // 设置下拉刷新和上拉加载更多的风格
        refreshLayout.setRefreshViewHolder(stickinessRefreshViewHolder);
        stickinessRefreshViewHolder.setLoadingMoreText("~~加载中哦~~~");
        stickinessRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.myblue);
        stickinessRefreshViewHolder.setLoadMoreBackgroundDrawableRes(R.color.myblue);
        stickinessRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.myblue);
        stickinessRefreshViewHolder.setRefreshViewBackgroundDrawableRes(R.color.myblue);

    }

    private User mySelf;
    public void initData(){
        if (null == mySelf) {
            if (!TextUtils.isEmpty(SharedPreferenceUtil.getString(SharedPreferenceUtil.USERKEY))) {
                mySelf = User.parse(SharedPreferenceUtil.getString(SharedPreferenceUtil.USERKEY));
            } else {
                requestSelfInfo();
                return;
            }
        }
        tvScreenName.setText(mySelf.screen_name);
        Picasso.with(mActivity).load(mySelf.avatar_large).into(ivPhoto);
        tvLocation.setText(mySelf.location);
        if (!TextUtils.isEmpty(mySelf.verified_reason))
            tvDescription.setText(mySelf.verified_reason);
        else
            tvDescription.setText(mySelf.description);
        tvFollows.setText(String.valueOf(mySelf.followers_count));
        tvFriends.setText(String.valueOf(mySelf.friends_count));
        tvFavorites.setText(String.valueOf(mySelf.followers_count));
        tvStatus.setText(String.valueOf(mySelf.statuses_count));
        if (!TextUtils.equals("m",mySelf.gender)) {
            ivSex.setImageResource(R.drawable.userinfo_icon_female);
        } else {
            ivSex.setImageResource(R.drawable.userinfo_icon_male);
        }
        if (mySelf.status == null){
            requestSelfStatus();
        }else{
            tvName.setText(mySelf.screen_name);
            tvCreateAt.setText(mySelf.status.created_at);
            tvStatusText.setText(mySelf.status.text);

            tvSource.setText(mySelf.status.source);
        }


    }

    private void requestSelfStatus() {
        StatusesAPI mStatusesApi = new StatusesAPI(mActivity,WeiBoConstants.APP_KEY,AccessTokenKeeper.readAccessToken(mActivity));
        mStatusesApi.userTimeline(mySelf.screen_name, 0l, 0l, 5, 1, false, 1, false, new RequestListener() {
            @Override
            public void onComplete(String s) {
                StatusList statusList = StatusList.parse(s);
                mySelf.status=statusList.statusList.get(0);
                //TODO 这里的逻辑应该是这么样么
                Gson gson = new Gson();
                gson.toJson(mySelf);
                SharedPreferenceUtil.setString(SharedPreferenceUtil.USERKEY, s);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        refreshLayout.endRefreshing();
                    }
                }, 2000);

            }

            @Override
            public void onWeiboException(WeiboException e) {

            }
        });
    }

    public void requestSelfInfo(){
        UsersAPI mUsersAPI = new UsersAPI(mActivity, WeiBoConstants.APP_KEY, AccessTokenKeeper.readAccessToken(mActivity));
        mUsersAPI.show(Long.parseLong(AccessTokenKeeper.readAccessToken(mActivity).getUid()), new RequestListener() {
            @Override
            public void onComplete(String s) {
                mySelf = User.parse(s);
                SharedPreferenceUtil.setString(SharedPreferenceUtil.USERKEY, s);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        refreshLayout.endRefreshing();
                    }
                },2000);
            }

            @Override
            public void onWeiboException(WeiboException e) {

            }
        });
    }

    private  Handler handler = new Handler();



    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
        Logs.e("刷新中______________________");
        requestSelfInfo();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {

        return false;
    }


}
