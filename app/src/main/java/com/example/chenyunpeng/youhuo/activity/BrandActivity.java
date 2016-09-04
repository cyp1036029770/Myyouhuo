package com.example.chenyunpeng.youhuo.activity;

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

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.adapter.BrandAdapter;
import com.example.chenyunpeng.youhuo.bena.BranBean;
import com.example.chenyunpeng.youhuo.bena.FllowGridBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.diaglog.AddCartDiaglog;
import com.example.chenyunpeng.youhuo.diaglog.LoadDataDiaglog;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.example.chenyunpeng.youhuo.view.MyListView;
import com.example.chenyunpeng.youhuo.view.MyRadioButton;
import com.example.chenyunpeng.youhuo.view.StickScrollView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
public class BrandActivity extends BaseActivity {

    @Bind(R.id.back_brand_details)
    ImageButton backBrandDetails;
    @Bind(R.id.share_brand_details)
    ImageButton shareBrandDetails;
    @Bind(R.id.toolbar)
    RelativeLayout toolbar;
    @Bind(R.id.add_cart)
    MyRadioButton addCart;
    @Bind(R.id.bottombar)
    RelativeLayout bottombar;
    @Bind(R.id.stickscrollview)
    StickScrollView stickscrollview;
    private ImageButton backbranddetails;
    private ImageButton sharebranddetails;
    private MyRadioButton addcart;
    private android.support.v4.view.ViewPager pager;
    private android.widget.TextView tvtitle;
    private android.widget.TextView tvprice;
    private android.widget.TextView tvdiscount;
    private com.example.chenyunpeng.youhuo.view.MyListView lv;
    private String id;
    private FllowGridBean.FollowBean.GoodsBean goods;
   private List<ImageView> ivList=new ArrayList<>();
    private MyPagerAdapter adapter;
    private BrandAdapter brandAdapter;
    private List<BranBean.ImgvaleBean> imgvale;
    private List<BranBean.GoodsBean> goods1;
    private BranBean branBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);
      //  goods = (FllowGridBean.FollowBean.GoodsBean) getIntent().getSerializableExtra("goods");
        //id = goods.get_id();
        initView();
        initData();
        initAdapter();

    }

    private void initAdapter() {
        adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
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
                for(int i=0;i<img.size();i++){
                    ivList.add(getImageView(img.get(i)));
                }
                adapter.notifyDataSetChanged();
                goods1 = branBean.getGoods();
                BranBean.GoodsBean goodsBean = goods1.get(0);
                tvtitle.setText(goodsBean.getTitle()+"");
                tvprice.setText("$ "+goodsBean.getPrice()+"");
                tvdiscount.setText("$ "+goodsBean.getDiscount()+"");

                imgvale = branBean.getImgvale();
                brandAdapter = new BrandAdapter(imgvale,BrandActivity.this);
                lv.setAdapter(brandAdapter);
                dismissionLoadDialog();
            }

            @Override
            public void failrue(String e) {
                dismissionLoadDialog();
            }
        });
        stickscrollview.scrollTo(0,0);
    }

    private ImageView getImageView(BranBean.ImgBean imgBean) {
        ImageView iv=new ImageView(this);
        String imgpath = imgBean.getImgpath();
        Picasso.with(this).load(HttpModel.IMAGEHOST+imgpath).error(R.mipmap.setting_network).fit().into(iv);
        return iv;
    }

    private void initView() {
        this.stickscrollview = (StickScrollView) findViewById(R.id.stickscrollview);
        this.lv = (MyListView) findViewById(R.id.v);
        this.tvdiscount = (TextView) findViewById(R.id.tv_discount);
        this.tvprice = (TextView) findViewById(R.id.tv_price);
        this.tvtitle = (TextView) findViewById(R.id.tv_title);
        this.pager = (ViewPager) findViewById(R.id.pager);
        this.bottombar = (RelativeLayout) findViewById(R.id.bottombar);
        this.addcart = (MyRadioButton) findViewById(R.id.add_cart);
        this.toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        this.sharebranddetails = (ImageButton) findViewById(R.id.share_brand_details);
        this.backbranddetails = (ImageButton) findViewById(R.id.back_brand_details);

    }

    public void addCart(View view) {
        AddCartDiaglog addCartDiaglog = new AddCartDiaglog(this, branBean);
     addCartDiaglog.show();
 }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_pinpai_in,R.anim.translate_xiangqing_out);
    }
    class  MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return ivList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object==view;
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
