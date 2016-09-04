package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;


/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class PullToReflashGridView extends RelativeLayout {
/*
    private int rawY;
    private GridView gv;
    private LayoutParams gvparams;
    private LayoutParams footparams;
    private ImageView foot;
    private LayoutParams headparams;
    private ImageView head;
    private boolean isPull;
    private boolean isLoad;
    private int minY;
    private int measuredHeight;
    private LoadDataListener listenner;

    public PullToReflashGridView(Context context) {
        this(context, null);
    }

    public PullToReflashGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToReflashGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        head = new ImageView(getContext());
        head.setImageResource(R.mipmap.icon_loaing_frame_1);
        head.setId(R.id.head);
        headparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(30));
        head.setLayoutParams(headparams);

        foot = new ImageView(getContext());
        foot.setImageResource(R.mipmap.icon_loaing_frame_1);
        foot.setId(R.id.foot);
        footparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(30));
        head.setLayoutParams(footparams);
        gv = new GridView(getContext());
        gvparams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        gvparams.addRule(ABOVE, R.id.foot);
        gvparams.addRule(BELOW, R.id.head);
        gv.setNumColumns(2);
        gv.setLayoutParams(gvparams);
        head.measure(0, 0);
        measuredHeight = head.getMeasuredHeight();
        headparams.topMargin = -measuredHeight;
        footparams.bottomMargin = -measuredHeight;
        head.setLayoutParams(headparams);
        foot.setLayoutParams(footparams);
        addView(head);
        addView(foot);
        addView(gv);
    }
    public  void setAdapter(BaseAdapter adapter){
        gv.setAdapter(adapter);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = (int) ev.getRawY();
                if (isPull || isLoad) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                minY = (int) (rawY - ev.getRawY())/3;
                if (gv.getChildCount() > 0) {
                    if (minY > 0 && gv.getFirstVisiblePosition() == 0 && gv.getChildAt(0).getTop() == 0) {
                        rawY = (int) ev.getRawY();
                        return true;
                    } else if (minY < 0 && gv.getLastVisiblePosition() == (gv.getChildCount() - 1) && gv.getChildAt(gv.getLastVisiblePosition() - gv.getFirstVisiblePosition()).getBottom() == gv.getHeight()) {
                        rawY = (int) ev.getRawY();
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = (int) event.getRawY();
                if (isPull) return true;
                break;
            case MotionEvent.ACTION_MOVE:
               int minY = (int) (event.getRawY() - rawY)/3;
                pull(minY);
                load(minY);
                break;
            case MotionEvent.ACTION_UP:
                pullData();
                loadData();
                break;
        }
        return true;
    }

    private void loadData() {
        if (isLoad) {
             return;
        }
        if(footparams.bottomMargin>-measuredHeight){
            if(footparams.bottomMargin<0){
                footparams.bottomMargin=-measuredHeight;
                foot.setLayoutParams(footparams);
                gvparams.bottomMargin=0;
                gv.setLayoutParams(gvparams);
                isLoad=false;
            }else {
                footparams.bottomMargin = 0;
                foot.setLayoutParams(footparams);
                gvparams.bottomMargin = -measuredHeight;
                gv.setLayoutParams(gvparams);
                foot.setImageResource(R.drawable.load_donghua);
                isLoad = true;
                if (listenner != null) {
                    listenner.load();
                }
            }
        }
    }

    private void pullData() {
        if (isPull) {
            return;
        }
        if (headparams.topMargin >= 0) {
            head.setImageResource(R.drawable.load_donghua);
            headparams.topMargin = 0;
            head.setLayoutParams(headparams);
            isPull = true;
            if (listenner != null) {
                listenner.pull();
            }
        } else if (headparams.bottomMargin < 0) {
            headparams.bottomMargin = -measuredHeight;
            head.setLayoutParams(headparams);
        }
    }

    private void load(int minY) {
        if (isLoad)return;
        if (minY < 0 && gv.getLastVisiblePosition() == gv.getChildCount() - 1) {
            footparams.bottomMargin = -measuredHeight - minY;
            foot.setLayoutParams(footparams);
            gvparams.topMargin = minY;
            gv.setLayoutParams(gvparams);
        }
    }

    private void pull(int minY) {
        if(isPull)return;
        if (gv.getFirstVisiblePosition() == 0) {
            headparams.topMargin = -measuredHeight + minY;
            head.setLayoutParams(headparams);
        }
    }

    public void ResetPull() {
        headparams.topMargin = -measuredHeight;
        isPull = false;
        head.setLayoutParams(headparams);
        head.setImageResource(R.mipmap.icon_loaing_frame_1);
    }

    public void ResetLoad() {
        footparams.bottomMargin = -measuredHeight;
        isLoad = false;
        gvparams.topMargin = 0;
        gv.setLayoutParams(gvparams);
        foot.setLayoutParams(footparams);
        foot.setImageResource(R.mipmap.icon_loaing_frame_1);
    }

    public interface LoadDataListener {
        void pull();

        void load();
    }

    public void setLoadDataListenner(LoadDataListener listenner) {
        this.listenner = listenner;
    }*/

    private int measuredHeight;
    private GridView gv;
    private ImageView headIv;
    private ImageView footerIv;
    private int y;
    private LayoutParams footerParams;
    private LayoutParams headParams;
    private LayoutParams gvParams;
    private PullSuccessListener pullSuccessListener;
    private boolean isPull;
    private boolean isLoad;
    private boolean headVisiavle=true;
    private boolean footerVisiavle=true;

    public PullToReflashGridView(Context context) {
        this(context,null);
    }

    public PullToReflashGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullToReflashGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void setOnItemClickListenener(AdapterView.OnItemClickListener listenener){
        gv.setOnItemClickListener(listenener);
    }

    private void init() {
        headIv = new ImageView(getContext());
        headIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        headParams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(30));
        headIv.setLayoutParams(headParams);
        headIv.setId(R.id.head);
        footerIv = new ImageView(getContext());
        footerIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        footerParams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(30));
        footerParams.addRule(ALIGN_PARENT_BOTTOM);
        footerIv.setLayoutParams(footerParams);
