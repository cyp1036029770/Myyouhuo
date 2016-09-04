package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
public class StickScrollView extends LinearLayout {
    private Scroller scroller;
    private ViewConfiguration configuration;
    private int scaledTouchSlop;
    private int y;
    private int x;
    private int height;
    private VelocityTracker tracker;
    private double invoke;
    private OverScroller fuleiScroller;

    public StickScrollView(Context context) {
        this(context,null);
    }

    public StickScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StickScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        configuration = ViewConfiguration.get(getContext());
        scaledTouchSlop = configuration.getScaledTouchSlop();
        post(new Runnable() {
            @Override
            public void run() {
                ViewGroup parent = (ViewGroup) getParent();
                height = parent.getHeight();
            }
        });
        tracker = VelocityTracker.obtain();
        Class<? extends StickScrollView> aClass = getClass();
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
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        tracker.recycle();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                x = (int) ev.getRawX();
                ;scroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                int min= (int) (ev.getRawY()-y);
                int minX= (int) (ev.getRawX()-x);
                if(Math.abs(min)>=scaledTouchSlop&&Math.abs(min)>Math.abs(minX)){
                    y= (int) ev.getRawY();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y= (int) event.getRawY();
                scroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                int minY= (int) (event.getRawY()-y);
                int currnetY = getScrollY() - minY;
                int max=getHeight()-height;
                int min=0;
                currnetY=Math.min(max,Math.max(min,currnetY));
                scrollTo(0,currnetY);
                y= (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                flling();
                break;
        }
        return true;
    }

    private void flling() {
        scroller.abortAnimation();
        tracker.computeCurrentVelocity(1000);

        float yVelocity = -tracker.getYVelocity();
        Class<? extends Scroller> aClass = scroller.getClass();
        try {
            Method getSplineFlingDistance = aClass.getDeclaredMethod("getSplineFlingDistance", float.class);
            getSplineFlingDistance.setAccessible(true);

            try {
                invoke = (double) getSplineFlingDistance.invoke(scroller, yVelocity)/6000;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        int duration=scroller.getDuration();

        if(invoke==0)invoke=1;
        scroller.fling(0, getScrollY(), 0, (int) ((int) yVelocity/invoke), 0, 0, 0, getHeight() - height);//scroller只是计算
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()) {
            int currY = scroller.getCurrY();
            scrollTo(0,currY);
            postInvalidate();
        }

    }
}
