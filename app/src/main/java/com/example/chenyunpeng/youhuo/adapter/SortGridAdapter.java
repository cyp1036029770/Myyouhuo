package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.FllowGridBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/9/4.
 */
public class SortGridAdapter extends BaseAdapter {
    private List<FllowGridBean.FollowBean.GoodsBean> list;
    private Context ctx;

    public SortGridAdapter(List<FllowGridBean.FollowBean.GoodsBean> list, Context ctx) {
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
        if(convertView==null){
            convertView = View.inflate(ctx, R.layout.item_fragment_sort_grid, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        FllowGridBean.FollowBean.GoodsBean goodsBean = list.get(position);
        holder.sortTv.setText("美斯特邦威 T恤衫");
        holder.sortTv2.setText(goodsBean.getPrice());
        Picasso.with(ctx).load(HttpModel.IMAGEHOST+goodsBean.getGoodsimg()).error(R.mipmap.setting_network).fit().into(holder.sortIv);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.sort_iv)
        ImageView sortIv;
        @Bind(R.id.sort_tv)
        TextView sortTv;
        @Bind(R.id.sort_tv2)
        TextView sortTv2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
