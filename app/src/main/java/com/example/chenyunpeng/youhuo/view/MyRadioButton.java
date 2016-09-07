package com.example.chenyunpeng.youhuo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.chenyunpeng.youhuo.R;
import com.example.chenyunpeng.youhuo.utils.Dp2Px;

/**
 * Created by chenyunpeng on 2016/8/23.
 */
public class MyRadioButton extends RadioButton {

    private int  circleText;
    private boolean isDraw;
    private Paint circlePaint;
    private Paint textPaint;

    public MyRadioButton(Context context) {
        this(context,null);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        circleText = typedArray.getInt(R.styleable.MyRadioButton_circleText, 0);
        isDraw = typedArray.getBoolean(R.styleable.MyRadioButton_isDrawRedCircle, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(3.0f);
        circlePaint.setTextSize(Dp2Px.dp2px(10));
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(Dp2Px.dp2px(2));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(Dp2Px.dp2px(10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isDraw&&circleText>0){
            DrawCircle(canvas);
        }
    }

    private void DrawCircle(Canvas canvas) {
        String text=circleText+"";
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width/4*3,height/4, Dp2Px.dp2px(8),circlePaint);
        float v = textPaint.measureText(text, 0, text.length());
         Rect rect=new Rect();
        textPaint.getTextBounds(text,0,text.length(),rect);
        int textHeight = rect.height();
       canvas.drawText(text,width/4*3-v/2,height/4+textPaint.getTextSize()/3,textPaint);
    }
    public  void setRedDotText(int  num){
        this.circleText=num;
    }


}
