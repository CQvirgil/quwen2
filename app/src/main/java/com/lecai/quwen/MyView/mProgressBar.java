package com.lecai.quwen.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class mProgressBar extends View {
    private float Percentage = 50;
    float textHeight,textWidth;
    float offset;
    int marTop;


    public mProgressBar(Context context) {
        super(context);
    }

    public mProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Paint paint = new Paint();
        paint.setTextSize(15);
        Rect textRect = new Rect();
        String str = Percentage+"%";
        paint.getTextBounds(str,0,str.length(),textRect);
        textHeight = textRect.height();
        textWidth = textRect.width();
        offset = 4;
        marTop = (int) (textHeight + offset);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        @SuppressLint("DrawAllocation") Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while(Percentage<100){
                        Percentage += 0.005f;
                        postInvalidate();
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Paint paint = new Paint();

        RectF mRectF = new RectF(textWidth/2,marTop,textWidth/2+20,getHeight());
        paint.setAntiAlias(true);
        float s = Percentage/100f;

        paint.setColor(Color.WHITE);
        canvas.drawArc(mRectF,90,180,false,paint);
        paint.setStrokeWidth(getHeight()-marTop);
        canvas.drawLine(textWidth/2+9,getHeight()-marTop+5,getWidth()-textWidth/2-9,getHeight()-marTop+5,paint);
        RectF yRectf = new RectF(getWidth()-textWidth/2-20,marTop,getWidth()-textWidth/2,getHeight());
        canvas.drawArc(yRectf,270,180,false,paint);

        paint.setColor(Color.parseColor("#ffd633"));

        canvas.drawArc(mRectF,90,180,false,paint);
        paint.setStrokeWidth(getHeight()-marTop);
        canvas.drawLine(textWidth/2+9,getHeight()-marTop+5,getWidth()*s-textWidth/2-9,getHeight()-marTop+5,paint);

        RectF yRectf2 = new RectF(getWidth()*s-textWidth/2-20,marTop,getWidth()*s-textWidth/2,getHeight());
        canvas.drawArc(yRectf2,270,180,false,paint);

        paint.setTextSize(15);
        paint.setColor(Color.WHITE);
        canvas.drawText((int)Percentage+"%",getWidth()*s-textWidth,textHeight+1,paint);
        thread.start();
    }


}
