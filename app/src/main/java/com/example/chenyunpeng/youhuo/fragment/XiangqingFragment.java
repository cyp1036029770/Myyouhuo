package com.example.chenyunpeng.youhuo.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.bena.HttpModel;
import com.example.chenyunpeng.youhuo.bena.XiangQingBean;
import com.example.chenyunpeng.youhuo.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyunpeng on 2016/8/31.
 */
public class XiangqingFragment extends BaseFragment implements View.OnClickListener {
    protected RadioButton zuixinXiangqing;
    protected RadioButton jiageXiangqing;
    protected RadioButton zhekouXiangqing;
    protected RadioGroup radioGroup;
    protected View line;
    protected ViewPager pager;
    private ArrayList<Fragment> list;
    private RelativeLayout.MarginLayoutParams params;
    private int width;


    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new ZuiXinFragment());
        list.add(new JiaGefragment());
        list.add(new ZheKouFragment());
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(params!=null){
                    int i = (int) ((position + positionOffset) * (line.getWidth()));
                    params.leftMargin=i;
                    line.setLayoutParams(params);
                }
            }

            @Override
            public void onPageSelected(int position) {
            switch (position){
                case 0:
                    zuixinXiangqing.setChecked(true);
                    break;
                case 1:
                    jiageXiangqing.setChecked(true);
                    break;
                case 2:
                    zhekouXiangqing.setChecked(true);
                    break;
            }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        zuixinXiangqing.setOnClickListener(this);
        jiageXiangqing.setOnClickListener(this);
        zhekouXiangqing.setOnClickListener(this);
            new HttpUtils().post(HttpModel.FLLOW,"params={\"categoryId:\"" + 1 + "}").DataCallBack(new HttpUtils.DataCallBack() {
                @Override
                public void successful(String data) {
                    XiangQingBean xiangQingBean = new Gson().fromJson(data, XiangQingBean.class);
                    List<XiangQingBean.FollowBean> follow = xiangQingBean.getFollow();

                }

                @Override
                public void failrue(String e) {

                }
            });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View inflate = inflater.inflate(R.layout.fragment_xiangqing, container, false);
        init(inflate);
        width = a.getWindowManager().getDefaultDisplay().getWidth();
        Log.e("tag",""+width);
        zuixinXiangqing.setChecked(true);
        return inflate;

    }

    private void init(View rootView) {
        zuixinXiangqing = (RadioButton) rootView.findViewById(R.id.zuixin_xiangqing);
        jiageXiangqing = (RadioButton) rootView.findViewById(R.id.jiage_xiangqing);
        zhekouXiangqing = (RadioButton) rootView.findViewById(R.id.zhekou_xiangqing);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
        line = (View) rootView.findViewById(R.id.line);
        pager = (ViewPager) rootView.findViewById(R.id.pager);
        line.post(new Runnable() {
            @Override
            public void run() {
                params = (RelativeLayout.MarginLayoutParams) line.getLayoutParams();
                params.width= width/3;
                line.setLayoutParams(params);
            }
        });
    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
    @Override
    public void onClick(View v) {
   switch (v.getId()){
       case R.id.zuixin_xiangqing:
           pager.setCurrentItem(0);
           break;
       case R.id.jiage_xiangqing:
           pager.setCurrentItem(1);
           break;
       case R.id.zhekou_xiangqing:
           pager.setCurrentItem(2);
           break;
   }
    }

}
