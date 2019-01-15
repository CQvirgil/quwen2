package com.lecai.quwen.DragGridView.base;

import android.widget.BaseAdapter;


public abstract class BaseDragAdapter extends BaseAdapter{

    public abstract void addItem(BaseItem item);
    public abstract void exchange(int dragPosition,int dropPosition);
    public abstract void removeItem(BaseItem item);
    public abstract void removePosition(int position);
    public abstract void dragEnd();
    public abstract void hidePosition(int position);
    public abstract void showAll();
}
