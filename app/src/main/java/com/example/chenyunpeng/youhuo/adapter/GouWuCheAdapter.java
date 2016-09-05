package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.GouWuCheBean;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.bena.itemCartBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenyunpeng on 2016/9/4.
 */
public class GouWuCheAdapter extends BaseAdapter {
    private List<itemCartBean> list;
    private Context context;

    public GouWuCheAdapter(List<itemCartBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        int itemViewType = getItemViewType(position);
       ViewHolder holder=null;
        if (convertView == null) {
            if (itemViewType == 0) {
                convertView = View.inflate(context, R.layout.item_cart_normal, null);
                holder = new normalViewHolder(convertView);
                convertView.setTag(holder);
            } else if(itemViewType==1){
                convertView = View.inflate(context, R.layout.item_cart_selected, null);
                holder = new SelectViewHolder(convertView);
                convertView.setTag(holder);
            }
        }else {
         holder= (ViewHolder) convertView.getTag();
        }
        if(itemViewType==0){
            BindNormal(holder,position);
        }else if(itemViewType==1){
            BindSelect(holder,position);
        }

        return convertView;
    }

    private void BindSelect(ViewHolder holder, int position) {
         SelectViewHolder selectViewHolder= (SelectViewHolder) holder;

        GouWuCheBean.Cart cart = list.get(position);
        selectViewHolder.cb.setTag(position);
        selectViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox= (CheckBox) v;
                int  pos = (int) v.getTag();
                list.get(pos).setCheck(checkBox.isChecked());

            }
        });
        selectViewHolder.cb.setChecked(list.get(position).isCheck());
       selectViewHolder.tv.setText(cart.getTitle()+"");
        selectViewHolder.tvNum.setText(cart.getNum());
        selectViewHolder.tvPrice.setText(cart.getPrice());
        Picasso.with(context).load(HttpModel.IMAGEHOST+cart.getImgpath()).error(R.mipmap.setting_network).fit().into(selectViewHolder.iv);
    }

    private void BindNormal(ViewHolder holder, int position) {
        normalViewHolder normalViewHolder= (GouWuCheAdapter.normalViewHolder) holder;
        GouWuCheBean.Cart cart = list.get(position);
        normalViewHolder.cb.setTag(position);
        normalViewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox= (CheckBox) v;
                int  pos = (int) v.getTag();
               list.get(pos).setCheck(checkBox.isChecked());

            }
        });
        normalViewHolder.cb.setChecked(list.get(position).isCheck());
        normalViewHolder.tv.setText("颜色:" + cart.getColor() + " 尺寸:" + cart.getSize());
        normalViewHolder.tvNum.setText(cart.getNum());
        normalViewHolder.tvPrice.setText(cart.getPrice());
        normalViewHolder.tvName.setText(cart.getTitle());
        Picasso.with(context).load(HttpModel.IMAGEHOST+cart.getImgpath()).error(R.mipmap.setting_network).fit().into(normalViewHolder.iv);

    }

    int type = 0; //0表示正常状态

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public void setType(int type) {
        if (type <=0) {
            type = 0;
        } else {
           type = 1;
        }
        this.type=type;
    }

    public GouWuCheAdapter(int type) {
        this.type = type;
    }

    static class normalViewHolder extends ViewHolder {
        @Bind(R.id.cb)
        CheckBox cb;
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_num)
        TextView tvNum;
        @Bind(R.id.tv)
        TextView tv;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        normalViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class SelectViewHolder extends ViewHolder{
        @Bind(R.id.cb)
        CheckBox cb;
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.shuliang)
        LinearLayout shuliang;
        @Bind(R.id.tv)
        TextView tv;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_num)
        TextView tvNum;

        SelectViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
  static class  ViewHolder{

    }
}
