package com.aib.base.recyclerview.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LeftAndRightTagDecoration extends RecyclerView.ItemDecoration {

    private int tagWidth;
    private Paint leftTagPaint = new Paint();
    private Paint rightTagPaint = new Paint();

    public LeftAndRightTagDecoration(int tagWidth, int leftTagColor, int rightTagColor){
        this.tagWidth = tagWidth;
        leftTagPaint.setColor(leftTagColor);
        rightTagPaint.setColor(rightTagColor);
    }

    /**
     * 在itemView上绘画
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        /*int count = parent.getChildCount();
        int left, top, right, bottom;
        for(int i = 0; i < count; i++){
            View view = parent.getChildAt(i);
            top = view.getTop();
            bottom = view.getBottom();
            if(i % 2 == 0){
                left = view.getLeft();
                right = view.getLeft() + tagWidth;
                c.drawRect(left, top, right, bottom, leftTagPaint);
            }else{
                left = view.getRight() - tagWidth;
                right = view.getRight();
                c.drawRect(left, top, right, bottom, rightTagPaint);
            }
        }*/
    }

    /**
     * 在RecyclerView上，itemView下绘画
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();    //屏幕中所能显示itemView数量。
        int left, top, right, bottom;
        for (int i = 0; i < count; i++){
            View view = parent.getChildAt(i);
            top = view.getTop();
            bottom = view.getBottom();
            if(i % 2 == 0){  //左标识
                left = view.getLeft() - tagWidth;
                right = view.getLeft();
                c.drawRect(left, top, right, bottom, leftTagPaint);
            }else{  //右标识
                left = view.getRight();
                right = view.getRight() + tagWidth;
                c.drawRect(left, top, right, bottom, rightTagPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = tagWidth;
        outRect.right = tagWidth;
    }
}
