package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;

/**
 * Created by chenyunpeng on 2016/8/30.
 */
public class MyPullToReflash extends RelativeLayout {
/*

    private ImageView head;
    private ImageView foot;
    private LayoutParams footparams;
    private LayoutParams headparams;
    private ListView lv;
    private LayoutParams lvparams;
    private int measuredHeight;
    private PullAndReflashSuccessfull successfulListener;
    private boolean isPull;
    private boolean isLoad;
    private int y;
    private int min;

    public MyPullToReflash(Context context) {
        this(context, null);
    }

    public MyPullToReflash(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPullToReflash(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initHead();
        initFoot();
        initListView();
        addView(head);
        addView(foot);
        addView(lv);
    }

    private void initListView() {
        lv = new ListView(getContext());
        lvparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lvparams.addRule(ABOVE, R.id.foot);
        lvparams.addRule(BELOW, R.id.head);
        lv.setLayoutParams(lvparams);
        head.measure(0, 0);
        measuredHeight = head.getMeasuredHeight();
        headparams.topMargin = -measuredHeight;
        head.setLayoutParams(headparams);
        footparams.bottomMargin = -measuredHeight;
        foot.setLayoutParams(footparams);
    }

    private void initHead() {
        head = new ImageView(getContext());
        head.setImageResource(R.mipmap.icon_loaing_frame_1);
        headparams = new LayoutParams(LayoutParams.WRAP_CONTENT, Dp2Px.dp2px(20));
        headparams.addRule(CENTER_HORIZONTAL);
        head.setLayoutParams(headparams);
        head.setId(R.id.head);
    }

    private void initFoot() {
        foot = new ImageView(getContext());
        foot.setImageResource(R.mipmap.icon_loaing_frame_1);
        footparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
        footparams.addRule(ALIGN_PARENT_BOTTOM);
        foot.setLayoutParams(footparams);
        foot.setId(R.id.foot);
    }

    public void setHeadView(View headview) {
        lv.addHeaderView(headview);
    }

    public void setfootView(View footView) {
        lv.addFooterView(footView);
    }

    public void setAdapter(BaseAdapter adapter) {
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isPull) break;
               int  min = (int) (event.getRawY() - y)/2;
                pull(min);
                load(min);
                break;
            case MotionEvent.ACTION_UP:
                pullMore();
                loadMore();
                break;
        }
        return true;
    }

    private void loadMore() {
        if(isLoad)return;
        if(footparams.bottomMargin>-measuredHeight) {
            if (footparams.bottomMargin < 0) {
                footparams.bottomMargin = -measuredHeight;
                foot.setLayoutParams(footparams);
                lvparams.topMargin = 0;
                lv.setLayoutParams(lvparams);
            } else {
                footparams.bottomMargin = 0;
                lvparams.topMargin = -measuredHeight;
                foot.setLayoutParams(footparams);
                lv.setLayoutParams(lvparams);
                foot.setImageResource(R.drawable.load_donghua);
                if (successfulListener != null) {
                    successfulListener.load();
                    isLoad = true;
                }
            }
        }
    }

    private void load(int min) {
        if (min < 0 && lv.getLastVisiblePosition() == (lv.getAdapter().getCount() - 1)&&!isLoad) {
            footparams.bottomMargin = -measuredHeight - min;
            foot.setLayoutParams(footparams);
            lvparams.topMargin=min;
            lv.setLayoutParams(lvparams);
        }
    }

    private void pull(int min) {
        if (lv.getFirstVisiblePosition() == 0 && min > 0&&!isPull) {
            headparams.topMargin = -measuredHeight+min;
            head.setLayoutParams(headparams);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                if(isLoad||isPull){
                    Log.e("tag", "onInterceptTouchEvent: "+isPull+""+isLoad );
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
             int   min = (int) (ev.getRawY() - y)/2;
                if (min > 0 && lv.getFirstVisiblePosition() == 0 || min < 0 && lv.getLastVisiblePosition() == (lv.getAdapter().getCount() - 1)) {
                    if (min < 0) {
                        int childCount = lv.getChildCount();
                        int i = childCount - lv.getFirstVisiblePosition() - 1;
                        View childAt = lv.getChildAt(i);
                        if (childAt.getBottom() <= lv.getHeight()) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                    if (min > 0) {
                        if(lv.getChildCount()==0){
                            return false;
                        }
                        View child = lv.getChildAt(0);
                        if (child.getTop() == 0) {
                            y = (int) ev.getRawY();
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void pullMore() {
        if (isPull) {
            return;
        }
        if (headparams.topMargin > -measuredHeight) {
            if (headparams.topMargin <=0)
                headparams.topMargin = -measuredHeight;
                head.setLayoutParams(headparams);

        } else  {
            headparams.topMargin = 0;
            head.setLayoutParams(headparams);
            head.setImageResource(R.drawable.load_donghua);
            isPull = true;
            if (successfulListener != null) {
                successfulListener.pull();

            }

        }
    }

    public interface PullAndReflashSuccessfull {
        void pull();

        void load();
    }

    public void setOnPullAndReflashSuccessfullListenner(PullAndReflashSuccessfull listenner) {
        this.successfulListener = listenner;
    }

    public void pullSuccessful() {
        headparams.topMargin = -measuredHeight;
        isPull = false;
        head.setImageResource(R.mipmap.icon_loaing_frame_1);
        head.setLayoutParams(headparams);
    }

    public void loadSuccessful() {
        footparams.bottomMargin = -measuredHeight;
        isLoad = false;
        lvparams.topMargin=0;
        lv.setLayoutParams(lvparams);
        foot.setImageResource(R.mipmap.icon_loaing_frame_1);
        foot.setLayoutParams(footparams);
    }
*/


