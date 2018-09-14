package com.example.xiaoyangchun.animatortest.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View{

    private Paint mPaint;
    private Path mPath;
    private boolean started;
    private int x;
    private int dx;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(3.0f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
        dx = 5;
    }

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void drawPath(float y) {
        y = getHeight() - y;
        if (!started) {
            x = 0;
            mPath.moveTo(0,getHeight()/2);
            started = true;
        }
        x += dx;
        mPath.lineTo(x,y - getHeight()/2);
        invalidate();
    }

    public void reset() {
        mPath.reset();
        started = false;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(3.0f);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0,200,getWidth(),getHeight()/2,mPaint);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);
    }

}
