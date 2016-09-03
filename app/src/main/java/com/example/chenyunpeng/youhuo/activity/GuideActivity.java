package com.example.chenyunpeng.youhuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.example.chenyunpeng.youhuo.utils.SPutils;

import java.util.ArrayList;
import java.util.List;
public class GuideActivity extends BaseActivity {
    private List<View> list;
    private ImageButton imageButton;
    private ViewPager pagerguide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SPutils.save("isFirst","true");
        init();
    }

    private void init() {
        initView();
        initData();
        initAdapter();
        initListenner();
    }

    private void initView() {
        this.pagerguide = (ViewPager) findViewById(R.id.pager_guide);

    }

    private void initListenner() {
         imageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(GuideActivity.this,ChooseActivity.class));
                 overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                 finish();
             }
         });
    }

    private void initAdapter() {
        pagerguide.setAdapter(new MypagerAdapter());
    }

    private void initData() {
       list=new ArrayList<>();
        list.add(getView(R.mipmap.guide_1,false));
        list.add(getView(R.mipmap.guide_2,false));
        list.add(getView(R.mipmap.guide_3,false));
        list.add(getView(R.mipmap.guide_4,false));
        list.add(getView(R.mipmap.guide_5,true));
    }


    private RelativeLayout getView(int resId, boolean b) {
        RelativeLayout relativeLayout=new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(screenwidth,screenheight);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(resId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams);
        relativeLayout.addView(imageView);
        if(b){
            imageButton = new ImageButton(this);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(Dp2Px.dp2px(120),Dp2Px.dp2px(30));
            imageButton.setImageResource(R.drawable.select_guide_btn_start);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.bottomMargin= Dp2Px.dp2px(40);
            relativeLayout.addView(imageButton,params);
        }
        return relativeLayout;
    }
 class  MypagerAdapter extends PagerAdapter{

     @Override
     public int getCount() {
         return list.size();
     }

     @Override
     public boolean isViewFromObject(View view, Object object) {
         return object==view;
     }

     @Override
     public Object instantiateItem(ViewGroup container, int position) {
         View view = list.get(position);
         container.addView(view);
         return view;
     }

     @Override
     public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView((View) object);
     }
 }

}