    private LinearLayout headGroup;
    private LinearLayout footerGroup;
    private ListView gv;
    private LayoutParams footerparams;
    private ImageView headIv;
    private ImageView footerIv;
    private LayoutParams headParams;
    private int measuredHeight;
    private int y;
    private PullToRelashListener pullToRelashListener;
    private boolean isPull;
    private LayoutParams params;
    private boolean isLoad;

    public MyPullToReflash(Context context) {
        this(context, null);
    }

    public MyPullToReflash(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPullToReflash(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void addHeadView(View headView) {
        gv.addHeaderView(headView);
    }

    public void addFooterView(View footerView) {
        gv.addFooterView(footerView);
    }


    private void init() {
        headIv = new ImageView(getContext());
        headIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        headParams = new LayoutParams(LayoutParams.WRAP_CONTENT, Dp2Px.dp2px(20));
        headParams.addRule(CENTER_HORIZONTAL);
        headIv.setLayoutParams(headParams);

        headIv.setId(R.id.head);
        footerIv = new ImageView(getContext());
        footerIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        footerparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
        footerparams.addRule(ALIGN_PARENT_BOTTOM);
        footerIv.setId(R.id.foot);
        footerIv.setLayoutParams(footerparams);
        gv = new ListView(getContext());
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.addRule(BELOW, R.id.head);
        params.addRule(ABOVE, R.id.foot);
        gv.setLayoutParams(params);
        headIv.measure(0, 0);
        measuredHeight = headIv.getMeasuredHeight();
        headParams.topMargin = -measuredHeight;
        headIv.setLayoutParams(headParams);
        footerparams.bottomMargin=-measuredHeight;
        addView(headIv);
        addView(footerIv);
        addView(gv);


    }

    public void setAdapter(BaseAdapter adapter) {
        gv.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(isPull)break;
                int minY = (int) (event.getRawY() - y) / 2;
                pull(minY);
                load(minY);
                break;
            case MotionEvent.ACTION_UP:
                loadPull();
                loadMore();
                break;
        }
        return true;
    }

    private void loadMore() {
        if(isLoad)return;
        if(footerparams.bottomMargin>-measuredHeight){
            if(footerparams.bottomMargin<=0){
                footerparams.bottomMargin=-measuredHeight;
                footerIv.setLayoutParams(footerparams);
                params.topMargin=0;
                gv.setLayoutParams(params);
            }else{
                footerparams.bottomMargin=0;
                footerIv.setLayoutParams(footerparams);
                params.topMargin=-measuredHeight;
                gv.setLayoutParams(params);
                footerIv.setImageResource(R.drawable.load_donghua);
                isLoad = true;
                if(pullToRelashListener!=null){
                    pullToRelashListener.load();
                }
            }
        }
    }

    private void load(int minY) {
        if(isLoad)return;
        if(minY<0&&gv.getLastVisiblePosition()==(gv.getAdapter().getCount()-1)){
            footerparams.bottomMargin=-measuredHeight-minY;
            footerIv.setLayoutParams(footerparams);
            params.topMargin=minY;
            gv.setLayoutParams(params);
        }
    }
    public void setSelection(int position){
        gv.setSelection(position);
    }

    private void loadPull() {
        if(isPull)return;
        if (headParams.topMargin > -measuredHeight) if (headParams.topMargin <= 0) {
            headParams.topMargin = -measuredHeight;
            headIv.setLayoutParams(headParams);
        } else {
            headParams.topMargin = 0;
            headIv.setLayoutParams(headParams);
            headIv.setImageResource(R.drawable.load_donghua);
            if(pullToRelashListener!=null){
                isPull = true;
                pullToRelashListener.pull();
            }
        }
    }

    public interface PullToRelashListener {
        void pull();

        void load();
    }

    public void setOnPullToRelashListener(PullToRelashListener listener) {
        pullToRelashListener = listener;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) event.getRawY();
                if(isPull||isLoad){
                    return  true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int minY = (int) (event.getRawY() - y) / 2;
                if (minY > 0 && gv.getFirstVisiblePosition() == 0||minY<0&&gv.getLastVisiblePosition()==(gv.getAdapter().getCount()-1)) {
                    if(minY<0){
                        int i = gv.getCount() - gv.getFirstVisiblePosition()-1;
                        View childAt = gv.getChildAt(i);
                        int bottom = childAt.getBottom();
                        if(bottom<=gv.getHeight()) {
                            return true;
                        }else{
                            return false;
                        }

                    }
                    if(minY>0){
                        if(gv.getChildCount()==0)return false;
                        View childAt = gv.getChildAt(0);
                        if(childAt.getTop()==0) {
                            y = (int) event.getRawY();
                            return true;
                        }else
                            return false;
                    }

                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
    public void setLoadSuccess(){
        footerIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        footerparams.bottomMargin=-measuredHeight;
        params.topMargin=0;
        footerIv.setLayoutParams(footerparams);
        gv.setLayoutParams(params);
        isLoad=false;
    }
    public void setPullSuccess(){
        headIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        headParams.topMargin=-measuredHeight;
        isPull=false;
        headIv.setLayoutParams(headParams);
    }
    private void pull(int minY) {
        if (minY > 0 && gv.getFirstVisiblePosition() == 0) {
            headParams.topMargin = -measuredHeight + minY;
            headIv.setLayoutParams(headParams);


        }
    }

}
