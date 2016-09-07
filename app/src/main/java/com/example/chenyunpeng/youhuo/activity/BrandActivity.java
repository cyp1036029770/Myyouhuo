package com.example.chenyunpeng.youhuo.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.share.ShareDiaglog;
import com.example.chenyunpeng.youhuo.MyApplication;
import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.BrandAdapter;
import com.example.chenyunpeng.youhuo.bena.BranBean;
import com.example.chenyunpeng.youhuo.bena.FllowGridBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.diaglog.AddCartDiaglog;
import com.example.chenyunpeng.youhuo.event.addCartEvent;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.example.chenyunpeng.youhuo.view.MyListView;
import com.example.chenyunpeng.youhuo.view.MyRadioButton;
import com.example.chenyunpeng.youhuo.view.StickScrollView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandActivity extends BaseActivity {
    private String id;
    private FllowGridBean.FollowBean.GoodsBean goods;
    private List<ImageView> ivList = new ArrayList<>();
    private MyPagerAdapter adapter;
    private BrandAdapter brandAdapter;
    private List<BranBean.ImgvaleBean> imgvale;
    private List<BranBean.GoodsBean> goods1;
    private BranBean branBean;
    private ImageButton backbranddetails;
    private ImageButton sharebranddetails;
    private RelativeLayout toolbar;
    private MyRadioButton addcart;
    private RelativeLayout bottombar;
    private ViewPager pagerbrand;
    private TextView tvtitle;
    private TextView tvprice;
    private TextView tvdiscount;
    private MyListView v;
    private StickScrollView stickscrollview;
    private RelativeLayout group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);
       // goods = (FllowGridBean.FollowBean.GoodsBean) getIntent().getSerializableExtra("goods");
      //  id = goods.get_id();
         initView();
        initData();
        initAdapter();

    }

    private void initView() {
        this.group = (RelativeLayout) findViewById(R.id.group);
        this.stickscrollview = (StickScrollView) findViewById(R.id.stickscrollview);
        this.v = (MyListView) findViewById(R.id.v);
        this.tvdiscount = (TextView) findViewById(R.id.tv_discount);
        this.tvprice = (TextView) findViewById(R.id.tv_price);
        this.tvtitle = (TextView) findViewById(R.id.tv_title);
        this.pagerbrand = (ViewPager) findViewById(R.id.pager_brand);
        this.bottombar = (RelativeLayout) findViewById(R.id.bottombar);
        this.addcart = (MyRadioButton) findViewById(R.id.add_cart);
        this.toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        this.sharebranddetails = (ImageButton) findViewById(R.id.share_brand_details);
        this.backbranddetails = (ImageButton) findViewById(R.id.back_brand_details);
        sharebranddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shouShareDiaglog();
            }
        });
    }


    private void initAdapter() {



    }

    private void initData() {
        //zhuyiba 1huancheng id
        showLoadDialog();
        new HttpUtils().post(HttpModel.GOODSDETAILS, "parames={\\\"goods_id\\\":\\\"\"" + 1 + "\"\\\"}").DataCallBack(new HttpUtils.DataCallBack() {
            @Override
            public void successful(String data) {
                branBean = new Gson().fromJson(data, BranBean.class);
                //viewpagerde shuju
                List<BranBean.ImgBean> img = branBean.getImg();
                for (int i = 0; i < img.size(); i++) {
                    ivList.add(getImageView(img.get(i)));
                }
                goods1 = branBean.getGoods();
                BranBean.GoodsBean goodsBean = goods1.get(0);
                tvtitle.setText(goodsBean.getTitle() + "");
                tvprice.setText("$ " + goodsBean.getPrice() + "");
                tvdiscount.setText("$ " + goodsBean.getDiscount() + "");
                imgvale = branBean.getImgvale();
                brandAdapter = new BrandAdapter(imgvale, BrandActivity.this);
                v.setAdapter(brandAdapter);
                stickscrollview.scrollTo(0,0);
                adapter = new MyPagerAdapter();
                pagerbrand.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void failrue(String e) {
            }
        });
        dismissionLoadDialog();
    }

    private ImageView getImageView(BranBean.ImgBean imgBean) {
        ImageView iv = new ImageView(this);
        String imgpath = imgBean.getImgpath();
        Picasso.with(this).load(HttpModel.IMAGEHOST + imgpath).error(R.mipmap.setting_network).fit().into(iv);
        return iv;
    }

    public void addCart(View view) {
        AddCartDiaglog addCartDiaglog = new AddCartDiaglog(this, branBean);
        addCartDiaglog.show();
        addCartDiaglog.setOnDisMissListenner(new AddCartDiaglog.OnDisMissListenner() {
            @Override
            public void dismiss(int num) {
                AddCartAnimation(num);
            }
        });


    }

    private void AddCartAnimation(final int num) {
        final CircleImageView civ = new CircleImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Dp2Px.dp2px(40), Dp2Px.dp2px(40));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        civ.setBorderColor(Color.GRAY);
        civ.setBorderWidth(Dp2Px.dp2px(2));
        civ.setLayoutParams(params);
        Picasso.with(this).load(HttpModel.IMAGEHOST + branBean.getImg().get(0).getImgpath()).fit().into(civ);
        group.addView(civ);
        group.post(new Runnable() {
            @Override
            public void run() {
                final int[] ivLocation = new int[2];
                final int[] cartLocation = new int[2];
                civ.getLocationOnScreen(ivLocation);
                addcart.getLocationOnScreen(cartLocation);
               /* Log.e("tag2",""+cartLocation[0]+"----------->"+cartLocation[1]);
                Log.e("tag3",""+ivLocation[0]+"----------->"+ivLocation[1]);*/
                ObjectAnimator animator = ObjectAnimator.ofFloat(civ, "translationX", 0, cartLocation[0] - ivLocation[0]);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float v = 1 - civ.getTranslationX() / (cartLocation[0] - ivLocation[0]);
                        civ.setScaleX(v);
                        civ.setScaleY(v);
                        civ.setAlpha(v + 0.2f);
                        civ.setTranslationY((cartLocation[1] - ivLocation[1] - addcart.getHeight()) * 1.0f / (cartLocation[0] - ivLocation[0] + addcart.getWidth() / 2) * civ.getTranslationX());
                    }
                });
               animator.addListener(new Animator.AnimatorListener() {
                   @Override
                   public void onAnimationStart(Animator animation) {

                   }

                   @Override
                   public void onAnimationEnd(Animator animation) {
                      group.removeView(civ);
                       MyApplication.count+=num;
                       addcart.setRedDotText(MyApplication.count);
                       EventBus.getDefault().post(new addCartEvent(MyApplication.count));
                   }

                   @Override
                   public void onAnimationCancel(Animator animation) {

                   }

                   @Override
                   public void onAnimationRepeat(Animator animation) {

                   }
               });
               animator.start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_pinpai_in, R.anim.translate_xiangqing_out);
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ivList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = ivList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }


}
