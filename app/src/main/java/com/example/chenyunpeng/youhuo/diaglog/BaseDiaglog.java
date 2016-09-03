package com.example.chenyunpeng.youhuo.diaglog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by chenyunpeng on 2016/8/28.
 */
public class BaseDiaglog extends Dialog {
    Activity a;
    int width;
    int height;

    public BaseDiaglog(Context context) {
        super(context);
        a = (Activity) context;
        width = a.getWindowManager().getDefaultDisplay().getWidth();
        height = a.getWindowManager().getDefaultDisplay().getHeight();
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }
}
