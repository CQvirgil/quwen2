package com.lecai.quwen.MyVIew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class CircleImage extends ImageView {
    Bitmap bitmap;

    public CircleImage(Context context) {
        super(context);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
//        Drawable drawable = getDrawable();
//        if(drawable !=null){
//            Bitmap mbitmap = ((BitmapDrawable)drawable).getBitmap();
//            Bitmap cbitmap = getOvalBitmap(mbitmap);
//            final Rect rectSrc = new Rect(0, 0, cbitmap.getWidth(), cbitmap.getHeight());
//            final Rect rectDest = new Rect(0,0,getWidth(),getHeight());
//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            canvas.drawBitmap(cbitmap,rectSrc,rectDest,paint);
//        }
        DrawCircleImage(canvas);
    }

    private void DrawCircleImage(Canvas canvas){
        Drawable drawable = getDrawable();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if(drawable !=null){
            Bitmap mbitmap = ((BitmapDrawable)drawable).getBitmap();
            bitmap = resizeBitmap(mbitmap,getWidth(),getHeight());
            Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
            Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            paint.setColor(Color.parseColor("#7f7f7f"));
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
            paint.setShader(bitmapShader);
            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-3,paint);
        }

    }

    /**
     * 得到圆形图片
     * @param bitmap
     * @return
     */

    public static Bitmap getOvalBitmap(Bitmap bitmap){

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 给Bitmap设置圆角
     * @param bitmap
     * @param roundPx  圆角值
     * @return
     */

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);
        return resizedBitmap;
    }

}
