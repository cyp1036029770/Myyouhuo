package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.BoyBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class PinLeiBaseAdapter extends BaseAdapter {
    private List<BoyBean> list;
    private Context ctx;

    public PinLeiBaseAdapter(List<BoyBean> list, Context ctx) {
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
            convertView = View.inflate(ctx, R.layout.pinlei_fragment_item, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
           holder= (ViewHolder) convertView.getTag();
        }
        BoyBean boyBean = list.get(position);
        holder.pinleiImagView.setImageResource(boyBean.getImagURL());
        holder.pinleiTv.setText(boyBean.getContent());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.pinlei_imagView)
        ImageView pinleiImagView;
        @Bind(R.id.pinlei_tv)
        TextView pinleiTv;
        @Bind(R.id.jiantou)
        ImageView jiantou;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
