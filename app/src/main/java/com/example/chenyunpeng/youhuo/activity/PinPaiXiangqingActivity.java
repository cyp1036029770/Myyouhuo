package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.fragment.PinpaijieshaoFragment;
import com.example.chenyunpeng.youhuo.fragment.XiangqingFragment;

public class PinPaiXiangqingActivity extends BaseActivity {
    private PinpaijieshaoFragment pinpaijieshaoFragment;
    private FragmentManager manager;
    private android.widget.ImageButton backtoobar;
    private android.widget.Button titletvtoobar;
    private ImageView titleivtoobar;
    private TextView selecttoolbar;
    private ImageView sharetoolbar;
    private RelativeLayout toolbar;
    private RelativeLayout group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_pin_pai_xiangqing);
        intiView();
        initFragment();
        initListener();
    }

    private void initListener() {
        titletvtoobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = pinpaijieshaoFragment.haveFragment;
                int backStackEntryCount = manager.getBackStackEntryCount();
                if (b && backStackEntryCount == 1) {
                    manager.popBackStack(PinpaijieshaoFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    manager.beginTransaction().add(R.id.group, pinpaijieshaoFragment, PinpaijieshaoFragment.class.getSimpleName())
                            .addToBackStack(PinpaijieshaoFragment.class.getSimpleName()).commit();
                }
            }
        });
        backtoobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    private void initFragment() {
            pinpaijieshaoFragment = new PinpaijieshaoFragment();
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.group,new XiangqingFragment()).commit();
    }

    private void intiView() {
        this.group = (RelativeLayout) findViewById(R.id.group);
        this.toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        this.sharetoolbar = (ImageView) findViewById(R.id.share_toolbar);
        this.selecttoolbar = (TextView) findViewById(R.id.select_toolbar);
        this.titleivtoobar = (ImageView) findViewById(R.id.title_iv_toobar);
        this.titletvtoobar = (Button) findViewById(R.id.title_tv_toobar);
        this.backtoobar = (ImageButton) findViewById(R.id.back_toobar);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_pinpai_in,R.anim.translate_xiangqing_out);
    }
}
