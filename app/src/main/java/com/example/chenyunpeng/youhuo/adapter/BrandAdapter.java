package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BranBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/9/4.
 */
public class BrandAdapter extends BaseAdapter {
    private List<BranBean.ImgvaleBean> list;
    private Context ctx;

    public BrandAdapter(List<BranBean.ImgvaleBean> list, Context ctx) {
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
       /* ViewHolder holder=null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_brand_activity, null);
              holder=new ViewHolder(convertView);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }*/
        ImageView iv=new ImageView(ctx);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(200));
        iv.setLayoutParams(params);
        BranBean.ImgvaleBean imgvaleBean = list.get(position);
        String imgpath = imgvaleBean.getImgpath();
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+imgpath).fit().error(R.mipmap.setting_network).into(iv);
        return iv;
    }

    /*static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }*/
}
