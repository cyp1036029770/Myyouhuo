package com.example.chenyunpeng.youhuo.bena;

/**
 * Created by chenyunpeng on 2016/8/29.
 */
public class GridBean {
    private String title;
    private int imgId;

    public GridBean(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
