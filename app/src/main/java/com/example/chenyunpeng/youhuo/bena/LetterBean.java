package com.example.chenyunpeng.youhuo.bena;

import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/27.
 */
public class LetterBean {
    private    String title;
    private List<AllBannerBean.BrandBean> list;

    public LetterBean(String title, List<AllBannerBean.BrandBean> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AllBannerBean.BrandBean> getList() {
        return list;
    }

    public void setList(List<AllBannerBean.BrandBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "LetterBean{" +
                "title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
