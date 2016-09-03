package com.example.chenyunpeng.youhuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.chenyunpeng.youhuo.activity.BaseActivity;
import com.example.chenyunpeng.youhuo.activity.GouWuCheActivity;
import com.example.chenyunpeng.youhuo.fragment.FeileiFragment;
import com.example.chenyunpeng.youhuo.fragment.GouWuCheFragment;
import com.example.chenyunpeng.youhuo.fragment.GuangFragment;
import com.example.chenyunpeng.youhuo.fragment.MineFragment;
import com.example.chenyunpeng.youhuo.fragment.ShouyeFragment;
import com.example.chenyunpeng.youhuo.view.MyRadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioGroup buttonGroup;
    private FrameLayout FragmentGroup;
    private FragmentManager manager;
    private com.example.chenyunpeng.youhuo.view.MyRadioButton shouye;
    private com.example.chenyunpeng.youhuo.view.MyRadioButton feilei;
    private com.example.chenyunpeng.youhuo.view.MyRadioButton guang;
    private com.example.chenyunpeng.youhuo.view.MyRadioButton gouwuche;
    private com.example.chenyunpeng.youhuo.view.MyRadioButton mine;
    HashMap<String, Fragment> fragmentHashMap = new HashMap<>();
    private List<MyRadioButton> radioButtonList;
    private String fragmentTag = "";
    private String ccurrentTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initRadioList();
    }

    private void initView() {
        this.FragmentGroup = (FrameLayout) findViewById(R.id.FragmentGroup);
        this.buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        this.mine = (MyRadioButton) findViewById(R.id.mine);
        this.gouwuche = (MyRadioButton) findViewById(R.id.gouwuche);
        this.guang = (MyRadioButton) findViewById(R.id.guang);
        this.feilei = (MyRadioButton) findViewById(R.id.feilei);
        this.shouye = (MyRadioButton) findViewById(R.id.shouye);
         shouye.setOnClickListener(this);
        feilei.setOnClickListener(this);
        guang.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        mine.setOnClickListener(this);

    }

    private void initRadioList() {
        radioButtonList = new ArrayList<>();


    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        fragmentHashMap.put(ShouyeFragment.class.getSimpleName(), new ShouyeFragment());
        fragmentHashMap.put(FeileiFragment.class.getSimpleName(), new FeileiFragment());
        fragmentHashMap.put(GuangFragment.class.getSimpleName(), new GuangFragment());
        fragmentHashMap.put(MineFragment.class.getSimpleName(), new MineFragment());
        fragmentHashMap.put(GouWuCheFragment.class.getSimpleName(), new GouWuCheFragment());
        replaceFragment(ShouyeFragment.class.getSimpleName());
    }

    private void replaceFragment(String simpleName) {
        Fragment fragment = fragmentHashMap.get(simpleName);
        if (!fragmentTag.equals(simpleName)) {
            ccurrentTag = simpleName;
            manager.beginTransaction().replace(R.id.FragmentGroup, fragment, simpleName).commit();
        }
        fragmentTag = simpleName;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_choose, R.anim.fade_out_boys);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouye:
                replaceFragment(ShouyeFragment.class.getSimpleName());
                break;
            case R.id.feilei:
                replaceFragment(FeileiFragment.class.getSimpleName());
                break;
            case R.id.guang:
                replaceFragment(GuangFragment.class.getSimpleName());
                break;
            case R.id.gouwuche:
                //此处需要判断是否登陆,如果未登录切换的是fragment,登录后切换的是fragment
                replaceFragment(GouWuCheFragment.class.getSimpleName());
                break;
            case R.id.mine:
                replaceFragment(MineFragment.class.getSimpleName());
                break;
        }
    }


    private void statGouwuChe() {
        startActivity(new Intent(MainActivity.this, GouWuCheActivity.class));
        overridePendingTransition(R.anim.gouwuche_in, 0);
    }
}
