package com.lecai.quwen.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.lecai.quwen.DragGridView.tools.Util;

public class mProgressBar2 extends View {
    private int totalW,totalH;
    private float line_startPositionX;
    private float line_endPositionX;
    private float line_startPositionY;
    private float line_endPositionY;
    private float line_width;

    private float text_width;
    private float text_height;
    private float text_size;
    private float text_position;

    public float max = 200;
    private String str_percentage = "100%";
    private float percentile = max/100;
    public float value = 0;
    private float percentage = value/max*100;
    private Paint linePaint;
    private Paint textPaint;

    private float Arc_left;
    private float Arc_right;
    private float Arc_top;
    private float Arc_bottom;
    private float Arc_size;

    private float Circle_radius;

    private PorterDuffXfermode xfermode;

    private boolean isinit = true;


    public mProgressBar2(Context context) {
        super(context);
    }

    public mProgressBar2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mProgressBar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(){
        textPaint = new Paint();
        text_size = Util.dip2px(getContext(),13);
        textPaint.setTextSize(text_size);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        Rect rect = new Rect();
        textPaint.getTextBounds(str_percentage,0,str_percentage.length(),rect);
        text_width = rect.width();
        text_height = rect.height();
        line_startPositionX = rect.width()/2;
        line_endPositionX = getWidth() - rect.width()/2;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        line_width = Util.dip2px(getContext(),13);
        linePaint.setStrokeWidth(line_width);
        line_startPositionY = getHeight() - text_height + Util.dip2px(getContext(),4f);

        Circle_radius = Util.dip2px(getContext(),6.5f);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isinit){
            init();
            isinit = false;
        }

        int sc=canvas.saveLayer(0,0,totalW,totalH,linePaint,Canvas.ALL_SAVE_FLAG);
        linePaint.setColor(Color.WHITE);
        canvas.drawCircle(line_startPositionX+Circle_radius,line_startPositionY,Circle_radius,linePaint);

        canvas.drawCircle(line_endPositionX-Circle_radius,line_startPositionY,Circle_radius,linePaint);
        canvas.drawLine(line_startPositionX+Circle_radius-0.5f,line_startPositionY,line_endPositionX-Circle_radius+0.5f,line_startPositionY,linePaint);

        linePaint.setColor(Color.parseColor("#ffd633"));
        linePaint.setXfermode(xfermode);
        float percentile_line_width = (line_endPositionX - line_startPositionX)/100;
        float line_length = percentile_line_width * percentage;
        canvas.drawLine(line_startPositionX,line_startPositionY,line_startPositionX + line_length,line_startPositionY,linePaint);
        linePaint.setXfermode(null);

        canvas.restoreToCount(sc);
        str_percentage = (int)percentage+"%";
        canvas.drawText(str_percentage,line_startPositionX +line_length  - text_width/2 -Util.dip2px(getContext(),1),text_size,textPaint);

        setThread();
    }

    private void setThread(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                while(percentage<100){
                    percentage += 0.01;
                    postInvalidate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        thread = null;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalW=w;
        totalH=h;
    }
}
