package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chenyunpeng on 2016/9/1.
 */
public class StickScrollView extends ScrollView {
    private int y;
    private ViewGroup parent;
    private View child1;
    private View child2;
    private int height;
    private boolean pull;
    private boolean load;
    private OverScroller fuleiScroller;
    private boolean isFlling;
    private int velocityY;

    public StickScrollView(Context context) {
        this(context, null);
    }

    public StickScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {

        post(new Runnable() {
            @Override
            public void run() {
                ViewGroup viewParent = (ViewGroup) getParent();
                height = getHeight();
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int minY = (int) (ev.getRawY() - y);
                int scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
                if (Math.abs(minY) > scaledTouchSlop) {
                    y = (int) ev.getRawY();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float miny = (y - ev.getRawY()) / 1.5f;
                if (miny >= 0 && getScrollY() > (child1.getHeight() - height) && getScrollY() < child1.getHeight()) {
                    scrollTo(0, (int) (getScrollY() + miny));
                    y = (int) ev.getRawY();
                    pull = true;
                    return true;
                }
                if (miny < 0 && getScrollY() > child1.getHeight() - height && getScrollY() < child1.getHeight()) {
                    scrollTo(0, (int) (getScrollY() + miny));
                    y = (int) ev.getRawY();
                    load = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollY() >= child1.getHeight() - height + height / 3.0f && pull) {
                    pull = false;
                    scrollTo(0, child1.getHeight());
                    return true;
                } else if (pull && getScrollY() >= child1.getHeight() - height) {
                    pull = false;
                    scrollTo(0, child1.getHeight() - height);
                    return true;
                }
                if (getScrollY() <= child1.getHeight() - height / 3 && load) {
                    load = false;
                    scrollTo(0, child1.getHeight() - height);
                    return true;
                } else if (load && getScrollY() > child1.getHeight() - height / 3) {
                    load = false;
                    scrollTo(0, child1.getHeight());
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int childCount = getChildCount();
        parent = (ViewGroup) getChildAt(0);
        if (parent.getChildCount() != 2) {
            throw new RuntimeException("zi view de  geshu bixu shi 2");

        }
        Class<ScrollView> aClass = ScrollView.class;
        try {

            Field mScroller = aClass.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            try {
                fuleiScroller = (OverScroller) mScroller.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        child1 = parent.getChildAt(0);
        child2 = parent.getChildAt(1);

    }
    public void details(){
        smoothScrollTo(0,child1.getHeight());
    }

    @Override
    public void fling(int velocityY) {
        if(getScrollY()>=0&&getScrollY()<child1.getHeight()-height){

            if(velocityY>1000){
                smoothScrollTo(0,child1.getHeight()-height);
            }else if(velocityY<=0){
                super.fling(velocityY);
            }
        }else if(getScrollY()>child1.getHeight()){
            if(velocityY>0){
                super.fling(velocityY);
            }else if(velocityY<=-1000){
                smoothScrollTo(0,child1.getHeight());
            }
        }
    }



    @Override
    public void computeScroll() {
        super.computeScroll();

    }
}
