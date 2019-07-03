package com.lecai.quwen.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

//圆形进度条
public class CircularProgress extends View {
    private float width, height;
    private int percentage  = 20;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
        postInvalidate();
    }

    public CircularProgress(Context context) {
        super(context);
    }

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#e2e2e2"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dip2px(3));
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawCircle(width/2,height/2,width/2 - px2dip(10), paint);

        paint.setColor(Color.parseColor("#e7af00"));
        RectF oval = new RectF(px2dip(10),px2dip(10),width - px2dip(10),height - px2dip(10));
        canvas.drawArc(oval,-90,percentage * 3.6f,false,paint);
    }

    private int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int px2dip(float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
