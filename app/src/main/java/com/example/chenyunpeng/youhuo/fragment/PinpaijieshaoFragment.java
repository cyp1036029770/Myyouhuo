package com.example.chenyunpeng.youhuo.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        TextView tv=new TextView(a);
        tv.setText("ssssssssssssssssssssssssssssssssssssssssss");
        tv.setTextSize(100);
        tv.setBackgroundColor(Color.WHITE);
        return tv;
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
