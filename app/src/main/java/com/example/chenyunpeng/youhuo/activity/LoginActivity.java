package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.MyApplication;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.User;
import com.example.chenyunpeng.youhuo.utils.SPutils;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

    private android.support.design.widget.TextInputEditText account;
    private android.support.design.widget.TextInputEditText pasword;
    private android.widget.Button login;
    private android.widget.RelativeLayout logingroup;
    private Button qqlogin;
    private Button sinalogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        this.sinalogin = (Button) findViewById(R.id.sina_login);
        this.qqlogin = (Button) findViewById(R.id.qqlogin);
        this.logingroup = (RelativeLayout) findViewById(R.id.logingroup);
        this.login = (Button) findViewById(R.id.login);
        this.pasword = (TextInputEditText) findViewById(R.id.pasword);
        this.account = (TextInputEditText) findViewById(R.id.account);
        this.login = (Button) findViewById(R.id.login);
        this.pasword = (TextInputEditText) findViewById(R.id.pasword);
        this.account = (TextInputEditText) findViewById(R.id.account);
        login.setOnClickListener(this);
        sinalogin.setOnClickListener(this);
        qqlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //模拟登陆
        boolean equals = account.getText().toString().equals("123456");
        boolean equals1 = pasword.getText().toString().equals("123456");
        if(equals&&equals1){
            User user=new User();
            user.name=account+"";
           user.currentTime= System.currentTimeMillis();
            user.upDateTime=60*1000*60*24*30;
            SPutils.save("loggin",(user.upDateTime+user.currentTime)+"");
            Log.e("tag",""+SPutils.get("loggin").toString());
            MyApplication.user=user;
           // Snackbar.make(v,"登陆成功",Snackbar.LENGTH_LONG).show();
            onBackPressed();
        }else {
           //Snackbar.make(v,"账号或密码错误,请重新登陆",Snackbar.LENGTH_SHORT).show();
       toast("登陆失败");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_choose,R.anim.fade_out_boys);
    }

    public  void onClickLogin(View view){
      if(view.getId()==R.id.sina_login){
          ShareSDK.initSDK(this);
          Platform sina= ShareSDK.getPlatform(this, SinaWeibo.NAME);
          sina.SSOSetting(false);
          sina.authorize();
          sina.setPlatformActionListener(this);
      }else if(view.getId()==R.id.qqlogin){
          ShareSDK.initSDK(this);
          Platform wechat= ShareSDK.getPlatform(this, QQ.NAME);

          wechat.SSOSetting(false);
          wechat.authorize();
          wechat.setPlatformActionListener(this);
          /*String userIcon = wechat.getDb().getUserIcon();
          Log.e("tag",""+userIcon);*/
      }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        String userIcon = platform.getDb().getUserIcon();
        Log.e("tag",""+userIcon.toString());
        LoginState(platform);
        toast("分享完成");
    }

    private void LoginState(Platform platform) {
        PlatformDb db = platform.getDb();
        MyApplication.user.upDateTime=db.getExpiresTime();
        MyApplication.user.currentTime=System.currentTimeMillis();
        MyApplication.user.id=db.getUserId();
        MyApplication.user.token=db.getToken();
        MyApplication.user.headImage=db.getUserIcon();
        MyApplication.user.name=db.getUserName();
        SPutils.clear("loggin");
        SPutils.save("loggin",(db.getExpiresTime()+System.currentTimeMillis())+"");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
toast("分享失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
toast("取消分享");
    }
}
