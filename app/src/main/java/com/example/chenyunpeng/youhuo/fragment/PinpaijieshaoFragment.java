package com.example.chenyunpeng.youhuo.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.example.chenyunpeng.youhuo.R;
/**
 * Created by chenyunpeng on 2016/8/31.
 */
public class PinpaijieshaoFragment extends BaseFragment {


    public boolean haveFragment;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        WebView wv=new WebView(a);
       wv.setWebViewClient(new WebViewClient(){
           @Override
           public boolean shouldOverrideUrlLoading(WebView view, String url) {
               return true;
           }
       });
        wv.loadUrl("www.baidu.com");
        return  wv;
    }

    @Override
    public void onAttach(Activity activity) {
          super.onAttach(activity);
        haveFragment = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
       haveFragment=false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(enter){
            return AnimationUtils.loadAnimation(a, R.anim.pinpai_translate_in);
        }else {
            return AnimationUtils.loadAnimation(a,R.anim.pinpai_translate_out);
        }

    }
}
