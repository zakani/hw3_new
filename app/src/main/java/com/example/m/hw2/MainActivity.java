package com.example.m.hw2;

/**
 * Created by M on 12/5/2014.
 */
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.example.m.hw2.CircleFillView;
import com.example.m.hw2.R;
//import  com.example.m.hw2.IncomeCostButton;

public class MainActivity extends Activity
{
    CircleFillView circleFill;
    SeekBar seekBar;
//    IncomeCostButton IncomeCost;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        circleFill = (CircleFillView) findViewById(R.id.circleFillView);

        circleFill.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               Log.e("","OnTouchListener");
            if(motionEvent.getX()<circleFill.getRight() && motionEvent.getX()>circleFill.getLeft()) {
                if (motionEvent.getY() > circleFill.getCenterY())
                    Log.e("", "Daaaaaaaramad");
                else
                    Log.e("", "hazineeeeeeeeeeeeeeee");
            }
            return false;
            }
        });
//        IncomeCost=(IncomeCostButton) findViewById(R.id.IncomeCostButton);
//        seekBar = (SeekBar) findViewById(R.id.seekBar);
//        seekBar.setProgress(circleFill.getValue());
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
//                                           {
//                                               @Override
//                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
//                                               {
//                                                   if (fromUser)
//                                                       circleFill.setValue(progress);
//                                               }
//
//                                               @Override
//                                               public void onStartTrackingTouch(SeekBar seekBar) {}
//
//                                               @Override
//                                               public void onStopTrackingTouch(SeekBar seekBar) {}
//                                           }
//        );
    }
}