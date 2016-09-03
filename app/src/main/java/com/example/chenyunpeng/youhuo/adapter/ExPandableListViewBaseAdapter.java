package com.example.chenyunpeng.youhuo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/28.
 */
public abstract class ExPandableListViewBaseAdapter<T> extends BaseExpandableListAdapter {
    List<T> list;
    Context ctx;

    public ExPandableListViewBaseAdapter(List<T> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }



    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
