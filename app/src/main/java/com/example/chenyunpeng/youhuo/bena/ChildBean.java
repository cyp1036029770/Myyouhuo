package com.example.chenyunpeng.youhuo.bena;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/25.
 */
public class ChildBean {
    private String index;
    private List<DataBean> list;
    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public ChildBean(String index, List<DataBean> list) {
        this.index = index;
        this.list = list;
    }

}
