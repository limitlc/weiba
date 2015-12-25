package com.paxw.weiba.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lichuang on 2015/12/25.
 */
public class MyTouXiang extends ImageView implements View.OnClickListener{
    public MyTouXiang(Context context) {
        super(context);
    }

    public MyTouXiang(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouXiang(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {
        // FIXME: 2015/12/25切换账号还是查看大图呢

    }
}
