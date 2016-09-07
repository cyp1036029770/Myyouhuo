package com.example.chenyunpeng.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by chenyunpeng on 2016/9/6.
 */
public class ShareDiaglog extends Dialog implements PlatformActionListener {

    private final Activity a;
    protected View rootView;
    protected GridView gv;
    protected Button quxiao;
    private List<GridBean> gidbean;
    private int measuredHeight;

    public ShareDiaglog(Context context) {
        super(context);
        a = (Activity) context;
        int width = a.getWindowManager().getDefaultDisplay().getWidth();
        View inflate = View.inflate(a, R.layout.diaglog_share, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(inflate);
        initView(inflate);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
         window.getDecorView().setPadding(0,0,0,0);
        window.setLayout(width, dp2px(230));
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setWindowAnimations(R.style.shareDiaglog);

    }

    public int  dp2px(int values) {
        float v = a.getResources().getDisplayMetrics().density * values + 0.5f;
        return (int) v;
    }

    private void initView(View rootView) {
        gv = (GridView) rootView.findViewById(R.id.gv);
        quxiao = (Button) rootView.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        initData();
    }

    private void initData() {
        gidbean = new ArrayList<>();
        gidbean.add(new GridBean("微信",R.drawable.goods_share_wx_icon));
        gidbean.add(new GridBean("QQ",R.drawable.goods_share_qq_icon));
        gidbean.add(new GridBean("QQ空间",R.drawable.goods_share_qqkj_icon));
        gidbean.add(new GridBean("朋友圈",R.drawable.goods_share_wpq_icon));
        gidbean.add(new GridBean("微博",R.drawable.goods_share_xl_icon));
        gidbean.add(new GridBean("二维码",R.drawable.goods_share_qr_icon));
        MyGridAdapter adapter=new MyGridAdapter();
        gv.setAdapter(adapter);
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        LayoutAnimationController controller=new LayoutAnimationController(scaleAnimation);
        controller.setDelay(0);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        gv.setLayoutAnimation(controller);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(gidbean.get(position).getTitle().equals("微信")){
                         shareForWeix();
                }else if(gidbean.get(position).getTitle().equals("QQ")){
                          shareForQQ();
                }else if(gidbean.get(position).getTitle().equals("QQ空间")){
                          shareForQQzone();
                }else if(gidbean.get(position).getTitle().equals("朋友圈")){
                           sharForWFriend();
                }else  if(gidbean.get(position).getTitle().equals("微博")){
                            shaForSina();

                }else  if(gidbean.get(position).getTitle().equals("二维码")){

                }
            }
        });

    }

    private void shaForSina() {
        ShareSDK.initSDK(a);
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setText("这是微博分享");
        sp.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(this);
        weibo.share(sp);
    }

    private void sharForWFriend() {
        ShareSDK.initSDK(a);
        Platform.ShareParams shareParams=new Platform.ShareParams();
        Platform shareqq = ShareSDK.getPlatform(Wechat.NAME);
        shareParams.setShareType(Platform.SHARE_TEXT);
        shareParams.setText("这是一个分享demo");
        shareParams.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        shareParams.setTitleUrl("http://www.bjsxt.com");  shareqq.setPlatformActionListener(this);
        shareqq.share(shareParams);
    }

    private void shareForQQzone() {
        ShareSDK.initSDK(a);
        Platform.ShareParams shareParams=new Platform.ShareParams();
        Platform shareqq = ShareSDK.getPlatform(QZone.NAME);
        shareParams.setShareType(Platform.SHARE_TEXT);
        shareParams.setSiteUrl("http://www.bjsxt.com");
        shareParams.setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        shareqq.setPlatformActionListener(this);
        shareqq.share(shareParams);
    }

    private void shareForQQ() {

        ShareSDK.initSDK(a);
        Platform.ShareParams shareParams=new Platform.ShareParams();
        Platform shareqq = ShareSDK.getPlatform(QQ.NAME);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        shareParams.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        shareqq.setPlatformActionListener(this);
        shareqq.share(shareParams);
    }

    private void shareForWeix() {
        ShareSDK.initSDK(a);
        Platform.ShareParams shareParams=new Platform.ShareParams();
        Platform weichart = ShareSDK.getPlatform(WechatMoments.NAME);
        shareParams.setShareType(Platform.SHARE_IMAGE);
        shareParams.setText("这是一个分享demo");
        shareParams.setImagePath("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1473151381&di=ea400172a296577181f575ba7735c337&src=http://g.hiphotos.baidu.com/image/pic/item/91ef76c6a7efce1b5b6d3e18ab51f3deb58f659a.jpg");
        shareParams.setTitleUrl("http://www.bjsxt.com");
        weichart.setPlatformActionListener(this);
        weichart.share(shareParams);

        
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Toast.makeText(a,"分享成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(a,"shibai",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(a,"quxiao",Toast.LENGTH_SHORT).show();
    }

    class  MyGridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return gidbean.size();
        }

        @Override
        public Object getItem(int position) {
            return gidbean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(a,R.layout.item_share,null);
           ImageView iv= (ImageView) convertView.findViewById(R.id.iv);
           TextView tv= (TextView) convertView.findViewById(R.id.tv);
            GridBean gridBean = gidbean.get(position);
            iv.setImageResource(gridBean.getImgId());
            tv.setText(gridBean.getTitle()+"");
            return convertView;
        }
    }
}
