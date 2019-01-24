package com.lecai.quwen.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lecai.quwen.DragGridView.tools.Util;
import com.lecai.quwen.R;

@SuppressLint("AppCompatCustomView")
public class mTextView extends TextView {
    public Decorate decorate = Decorate.None;

    public Decorate getDecorate() {
        return decorate;
    }

    public void setDecorate(Decorate decorate) {
        this.decorate = decorate;
        invalidate();
    }

    public mTextView(Context context) {
        super(context);
    }

    public mTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (decorate){
            case Close:
                DrawableClose(canvas);
                break;
            case redpoint:
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                float CR = Util.dip2px(getContext(),2.5f);
                canvas.drawCircle(getWidth()-CR,CR,CR,paint);
                break;
        }

    }

    private void DrawableClose(Canvas canvas){
        Drawable drawable = getContext().getResources().getDrawable(R.mipmap.img_item_delete);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        Bitmap newBitmap = resetBitmapSize(bitmap,15,15);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawBitmap(newBitmap,getWidth()-newBitmap.getWidth(),0,paint);
    }

    private Bitmap resetBitmapSize(Bitmap bitmap,int wdp,int hdp){
        int bitmapwidth = bitmap.getWidth();
        int bitmapheight = bitmap.getHeight();
        float newWidth = Util.dip2px(getContext(),wdp);
        float newHeight = Util.dip2px(getContext(),hdp);
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth/bitmapwidth,newHeight/bitmapheight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return newBitmap;
    }

    public enum Decorate{
        None,Close, redpoint
    }
}
