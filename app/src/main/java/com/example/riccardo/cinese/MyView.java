package com.example.riccardo.cinese;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by riccardo on 16/12/16.
 */

class MyView extends View {
    Canvas canvas;
    public float x=160,y=150;
    public float x1=160,y1=100;
    public float x2=150,y2=60;
    public MyView(Context context) {
        super(context);
    }

    public void drawBox(float x,float y){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        canvas.drawRect(x-160, y-10, x+160, y+10, mPaint); //(x-160, y-10, x+160, y+10, mPaint);

    }

    public void drawGround(){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 460, 320, 480, mPaint); //(0, 460, 320, 480, mPaint);
    }

    public void drawCircle(float x1,float y1){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(x1, y1, 10, mPaint);
    }

    public void update(){
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawGround();
        drawBox(x, y);
        drawCircle(x1, y1);
        drawCircle(x2, y2);
    }


}