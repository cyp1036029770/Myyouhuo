package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.HostBannerBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/25.
 */
public class MyScollView extends RelativeLayout {

    private TextView textView;
    private RecyclerView recycle;
    private List<HostBannerBean.BrandBean> brandBeanListst;


    public MyScollView(Context context) {
        this(context,null);
    }

    public MyScollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_item_pinpai, null);
        addView(inflate);
        textView = (TextView) inflate.findViewById(R.id.tv);
        recycle = (RecyclerView) inflate.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
     public  void loadData(String path,String body){
         new HttpUtils().post(path,body).DataCallBack(new HttpUtils.DataCallBack() {
             @Override
             public void successful(String data) {
                 HostBannerBean hostBannerBean = new Gson().fromJson(data, HostBannerBean.class);
                 brandBeanListst = hostBannerBean.getBrand();
                 Log.e("tagmyscllview",""+ brandBeanListst.toString());
                 recycle.setAdapter(new MyRecycleViewAdapter());
             }

             @Override
             public void failrue(String e) {
                  Log.e("tag",""+e);
             }
         });
     }

     class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

         @Override
         public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             return new MyViewHolder(View.inflate(getContext(),R.layout.recycle_item_pinpai,null));
         }

         @Override
         public void onBindViewHolder(MyViewHolder holder, int position) {
             HostBannerBean.BrandBean brandBean = brandBeanListst.get(position);
             Log.e("tag",""+brandBean.getName());
             holder.tv.setText(brandBean.getName()+"");
             Picasso.with(getContext()).load(HttpModel.IMAGEHOST+brandBean.getImgpath()).error(R.mipmap.setting_network).placeholder(R.mipmap.setting_network).into(holder.iv);

         }

         @Override
         public int getItemCount() {
             return brandBeanListst.size();
         }
     }
     class   MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
         ImageView iv;

         public MyViewHolder(View itemView) {
             super(itemView);
             tv = (TextView) itemView.findViewById(R.id.pinpai_tv);
             iv = (ImageView) itemView.findViewById(R.id.imgage_recycle_pinpai);
         }
     }
}
