package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.MyApplication;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.User;
import com.example.chenyunpeng.youhuo.utils.SPutils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private android.support.design.widget.TextInputEditText account;
    private android.support.design.widget.TextInputEditText pasword;
    private android.widget.Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        this.login = (Button) findViewById(R.id.login);
        this.pasword = (TextInputEditText) findViewById(R.id.pasword);
        this.account = (TextInputEditText) findViewById(R.id.account);
        login.setOnClickListener(this);
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
            MyApplication.user=user;
            Snackbar.make(v,"登陆成功",Snackbar.LENGTH_LONG).show();
            onBackPressed();
        }else {
           Snackbar.make(v,"账号或密码错误,请重新登陆",Snackbar.LENGTH_SHORT).show();
  /*     toast("登陆失败");*/
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_choose,R.anim.fade_out_boys);
    }
}
