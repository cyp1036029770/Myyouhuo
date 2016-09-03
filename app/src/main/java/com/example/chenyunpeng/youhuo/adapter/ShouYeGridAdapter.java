package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.HomeBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/9/3.
 */
public class ShouYeGridAdapter extends BaseAdapter {
    private List<List<HomeBean.BrandBean>> list;
    private Context ctx;


    public ShouYeGridAdapter(List<List<HomeBean.BrandBean>> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_home_gridview, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
           holder= (ViewHolder) convertView.getTag();
        }

        List<HomeBean.BrandBean> beanList = list.get(position);
        String name = beanList.get(0).getName();
        holder.tv.setText(name+"");
        List<ImageView> ivlist=new ArrayList();
        ivlist.add(holder.iv1);
        ivlist.add(holder.tv2);
        ivlist.add(holder.tv3);
        ivlist.add(holder.tv4);
        ivlist.add(holder.tv5);
        ivlist.add(holder.tv6);
        for(int i=0;i<6;i++){
            HomeBean.BrandBean brandBean = beanList.get(i);
            String imgpath = brandBean.getImgpath();
            Picasso.with(ctx).load(HttpModel.IMAGEHOST+imgpath).fit().error(R.mipmap.setting_network).into(ivlist.get(i));
            Log.e("tag",""+(HttpModel.IMAGEHOST+imgpath).toString());

        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv)
        TextView tv;
        @Bind(R.id.iv1)
        ImageView iv1;
        @Bind(R.id.tv2)
        ImageView tv2;
        @Bind(R.id.tv3)
        ImageView tv3;
        @Bind(R.id.tv4)
        ImageView tv4;
        @Bind(R.id.tv5)
        ImageView tv5;
        @Bind(R.id.tv6)
        ImageView tv6;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
