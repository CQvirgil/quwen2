package com.lecai.quwen.DragGridView.tools;

import android.content.Context;

public class Util {
    /**
     * dip tp px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context,float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static int px2dip(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
