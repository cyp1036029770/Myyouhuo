package com.example.chenyunpeng.youhuo.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.diaglog.AddCartDiaglog;
import com.example.chenyunpeng.youhuo.view.MyRadioButton;
import com.example.chenyunpeng.youhuo.view.StickScrollView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);

    }

 public  void addCart(View view){
     AddCartDiaglog addCartDiaglog=new AddCartDiaglog(this);
     addCartDiaglog.show();
 }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.translate_pinpai_in,R.anim.translate_xiangqing_out);
    }
}
