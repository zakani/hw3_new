package com.example.m.hw2;

/**
 * Created by M on 12/5/2014.
 */

import android.content.Context;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by M on 12/5/2014.
 */
public class IncomeCostButton extends View {
    private int radius;
    private PointF center = new PointF();
    private float strokeWidth;
    private int strokeColor;
    private Paint strokePaint = new Paint();
    private RectF circleRect = new RectF();


    public IncomeCostButton(Context context)
    {
        this(context, null);
    }

    public IncomeCostButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.IncomeCostButton,
                0, 0);

        try
        {
            strokeColor = a.getColor(R.styleable.IncomeCostButton_strokeColor_button2, Color.BLACK);
            strokeWidth = a.getFloat(R.styleable.IncomeCostButton_strokeWidth_button2, 1f);
        }
        finally
        {
            a.recycle();
        }

        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);
    }

    public void setStrokeWidth(float strokeWidth)
    {
        this.strokeWidth = strokeWidth;
        strokePaint.setStrokeWidth(strokeWidth);
        invalidate();
    }

    public float getStrokeWidth()
    {
        return strokeWidth;
    }
    public void setStrokeColor(int strokeColor)
    {
        this.strokeColor = strokeColor;
        strokePaint.setColor(strokeColor);
        invalidate();
    }

    public int getStrokeColor()
    {
        return strokeColor;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        center.x = getWidth() / 2;
        center.y = getHeight() / 2;
        radius = Math.min(getWidth(), getHeight()) / 2 - (int) strokeWidth;

    }



    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(center.x, center.y, radius, strokePaint);
    }

}
