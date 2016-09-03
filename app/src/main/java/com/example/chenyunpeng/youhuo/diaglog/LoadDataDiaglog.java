package com.example.chenyunpeng.youhuo.diaglog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;

/**
 * Created by chenyunpeng on 2016/8/28.
 */
public class LoadDataDiaglog extends BaseDiaglog {
    private final AnimationDrawable anim;

    public LoadDataDiaglog(Context context) {
        super(context);
        ImageView iv=new ImageView(a);
        iv.setBackgroundResource(R.drawable.load_donghua);
        setContentView(iv);
        getWindow().getAttributes().dimAmount=0;
        getWindow().setLayout( Dp2Px.dp2px(80), Dp2Px.dp2px(50));
        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        anim = (AnimationDrawable) iv.getBackground();
        anim.start();
    }
    @Override
    public void dismiss() {
        super.dismiss();
        anim.stop();
    }
}
