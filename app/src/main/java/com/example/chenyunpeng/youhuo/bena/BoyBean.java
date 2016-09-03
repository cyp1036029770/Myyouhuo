package com.example.chenyunpeng.youhuo.bena;

/**
 * Created by chenyunpeng on 2016/8/24.
 */
public class BoyBean {
    private  String content;
    private  int imageId;
    private  boolean haveJiantou;

    public BoyBean(String content, int imagURL, boolean haveJiantou) {
        this.content = content;
        imageId = imagURL;
        this.haveJiantou = haveJiantou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImagURL() {
        return imageId;
    }

    public void setImagURL(int imagURL) {
        imageId = imagURL;
    }

    public boolean isHaveJiantou() {
        return haveJiantou;
    }

    public void setHaveJiantou(boolean haveJiantou) {
        this.haveJiantou = haveJiantou;
    }
}
