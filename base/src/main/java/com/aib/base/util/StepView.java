package com.aib.base.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aib.base.R;

public class StepView extends View {

    private int stepCount;  //步数总数
    private int currentStep;  //当前步数
    private Drawable normalDrawable;  //常态下的图标
    private Drawable stepDrawable;  //步数图标
    private int drawableSize;  //图标大小
    private int stepTextColor;  //步数文本颜色
    private int stepDescribeColor;  //步数描述文本颜色
    private int stepTextSize;  //步数文本大小
    private int stepDescribeSize;  //步数描述文本大小
    private CharSequence[] stepTexts;  //步数文本集合
    private CharSequence[] stepDescribes;  //步数描述集合
    private int stepTextMarginTop;  //步数文本上边距
    private int stepDescribeMarginTop;  //步数描述文本上边距

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, 0, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs);
        initPaint();
    }

    private void initAttributes(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepView);
        stepCount = a.getInteger(R.styleable.StepView_stepCount, 4);
        currentStep = a.getInteger(R.styleable.StepView_stepCount, 1);
        normalDrawable = a.getDrawable(R.styleable.StepView_normalDrawable);
        stepDrawable = a.getDrawable(R.styleable.StepView_stepDrawable);
        drawableSize = a.getDimensionPixelSize(R.styleable.StepView_drawableSize, 16);
        stepTextColor = a.getColor(R.styleable.StepView_stepTextColor, Color.BLACK);
        stepDescribeColor = a.getColor(R.styleable.StepView_stepDescribeColor, Color.BLACK);
        stepTextSize = a.getDimensionPixelSize(R.styleable.StepView_stepTextSize, 12);
        stepDescribeSize = a.getDimensionPixelSize(R.styleable.StepView_stepDescribeSize, 14);
        stepTexts = a.getTextArray(R.styleable.StepView_stepTexts);
        stepDescribes = a.getTextArray(R.styleable.StepView_stepDescribes);
        stepTextMarginTop = a.getDimensionPixelSize(R.styleable.StepView_stepTextMarginTop, 4);
        stepDescribeMarginTop = a.getDimensionPixelSize(R.styleable.StepView_stepDescribeMarginTop, 8);
        a.recycle();
    }
    private Paint stepTextPaint;
    private Paint stepDescribePaint;

    private void initPaint(){
        stepTextPaint = new Paint();
        stepTextPaint.setColor(stepTextColor);
        stepTextPaint.setTextSize(stepTextSize);

        stepDescribePaint = new Paint();
        stepDescribePaint.setColor(stepDescribeColor);
        stepDescribePaint.setTextSize(stepDescribeSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if(stepTexts == null || stepTexts.length == 0){

        }else{
            canvas.drawText(stepTexts[0].toString(), 0, 0, stepTextPaint);



            canvas.drawText(stepTexts[stepTexts.length - 1].toString(), getWidth() - stepTextPaint.measureText(stepTexts[stepTexts.length - 1].toString()), 0, stepTextPaint);
        }
        //canvas.drawText(stepTexts[0].toString(), getX(), getY(), stepTextPaint);
       // canvas.drawCircle(50, 50, 50, stepTextPaint);

        super.onDraw(canvas);
    }
}
