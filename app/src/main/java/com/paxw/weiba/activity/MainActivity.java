package com.paxw.weiba.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.paxw.weiba.R;
import com.paxw.weiba.picasso.CircleTransform;
import com.paxw.weiba.utils.Logs;
import com.paxw.weiba.utils.WeiBoConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.legacy.GroupAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.squareup.picasso.Picasso;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * Created by lichuang on 2015/12/24.
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private TextView textName;
    private ImageView icon;
    private ListView myGroup;
    private BGARefreshLayout refGroup;
    private GroupAPI groupAPI;

    @Override
    protected void initView(){
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        textName = (TextView) findViewById(R.id.user_info_name);
        icon = (ImageView) findViewById(R.id.user_info_icon);
        myGroup = (ListView) findViewById(R.id.my_group_listview);
        refGroup = (BGARefreshLayout) findViewById(R.id.rl_group_refresh);

        getUserInfo();



    }
    private UsersAPI mUsersAPI;
    private void getUserInfo(){
        mUsersAPI = new UsersAPI(this, WeiBoConstants.APP_KEY,mAccessToken);
        mUsersAPI.show(Long.parseLong(mAccessToken.getUid()), mListener);
        groupAPI = new GroupAPI(this, WeiBoConstants.APP_KEY,mAccessToken);
        groupAPI.showGroup(Long.parseLong(mAccessToken.getUid()),friendGroup);

    }
    private RequestListener friendGroup = new RequestListener() {
        @Override
        public void onComplete(String s) {
            Logs.e(s);
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                // 调用 User#parse 将JSON串解析成User对象
                Logs.e(response);
                User user = User.parse(response);
                Logs.e(user.avatar_large);
                if (null!=user){
                    Picasso.with(MainActivity.this).load(user.avatar_large).transform(new CircleTransform()).into(icon);
                    textName.setText(user.screen_name);


                }


            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };
    public void news(View View){
        Intent news = new Intent(this ,NewWeibo.class);
        startActivity(news);
//

    }

}
