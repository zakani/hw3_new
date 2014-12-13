package com.example.m.hw2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by M on 11/26/2014.
 */
public class myButton extends Button {
    private Paint paint;
    public myButton(Context context) {
        super(context);
        paint=new Paint();
        paint.setColor(220);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        RectF rectF = new RectF(20, 250, 300, 480);
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
//        canvas.drawArc (rectF, 290, 45, true, paint);
        canvas.drawCircle(4,20,40,paint);
        canvas.save();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Get the width measurement
        int widthSize =getMaxWidth();
        //Get the height measurement
        int heightSize = getMaxHeight();

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize, heightSize);
    }

    }

