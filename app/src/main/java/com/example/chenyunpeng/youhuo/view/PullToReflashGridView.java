package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;


/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class PullToReflashGridView extends RelativeLayout {

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
        headparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
        head.setLayoutParams(headparams);

        foot = new ImageView(getContext());
        foot.setImageResource(R.mipmap.icon_loaing_frame_1);
        foot.setId(R.id.foot);
        footparams = new LayoutParams(LayoutParams.MATCH_PARENT, Dp2Px.dp2px(20));
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
    }
}
