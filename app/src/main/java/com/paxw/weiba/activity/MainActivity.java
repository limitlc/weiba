package com.paxw.weiba.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.paxw.weiba.R;
import com.paxw.weiba.fragment.FragmentFactory;
import com.paxw.weiba.picasso.CircleTransform;
import com.paxw.weiba.utils.Logs;
import com.paxw.weiba.utils.SharedPreferenceUtil;
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
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private DrawerLayout drawerLayout;
    private TextView textName;
    private ImageView icon;
    private ListView myGroup;
    private BGARefreshLayout refGroup;
    private GroupAPI groupAPI;
    private FrameLayout mainContent;
    private RadioGroup radioGroup;
    private User user;

    @Override
    protected void initView(){
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        textName = (TextView) findViewById(R.id.user_info_name);
        icon = (ImageView) findViewById(R.id.user_info_icon);
        myGroup = (ListView) findViewById(R.id.my_group_listview);
        refGroup = (BGARefreshLayout) findViewById(R.id.rl_group_refresh);
        mainContent = (FrameLayout) findViewById(R.id.main_content);
        radioGroup = (RadioGroup) findViewById( R.id.main_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        chechFragment(FragmentFactory.FRAGMENTHOME);
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
                Logs.e("user", response);
                user = User.parse(response);
                if (TextUtils.isEmpty(SharedPreferenceUtil.getString(SharedPreferenceUtil.USERKEY)))
                         SharedPreferenceUtil.setString(SharedPreferenceUtil.USERKEY,response);
                Logs.e(user.avatar_large);
                if (null!= user){
                    Picasso.with(MainActivity.this).load(user.avatar_large).transform(new CircleTransform()).into(icon);
                    textName.setText(user.screen_name);


                }


            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };
//    public void news(View View){
//        Intent news = new Intent(this ,NewWeibo.class);
//        startActivity(news);
////
//
//    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch ( checkedId ){
            case R.id.bottom_first:
                   chechFragment(FragmentFactory.FRAGMENTHOME);
                break;
            case R.id.bottom_second:
                    chechFragment(FragmentFactory.FRAGMENTMESSAGE);
                break;
            case R.id.bottom_third:
                chechFragment(FragmentFactory.FRAGMENTFIND);
                break;
            case R.id.bottom_fourth:
                chechFragment(FragmentFactory.FRAGMENTSELF);

                break;
            default:
                chechFragment(FragmentFactory.FRAGMENTHOME);
                break;
        }

    }


    private void chechFragment(int tag){
        // 获取FragmentManager管理器对象
        FragmentManager fm = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction ft = fm.beginTransaction(); // 获取事务操作对象
        // 替换Fragment
        ft.replace(R.id.main_content, FragmentFactory.get(tag)); // 把帧布局替换成主界面正文Fragment对象
        // 提交事务
        ft.commit();
        // TODO: 2015/12/29 更改title 

    }

    public User getUserInfoByActivity(){
        if (null == user){
            return null;
        }
        return user;
    }
}
