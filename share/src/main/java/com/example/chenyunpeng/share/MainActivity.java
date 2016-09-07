package com.example.chenyunpeng.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mob.commons.SHARESDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.utils.WechatHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener {

    private android.widget.Button weixin;
    private android.widget.Button qqshare;
    private android.widget.Button qqzone;
    private android.widget.Button sinanweibo;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.sinanweibo = (Button) findViewById(R.id.sinanweibo);
        this.qqzone = (Button) findViewById(R.id.qqzone);
        this.qqshare = (Button) findViewById(R.id.qqshare);
        this.weixin = (Button) findViewById(R.id.weixin);
        this.share = (Button) findViewById(R.id.share);
        sinanweibo.setOnClickListener(this);
        qqzone.setOnClickListener(this);
        qqshare.setOnClickListener(this);
        weixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      /*
        switch (v.getId()){
            case R.id.qqshare:
               // ShareForQQ();
               ShareDiaglog diaglog=new ShareDiaglog(this);
                diaglog.show();
                break;
            case R.id.qqzone:
                ShareForQQzone();
                break;
            case R.id.sinanweibo:
                ShareForSina();
                break;
            case R.id.weixin:
                ShareForWeiXin();
                break;
            case R.id.share:
                startShare();
                break;
        }*/
    }

    private void startShare() {
        ShareSDK.initSDK(this);
        showShare();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.setText("fengxiang");
        oks.disableSSOWhenAuthorize();

        oks.show(this);
    }

    private void ShareForQQzone() {
        ShareSDK.initSDK(this);
        Platform.ShareParams sp = new Platform.ShareParams();
        //sp.setTitle("这是一个分享测试版连接,");
      //  sp.setTitleUrl("http://www.bjsxt.com");
        sp.setText("测试demo");
        sp.setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        //sp.setSite("尚学堂");
       // sp.setSiteUrl("http://www.bjsxt.com");

        Platform qzone = ShareSDK.getPlatform (QZone.NAME);
        qzone. setPlatformActionListener (this);
        qzone.share(sp);
    }

    private void ShareForWeiXin() {
        ShareSDK.initSDK(this);
        Platform.ShareParams shareParams=new Platform.ShareParams();
        shareParams.setText("这是一个分享demo");
        //shareParams.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
       shareParams.setTitleUrl("http://www.bjsxt.com");
        Platform weichart = ShareSDK.getPlatform(Wechat.NAME);
        weichart.setPlatformActionListener(this);
        weichart.share(shareParams);
        Log.e("tag","fengxiangweixinle");
    }

    private void ShareForSina() {
        ShareSDK.initSDK(this);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText("这是微博分享");
        sp.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(this);
        weibo.share(sp);
    }

    private void ShareForQQ() {
        ShareSDK.initSDK(this);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText("这是qq分享");
        sp.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        Platform weibo = ShareSDK.getPlatform(QQ.NAME);
        weibo.setPlatformActionListener(this);
        weibo.share(sp);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this,"分享出现错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
    }
}
