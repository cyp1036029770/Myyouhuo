package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.GuanzhuBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/28.
 */
public class GuanzhuAdapter extends BaseAdapter {
    private List<GuanzhuBean.FollowBean> list;
    private Context ctx;
    public GuanzhuAdapter(List<GuanzhuBean.FollowBean> list, Context ctx) {
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
        ViewHolder holder;
        if(convertView==null) {
            convertView = View.inflate(ctx, R.layout.item_guanzhu, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else
            holder= (ViewHolder) convertView.getTag();
        GuanzhuBean.FollowBean followBean = list.get(position);
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+followBean.getBrandimg()).into(holder.iv);
        List<GuanzhuBean.FollowBean.GoodsBean> goods = followBean.getGoods();
        String goodsimg1 = goods.get(0).getGoodsimg();
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+goodsimg1).into(holder.iv1);
        GuanzhuBean.FollowBean.GoodsBean goodsBean = goods.get(0);
        holder.tv1Normal.setText(goodsBean.getPrice());
        holder.tv1Distance.setText(goodsBean.getDistance());

        String goodsimg2 = goods.get(1).getGoodsimg();
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+goodsimg2).into(holder.iv2);
        GuanzhuBean.FollowBean.GoodsBean goodsBean2 = goods.get(0);
        holder.tv2Normal.setText(goodsBean2.getPrice());
        holder.tv2Discount.setText(goodsBean2.getDistance());


        String goodsimg3 = goods.get(2).getGoodsimg();
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+goodsimg3).into(holder.iv3);
        GuanzhuBean.FollowBean.GoodsBean goodsBean3 = goods.get(0);
        holder.tv3Normal.setText(goodsBean3.getPrice());
        holder.tv3Dicount.setText(goodsBean3.getDistance());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.titleTv)
        TextView titleTv;
        @Bind(R.id.iv1)
        ImageView iv1;
        @Bind(R.id.iv2)
        ImageView iv2;
        @Bind(R.id.iv3)
        ImageView iv3;
        @Bind(R.id.tv1_normal)
        TextView tv1Normal;
        @Bind(R.id.tv2_normal)
        TextView tv2Normal;
        @Bind(R.id.tv3_normal)
        TextView tv3Normal;
        @Bind(R.id.tv1_distance)
        TextView tv1Distance;
        @Bind(R.id.tv2_discount)
        TextView tv2Discount;
        @Bind(R.id.tv3_dicount)
        TextView tv3Dicount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
