package com.example.chenyunpeng.youhuo.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import com.example.chenyunpeng.youhuo.R;
public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private ValueAnimator animator;
    private ImageView delayimage;
    private ImageView delayimage2;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome);
        initView();

        delayimage.setScaleY(1.5f);
        delayimage.setScaleX(1.5f);
        start.setPressed(false);
        init();
    }
    private void initView() {
        this.start = (Button) findViewById(R.id.start);
        this.delayimage2 = (ImageView) findViewById(R.id.delay_image2);
        this.delayimage = (ImageView) findViewById(R.id.delay_image);
        start.setOnClickListener(this);
    }

    private void init() {
        delayimage.post(new Runnable() {
            @Override
            public void run() {
                animator = ValueAnimator.ofFloat(1.5f,1.0f);
                animator.setDuration(2000);
                AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
                alphaAnimation.setDuration(3000);
                alphaAnimation.setFillAfter(true);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        delayimage2.clearAnimation();
                        delayimage2.setVisibility(View.GONE);
                        animator.start();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
               animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   @Override
                   public void onAnimationUpdate(ValueAnimator animation) {
                       float animatedValue = (float) animation.getAnimatedValue();
                       delayimage.setScaleX(animatedValue);
                       delayimage.setScaleY(animatedValue);
                   }
               });
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                delayimage2.clearAnimation();
                                animator.removeAllListeners();
                                startActivity(new Intent(WelcomeActivity.this,ChooseActivity.class));
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                            }
                        },300);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                delayimage2.setAnimation(alphaAnimation);
            }
        });

    }

    @Override
    public void onClick(View v) {
        delayimage2.clearAnimation();
        animator.removeAllListeners();
        startActivity(new Intent(WelcomeActivity.this,ChooseActivity.class));
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        finish();
    }
}
