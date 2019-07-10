package com.aib.base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Administrator on 2017/8/21.
 */

public class DrawableTextView extends AppCompatTextView {

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (null != drawables) {
            Drawable drawableLeft = drawables[0];
            Drawable drawableRight = drawables[2];
            float textWidth = getPaint().measureText(getText().toString());
            if (null != drawableLeft) {
                setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableLeft.getIntrinsicWidth();
                if (getWidth() - contentWidth > 0) {
                    canvas.translate((getWidth() - contentWidth - getPaddingRight() - getPaddingLeft()) / 2, 0);
                }
            }
            if (null != drawableRight) {
                setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                float contentWidth = textWidth + getCompoundDrawablePadding() + drawableRight.getIntrinsicWidth();
                if (getWidth() - contentWidth > 0) {
                    canvas.translate(-(getWidth() - contentWidth - getPaddingRight() - getPaddingLeft()) / 2, 0);
                }
            }

            Drawable drawableTop = drawables[1];
            Drawable drawableBottom = drawables[3];
            Rect rect = new Rect();
            getPaint().getTextBounds(getText().toString(), 0, getText().toString().length(), rect);
            float textHeight = rect.height();
            if (null != drawableTop) {
                setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                float contentHeight = textHeight + getCompoundDrawablePadding() + drawableTop.getIntrinsicHeight();
                if (getHeight() - contentHeight > 0) {
                    canvas.translate(0, (getHeight() - contentHeight - getPaddingTop() - getPaddingBottom()) / 2);
                }
            }
            if (null != drawableBottom) {
                setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                float contentHeight = textHeight + getCompoundDrawablePadding() + drawableBottom.getIntrinsicHeight();
                if (getHeight() - contentHeight > 0) {
                    canvas.translate(0, -(getHeight() - contentHeight - getPaddingTop() - getPaddingBottom()) / 2);
                }
            }

            if (null == drawableRight && null == drawableLeft && null == drawableTop && null == drawableBottom) {
                setGravity(Gravity.CENTER);
            }

        }
        super.onDraw(canvas);
    }
}
