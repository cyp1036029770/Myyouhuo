package com.example.chenyunpeng.youhuo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.AllBannerBean;
import com.example.chenyunpeng.youhuo.fragment.PinpaijieshaoFragment;
import com.example.chenyunpeng.youhuo.fragment.XiangqingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PinPaiXiangqingActivity extends BaseActivity {
    @Bind(R.id.back_toobar)
    ImageButton backToobar;
    @Bind(R.id.title_tv_toobar)
    TextView titleTvToobar;
    @Bind(R.id.title_iv_toobar)
    ImageView titleIvToobar;
    @Bind(R.id.select_toolbar)
    TextView selectToolbar;
    @Bind(R.id.share_toolbar)
    ImageView shareToolbar;
    @Bind(R.id.toolbar)
    RelativeLayout toolbar;
    @Bind(R.id.group)
    RelativeLayout group;
    private PinpaijieshaoFragment pinpaijieshaoFragment;
    private FragmentManager manager;
    public AllBannerBean.BrandBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_pai_xiangqing);
        ButterKnife.bind(this);
        bean = (AllBannerBean.BrandBean) getIntent().getSerializableExtra("bean");
        String name = bean.getName();

        if(!TextUtils.isEmpty(name)){
            Log.e("tag",""+name.toString());
           titleTvToobar.setText(name+"");

        }
        initFragment();
        initListener();
    }
    private void initListener() {
        titleTvToobar.setOnClickListener(new View.OnClickListener() {
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
        backToobar.setOnClickListener(new View.OnClickListener() {
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
        manager.beginTransaction().replace(R.id.group, new XiangqingFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_pinpai_in, R.anim.translate_xiangqing_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.back_toobar, R.id.share_toolbar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_toobar:
                onBackPressed();
                break;
            case R.id.share_toolbar:
              shouShareDiaglog();
                break;
        }
    }
}
