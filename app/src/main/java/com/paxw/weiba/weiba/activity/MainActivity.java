package com.paxw.weiba.weiba.activity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.paxw.weiba.weiba.R;
import com.paxw.weiba.weiba.utils.Logs;
import com.paxw.weiba.weiba.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView shareList;
    private String[] shareItems = new String[]{
            "新浪","空间","微信"
    } ;

    @Override
    protected void initView() {
        setContentView(R.layout.main_layout);
        shareList = (ListView) findViewById(R.id.share_item_listview);
        shareList.setAdapter(new ArrayAdapter<String >(this,R.layout.share_item,shareItems));
        shareList.setOnItemClickListener(this);

    }
    SHARE_MEDIA platform = null;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        new ShareAction(this)
//                .setPlatform(SHARE_MEDIA.DOUBAN)
//                .setCallback(umShareListener)
//                .withText("Hello 豆瓣")
//                .withMedia(image)
//                .share();
        switch (position){
            case 1:
                platform = SHARE_MEDIA.QZONE;
                break;
            case 2:
                platform = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case 0:
                platform = SHARE_MEDIA.SINA;
                break;
        }
        showShareDialog();







    }
    String pathTitle  = "";
    AlertDialog dialog =null;
    private void showShareDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View contentView = View.inflate(this, R.layout.sharedialog,
                null);
        dialog.setView(contentView, 0, 0, 0, 0);
       final TextView content = (TextView) contentView.findViewById(R.id.share_content);
        Button btn_share = (Button) contentView.findViewById(R.id.share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logs.e();
                new ShareAction(MainActivity.this)
                        .setPlatform(platform)
                        .setCallback(umShareListener)
                        .withText(content.getText().toString())
//                        .withMedia(image)
                        .share();


            }
        });
        dialog.show();

    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtil.showToast(platform + " 分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            ToastUtil.showToast(platform + " 失败"+throwable.getLocalizedMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}
