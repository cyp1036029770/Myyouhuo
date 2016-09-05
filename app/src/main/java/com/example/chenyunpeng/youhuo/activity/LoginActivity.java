package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.chenyunpeng.youhuo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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
        //模拟本地登陆
    }
}
