package com.paxw.weiba.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paxw.weiba.R;
import com.paxw.weiba.utils.AccessTokenKeeper;
import com.paxw.weiba.utils.ToastUtil;
import com.paxw.weiba.utils.WeiBoConstants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.LogoutAPI;
import com.sina.weibo.sdk.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {


    private TextView version;
    private AuthInfo mAuthInfo;
    private LoginButton login;
    private Button logout;
    private Timer time;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private SsoHandler mSsoHandler;

    protected void initView() {
        setContentView(R.layout.activity_first);
        // FIXME: 2015/12/24 如果有token应该跳过这一步


        mAuthInfo = new AuthInfo(this, WeiBoConstants.APP_KEY,
                WeiBoConstants.REDIRECT_URL, WeiBoConstants.SCOPE);
        version = (TextView) findViewById(R.id.version_text);
        version.setText("版本号:" + getVersion());
        login = (LoginButton) findViewById(R.id.sina_login);

        login.setWeiboAuthInfo(mAuthInfo, new AuthListener());
        // 进入该界面时就想让该按钮显示"注销"，请放开以下代码
//        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(this);
//        login.setLogoutInfo(token, mLogoutListener);
        logout = (Button) findViewById(R.id.sina_loginout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != time) time.cancel();
                new LogoutAPI(SplashActivity.this, WeiBoConstants.APP_KEY,
                        AccessTokenKeeper.readAccessToken(SplashActivity.this)).logout(new RequestListener() {
                    @Override
                    public void onComplete(String response) {
                        login.setVisibility(View.VISIBLE);
                        logout.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                String value = obj.getString("result");

                                if ("true".equalsIgnoreCase(value)) {
                                    AccessTokenKeeper.clear(SplashActivity.this);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {

                    }
                });
            }
        });
        time = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this , MainActivity.class));
                        finish();
                    }
                });
            }
        };
        Oauth2AccessToken oauth2AccessToken = AccessTokenKeeper.readAccessToken(this);
        if (null!= oauth2AccessToken && oauth2AccessToken.isSessionValid() && !TextUtils.isEmpty(oauth2AccessToken.getToken())){
            login.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            time.schedule(task, 2000);



        }
    }

    public String getVersion() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private Oauth2AccessToken mAccessToken;
    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {

            mAccessToken = Oauth2AccessToken.parseAccessToken(values);

            String phoneNum = mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {

                AccessTokenKeeper.writeAccessToken(SplashActivity.this, mAccessToken);

                ToastUtil.showToast(getString(R.string.oauth2accessSuccess));
                // TODO: 2015/12/24 跳转到主页
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = "失败";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(SplashActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(SplashActivity.this,
                   "取消了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(SplashActivity.this,
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login.onActivityResult(requestCode, resultCode, data);
    }


}
