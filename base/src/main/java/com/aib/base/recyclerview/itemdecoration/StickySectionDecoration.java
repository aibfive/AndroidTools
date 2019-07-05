package com.aib.base.recyclerview.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class StickySectionDecoration extends RecyclerView.ItemDecoration {

    private int sectionHeight;
    private float pureSectionTextSize;
    private int sectionPaddingLeft;
    private Paint sectionPaint = new Paint();
    private Paint sectionTextPaint = new Paint();
    private DecorationCallback callback;

    public StickySectionDecoration(int sectionHeight, int sectionColor, int sectionTextColor,
                                   int sectionTextSize, int sectionPaddingLeft, DecorationCallback callback){
        this.sectionHeight = sectionHeight;
        this.sectionPaddingLeft = sectionPaddingLeft;
        this.callback = callback;

        sectionPaint.setColor(sectionColor);
        sectionTextPaint.setColor(sectionTextColor);
        sectionTextPaint.setTextSize(sectionTextSize);
        pureSectionTextSize = Math.abs(sectionTextPaint.getFontMetrics().ascent) - Math.abs(sectionTextPaint.getFontMetrics().descent);
    }

    /**
     * 判断是否组中第一个
     * @param firstSection
     * @param secondSection
     * @return
     */
    private boolean isFirstSectionInGroup(String firstSection, String secondSection){
        if(firstSection.equals(secondSection)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(callback == null) {
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        if(position == 0){
            outRect.top = sectionHeight;
        }else{
            String firstSection = callback.getSectionInGroup(position - 1);
            String secondSection = callback.getSectionInGroup(position);
            if(isFirstSectionInGroup(firstSection, secondSection)){
                outRect.top = sectionHeight;
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(callback == null){
            return;
        }
        int count = parent.getChildCount();  //屏幕中所能显示itemView数量。
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for(int i = 0; i < count; i++){
            View view = parent.getChildAt(i);
            /**
             * 为什么这里不直接用i来进行前、后两个section的相等判断，而是通过itemView获取其在Adapter里的位置position，
             * 在使用position来进行相等判断。因为count值是屏幕中所显示的itemView数量，而当RecyclerView进行滑动，
             * 屏幕中的itemView其位置便不再是i值所能代表的了，所以itemView背后的section值也便不能使用i值来获取，
             * 只能先靠RecyclerView的getChildAdapterPosition获取itemView在Adapter中的位置，再通过position获取section值进行相等比较。
             */
            int position = parent.getChildAdapterPosition(view);
            int top, bottom;
            if(position == 0){
                top = view.getTop() - sectionHeight;
                bottom = view.getTop();
                c.drawRect(left, top, right, bottom, sectionPaint);
                c.drawText(callback.getSectionInGroup(position), sectionPaddingLeft, view.getTop() - ((sectionHeight - pureSectionTextSize) / 2.0f), sectionTextPaint);
            }else{
                String firstSection = callback.getSectionInGroup(position - 1);
                String secondSection = callback.getSectionInGroup(position);
                if(isFirstSectionInGroup(firstSection, secondSection)){
                    top = view.getTop() - sectionHeight;
                    bottom = view.getTop();
                    c.drawRect(left, top, right, bottom, sectionPaint);
                    c.drawText(callback.getSectionInGroup(position), sectionPaddingLeft, view.getTop() - ((sectionHeight - pureSectionTextSize) / 2.0f), sectionTextPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public interface DecorationCallback{
        String getSectionInGroup(int position);
    }

}