//        footerIv.measure(0,0);
        measuredHeight = footerParams.height;
        footerIv.setId(R.id.foot);
        gv = new GridView(getContext());
        gv.setNumColumns(2);
        gvParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        gvParams.addRule(BELOW,R.id.head);
        gvParams.addRule(ABOVE,R.id.foot);
        gv.setLayoutParams(gvParams);
        headParams.topMargin=-measuredHeight;
        footerParams.bottomMargin=-measuredHeight;
        addView(headIv);
        addView(footerIv);
        addView(gv);
        gv.setSelector(new BitmapDrawable());

    }
    public void setAdapter(BaseAdapter adapter){
        gv.setAdapter(adapter);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                if(isLoad||isPull)return true;
                break;
            case MotionEvent.ACTION_MOVE:
                int min= (int) (ev.getRawY()-y)/3;
                if(gv.getChildCount()>0){
                    if(min>0&&gv.getFirstVisiblePosition()==0&&gv.getChildAt(0).getTop()==0){
                        y= (int) ev.getRawY();
                        return true;
                    }else if(min<0&&gv.getLastVisiblePosition()==gv.getAdapter().getCount()-1&&gv.getChildAt(gv.getLastVisiblePosition()-gv.getFirstVisiblePosition()).getBottom()==gv.getHeight()){
                        y= (int) ev.getRawY();
                        return true;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                y= (int) event.getRawY();
                if(isPull)return true;
                break;
            case MotionEvent.ACTION_MOVE:
                int min= (int) (event.getRawY()-y)/3;
                pull(min);
                load(min);
                break;
            case MotionEvent.ACTION_UP:
                pullSongShou();
                loadSongShou();
                break;
        }
        return true;
    }

    private void loadSongShou() {
        if(isLoad)return;
        if(footerParams.bottomMargin>-measuredHeight){
            if(footerParams.bottomMargin<=0){
                footerParams.bottomMargin=-measuredHeight;
                gvParams.topMargin=0;
                footerIv.setLayoutParams(footerParams);
                gv.setLayoutParams(gvParams);
            }else{
                footerParams.bottomMargin=0;
                gvParams.topMargin=-measuredHeight;
                footerIv.setLayoutParams(footerParams);
                footerIv.setImageResource(R.drawable.load_donghua);
                gv.setLayoutParams(gvParams);
                if(pullSuccessListener!=null){
                    isLoad = true;
                    pullSuccessListener.load();
                }

            }
        }
    }

    private void load(int min) {
        if(min<0&&gv.getLastVisiblePosition()==gv.getAdapter().getCount()-1&&!isLoad&&footerVisiavle){
            footerParams.bottomMargin=-measuredHeight-min;
            gvParams.topMargin=min;
            footerIv.setLayoutParams(footerParams);
            gv.setLayoutParams(gvParams);
        }
    }

    private void pullSongShou() {
        if(isPull)return;
        int topMargin = headParams.topMargin;
        if(topMargin<0){
            headParams.topMargin=-measuredHeight;
            headIv.setLayoutParams(headParams);
        }else{
            headIv.setImageResource(R.drawable.load_donghua);
            headParams.topMargin=0;
            headIv.setLayoutParams(headParams);
            if(pullSuccessListener!=null){
                isPull = true;
                pullSuccessListener.pull();
            }
        }
    }


    private void pull(int min) {
        if(min>0&&gv.getFirstVisiblePosition()==0&&!isPull&&headVisiavle){
            headParams.topMargin=-measuredHeight+min;
            headIv.setLayoutParams(headParams);
        }
    }
    public interface PullSuccessListener{
        void pull();
        void load();
    }
    public void setOnPullSuccessListener(PullSuccessListener listener){
        pullSuccessListener =listener;
    }
    public void pullSuccess(){
        headIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        headParams.topMargin=-measuredHeight;
        headIv.setLayoutParams(headParams);
        isPull=false;
    }
    public void loadSuccess(){
        footerParams.bottomMargin=-measuredHeight;
        gvParams.topMargin=0;
        footerIv.setLayoutParams(footerParams);
        gv.setLayoutParams(gvParams);
        footerIv.setImageResource(R.mipmap.icon_loaing_frame_1);
        isLoad=false;

    }
    public void setHeadVisiable(boolean visiable){
        this.headVisiavle=visiable;
    }
    public void setFooterVisiable(boolean visiable){
        this.footerVisiavle=visiable;
    }

}
