package com.example.chenyunpeng.youhuo.bena;

/**
 * Created by chenyunpeng on 2016/8/26.
 */
public class DataBean {
        private String name;
        private String tag;

    public DataBean(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
