package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chenyunpeng.youhuo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/9/9.
 */
public class ZhiFuActivity extends BaseActivity {

    private ListView lv;
    private ImageView backpress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        lv = (ListView) findViewById(R.id.lv);
        backpress = (ImageView) findViewById(R.id.back_press);
        View inflate = View.inflate(this, R.layout.zhifu_head, null);
         lv.addHeaderView(inflate);
        initExpandData();
    }

    private void initExpandData() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_boys,R.anim.fade_out_choose);
    }
}
