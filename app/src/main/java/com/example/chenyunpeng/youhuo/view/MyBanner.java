package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BannerBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/26.
 */
public class MyBanner extends RelativeLayout {

    private List<BannerBean> bannerBeanList;
    private List<ImageView> imageViewList;
    private LinearLayout dotGroup;
    private ViewPager pager;
    private boolean isDrag;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(imageViewList.size()>0){
                int i = pager.getCurrentItem() + 1;
                pager.setCurrentItem(i);
            }
            handler.sendEmptyMessageDelayed(0,3500);
        }
    };

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        bannerBeanList = new ArrayList<>();
        imageViewList = new ArrayList<>();
    }

    private void init() {
        pager = new ViewPager(getContext());
        addView(pager);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               if(imageViewList.size()>0){
                   selectdot(position%imageViewList.size());
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
              if(state==ViewPager.SCROLL_STATE_DRAGGING){
                  stop();
                  isDrag = true;
              }else {
                  if(isDrag){
                      isDrag=false;
                      start();
                  }
              }
            }
        });
        dotGroup = new LinearLayout(getContext());
        dotGroup.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, Dp2Px.dp2px(20));
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        params.bottomMargin = Dp2Px.dp2px(5);
        dotGroup.setLayoutParams(params);
        addView(dotGroup);

    }

    public void stop() {
      handler.removeCallbacksAndMessages(null);
    }
    public void start(){
  handler.sendEmptyMessageDelayed(0,3500);
    }

    private void selectdot(int position) {
        if(position<dotGroup.getChildCount()){
            int childCount = dotGroup.getChildCount();
            for(int i=0;i<childCount;i++){
                dotGroup.getChildAt(i).setSelected(position==i);
            }
        }
    }

    private View getDot(boolean isWhite) {
        View dotView = new View(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Dp2Px.dp2px(10), Dp2Px.dp2px(10));
        params.leftMargin = Dp2Px.dp2px(10);
        dotView.setLayoutParams(params);
        dotView.setBackgroundResource(R.drawable.select_dot);
        if (isWhite) {
            dotView.setSelected(true);
        }
        return dotView;
    }

    private ImageView getImageView(String path) {
        ImageView iv = new ImageView(getContext());
        Picasso.with(getContext()).load(HttpModel.IMAGEHOST+path).fit().placeholder(R.mipmap.setting_network).error(R.mipmap.setting_network).into(iv);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return iv;
    }

    public void LoadData(String path, String body) {
        new HttpUtils().post(path, body).DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                List<BannerBean> list = new Gson().fromJson(data, new TypeToken<List<BannerBean>>() {
                }.getType());

                bannerBeanList.clear();
                imageViewList.clear();
                bannerBeanList.addAll(list);
                for(int i=0;i<bannerBeanList.size();i++){
                    BannerBean bannerBean = bannerBeanList.get(i);
                    ImageView imageView = getImageView(bannerBean.getImgpath());
                    imageViewList.add(imageView);
                }
                for(int i=0;i<bannerBeanList.size();i++){
                  dotGroup.addView(getDot(i==0));
                }
                pager.setAdapter(new MyPagerAdapter());
                pager.setCurrentItem(1000*bannerBeanList.size());

            }

            @Override
            public void failrue(String e) {

                Log.e("tag",""+e);
            }
        });
    }
     class  MyPagerAdapter extends PagerAdapter{

         @Override
         public int getCount() {
             return Integer.MAX_VALUE;
         }

         @Override
         public boolean isViewFromObject(View view, Object object) {
             return view==object;
         }

         @Override
         public Object instantiateItem(ViewGroup container, int position) {
             ImageView imageView = imageViewList.get(position % imageViewList.size());
             ViewParent parent = imageView.getParent();
             if(parent!=null){
                 ((ViewGroup) (parent)).removeView(imageView);
             }
             container.addView(imageView);
             return imageView;
         }

         @Override
         public void destroyItem(ViewGroup container, int position, Object object) {
             //super.destroyItem(container, position, object);
         }
     }

}
