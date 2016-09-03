package com.example.chenyunpeng.youhuo.utils;

import android.os.Looper;
import android.text.TextUtils;

import com.example.chenyunpeng.youhuo.MyApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chenyunpeng on 2016/8/25.
 */
public class HttpUtils {
    public  static  android.os.Handler handler=new android.os.Handler(Looper.getMainLooper());
    private static OkHttpClient okHttpClient;
       static {
           okHttpClient=new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                   .readTimeout(60,TimeUnit.SECONDS)
                   .writeTimeout(60,TimeUnit.SECONDS)
                   .cache(new Cache(MyApplication.app.getCacheDir(),1024*8*1024))
                   .build();


       }
    private DataCallBack callback;
    public  HttpUtils post(String path,String reequestBody){
      FormBody.Builder  builder = new FormBody.Builder();
       if(!TextUtils.isEmpty(reequestBody)){
           String[] split = reequestBody.split("&");
           for(int i=0;i<split.length;i++){
               String[] split1 = split[i].split("=");
               String s = split1[0];
               String data=split1[1];
              ;builder.add(s,data);
           }
       }
        Request request=new Request.Builder().url(path).post(builder.build()).build();
          okHttpClient.newCall(request).enqueue(new Callback() {
              @Override
              public void onFailure(Call call, final IOException e) {
                  handler.post(new Runnable() {
                      @Override
                      public void run() {
                      if(callback!=null){
                          callback.failrue(e.getMessage());
                      }
                      }
                  });
              }

              @Override
              public void onResponse(Call call, final Response response) throws IOException {
                  final String string = response.body().string();
                  if(TextUtils.isEmpty(string)){
                      handler.post(new Runnable() {
                          @Override
                          public void run() {
                              if(callback!=null){
                                callback.failrue("数据为空");
                              }
                          }
                      });
                  }else {
                      handler.post(new Runnable() {
                          @Override
                          public void run() {
                           if(callback!=null){
                               callback.successful(string);
                           }
                          }
                      });
                  }

              }
          });
        return  this;
    }
    public  interface  DataCallBack{
        void successful(String data);
         void failrue(String e);
    }
    public  void DataCallBack(DataCallBack callBack){
        this.callback=callBack;
    }

}
