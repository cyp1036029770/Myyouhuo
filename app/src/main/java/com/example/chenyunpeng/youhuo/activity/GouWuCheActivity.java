package com.example.chenyunpeng.youhuo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.chenyunpeng.youhuo.R;
public class GouWuCheActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       overridePendingTransition(0,R.anim.gouwuche_out);
    }
}
