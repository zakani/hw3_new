package com.example.m.hw2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by M on 12/5/2014.
 */
public class CircleFillView extends View
{
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 100;

    private PointF center = new PointF();
    private PointF text_cost = new PointF();
    private PointF touch_event = new PointF();
    private PointF text_income = new PointF();
    private RectF circleRect = new RectF();
    private RectF oval = new RectF();
    private Path segment = new Path();
    private Paint strokePaint = new Paint();
//    private Paint strokePaint_IncomeCost = new Paint();
    private Paint strokePaint_Cost = new Paint();
    private Paint strokePaint_Income = new Paint();
    private Paint strokePaint_text = new Paint();
    private Paint fillPaint = new Paint();

    private int radius;
    private int radius2;


    private int fillColor;
    private int strokeColor;
    private float strokeWidth;
    private int strokeColor_Income;
    private int strokeColor_Cost;
    private int strokeColor_text;
    private float strokeWidth_IncomeCost;
    private int value;

    public CircleFillView(Context context)
    {
        this(context, null);
    }

    public CircleFillView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleFillView,
                0, 0);

        try
        {
            fillColor = a.getColor(R.styleable.CircleFillView_fillColor, Color.WHITE);
            strokeColor = a.getColor(R.styleable.CircleFillView_strokeColor, Color.BLACK);
            strokeColor_Income=a.getColor(R.styleable.CircleFillView_strokeColor_Income,Color.BLACK);
            strokeColor_Cost=a.getColor(R.styleable.CircleFillView_strokeColor_Cost,Color.BLACK);
            strokeWidth = a.getFloat(R.styleable.CircleFillView_strokeWidth, 1f);
            strokeWidth_IncomeCost = a.getFloat(R.styleable.CircleFillView_strokeWidth_button, 1f);
            value = a.getInteger(R.styleable.CircleFillView_value, 0);
            adjustValue(value);
        }
        finally
        {
            a.recycle();
        }

        fillPaint.setColor(fillColor);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint_Cost.setColor(strokeColor_Cost);
        strokePaint_Cost.setStrokeWidth(strokeWidth_IncomeCost);
        strokePaint_Cost.setStyle(Paint.Style.STROKE);

        strokePaint_Income.setColor(strokeColor_Income);
        strokePaint_Income.setStrokeWidth(strokeWidth_IncomeCost);
        strokePaint_Income.setStyle(Paint.Style.STROKE);
        strokePaint_text.setColor(Color.BLACK);
        strokePaint_text.setStrokeWidth(3f);
        strokePaint_text.setTextSize(25f);
    }

    public void setFillColor(int fillColor)
    {
        this.fillColor = fillColor;
        fillPaint.setColor(fillColor);
        invalidate();
    }

    public int getFillColor()
    {
        return fillColor;
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

    public void setValue(int value)
    {
        adjustValue(value);
        setPaths();

        invalidate();
    }

    public int getValue()
    {
        return value;
    }

    private void adjustValue(int value)
    {
        this.value = Math.min(MAX_VALUE, Math.max(MIN_VALUE, value));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        center.x = getWidth() / 2;
        center.y = getHeight() / 2;
        radius = Math.min(getWidth(), getHeight()) / 5 - (int) strokeWidth;
        strokeWidth=radius/10;
        strokePaint.setStrokeWidth(strokeWidth);
        circleRect.set(center.x - radius, center.y - radius, center.x + radius, center.y + radius);
        radius2 = (int) ((radius+strokeWidth)*1.5);
        strokePaint_Cost.setStrokeWidth(radius);
        strokePaint_Income.setStrokeWidth(radius);
        oval.set(center.x - radius2, center.y - radius2, center.x + radius2, center.y + radius2);
        text_cost.y=center.y + radius2;
        text_income.y=center.y-radius2;
        text_income.x=center.x-(int)(radius*0.2);
        text_cost.x=center.x-(int)(radius*0.2);
        setPaths();
    }

    private void setPaths()
    {
        float y = center.y + radius - (2 * radius * value / 100 - 1);
        float x = center.x - (float) Math.sqrt(Math.pow(radius, 2) - Math.pow(y - center.y, 2));

        float angle = (float) Math.toDegrees(Math.atan((center.y - y) / (x - center.x)));
        float startAngle = 180 - angle;
        float sweepAngle = 2 * angle - 180;

        segment.rewind();
        segment.addArc(circleRect, startAngle, sweepAngle);
        segment.close();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawPath(segment, fillPaint);
        canvas.drawCircle(center.x, center.y, radius, strokePaint);
        canvas.drawArc(oval , 1, 180, false,strokePaint_Income );
        canvas.drawArc(oval , 180, 182, false, strokePaint_Cost);
        canvas.drawText("درآمد", text_cost.x,text_cost.y,strokePaint_text);
        canvas.drawText("هزینه های جاری", text_income.x,text_income.y,strokePaint_text);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    public float getCenterX(){
        return center.x;
    }

    public float getCenterY(){
        return center.y;
    }


}