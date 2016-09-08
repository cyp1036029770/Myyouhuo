package com.example.chenyunpeng.youhuo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.chenyunpeng.youhuo.bena.PushBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.IOException;

import cn.bmob.push.PushConstants;

/**
 * Created by chenyunpeng on 2016/9/8.
 */
public class MyPushMessageReceiver extends BroadcastReceiver {

    private Bitmap bitmap;
 static  android.os.Handler handler=new Handler();
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(final Context context, Intent intent) {

        NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            String msg = intent.getStringExtra("msg");

            PushBean pushBean = new Gson().fromJson(msg, PushBean.class);
            final PushBean.ApsBean aps = pushBean.getAps();

            Notification notification=new Notification.Builder(context)
                    .setTicker(aps.getName())
                    .setContentText(aps.getAlert()).setContentTitle(aps.getAlert())
                 .setSmallIcon(R.mipmap.search_icon)
                    .setAutoCancel(true)
                    .build();
           manager.notify(1,notification);


        }
    }
}
