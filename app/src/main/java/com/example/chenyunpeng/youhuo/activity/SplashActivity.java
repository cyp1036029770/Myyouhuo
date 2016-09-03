package com.example.chenyunpeng.youhuo.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.SPutils;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class SplashActivity extends BaseActivity {
    private android.widget.RelativeLayout splashgroup;
    private Animator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.splashgroup = (RelativeLayout) findViewById(R.id.splash_group);
        splashgroup.setTranslationY(-screenheight);
        init();
    }

    private void init() {
        animator = ObjectAnimator.ofFloat(splashgroup,"translationY",-screenheight,0);
        animator.setInterpolator(new BounceInterpolator());

        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                String isFirst = SPutils.get("isFirst");
                if(TextUtils.isEmpty(isFirst)||isFirst.equals("false")){
                    startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }else if(isFirst.equals("true")){
                    startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            };

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            animator.start();
        }
    }
}
