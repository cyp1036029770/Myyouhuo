package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.GridBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/29.
 */
public class HomeGridviewAdapter extends BaseAdapter {
    private List<GridBean> list;
    private Context ctx;

    public HomeGridviewAdapter(List<GridBean> list, Context ctx) {
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
        convertView = View.inflate(ctx, R.layout.home_grid_item, null);
         ViewHolder holder=new ViewHolder(convertView);
        GridBean gridBean = list.get(position);
        holder.iv.setImageResource(gridBean.getImgId());
        holder.tv.setText(gridBean.getTitle());
        return convertView;
    }
    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
