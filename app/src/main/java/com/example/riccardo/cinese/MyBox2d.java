package com.example.riccardo.cinese;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;


public class MyBox2d extends Activity {

    private final static int RATE = 10;
    private AABB worldAABB;
    private World world;
    private float timeStep = 1/60;//模拟的的频率
    private int iterations = 10;//迭代越大，模拟约精确，但性能越低
    private Body body,body2,body3;
    private MyView myView;
    private Handler mHandler;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        worldAABB = new AABB();
        worldAABB.lowerBound.set(-1000.0f,-1000.0f);    //(-100.0f,-100.0f);
        worldAABB.upperBound.set(1000.0f, 1000.0f);     //(100.0f,100.0f);

        Vec2 gravity = new Vec2(0.0f,10.0f);
        boolean doSleep = true;


        world = new World(worldAABB, gravity, doSleep);

        createBox(160, 470, 160, 10, true);
        createBox1(160, 150, 160, 10, false);
        //another

        createCircle(160, 100, 10);
        createCircle1(150, 60, 10);
        timeStep = 1.0f/60.0f;
        iterations = 10;

        myView = new MyView(this);
        setContentView(myView);
        mHandler = new Handler();
        mHandler.post(update);
    }

    private Runnable update = new Runnable() {
        public void run() {
            world.step(timeStep, iterations);
            Vec2 position = body.getPosition();
            Vec2 position1 = body2.getPosition();
            Vec2 position2 = body3.getPosition();
            myView.x=position.x*RATE;
            myView.y=position.y*RATE;

            myView.x1=position1.x*RATE;
            myView.y1=position1.y*RATE;

            myView.x2=position2.x*RATE;
            myView.y2=position2.y*RATE;
            myView.update();
            mHandler.postDelayed(update, (long)timeStep*1000);
        }
    };

    public void createBox(float x,float y,float half_width,float half_height,
                          boolean isStatic){
        PolygonDef shape = new PolygonDef();
        if(isStatic){shape.density = 0;}
        else{shape.density = 2.0f;}
        shape.friction = 0.8f;
        shape.restitution = 0.3f;
        shape.setAsBox(half_width/RATE, half_height/RATE);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x/RATE, y/RATE);
        Body body1= world.createBody(bodyDef);
        body1.createShape(shape);
        body1.setMassFromShapes();
    }

    public void createCircle(float x,float y,float radius){
        CircleDef shape = new CircleDef();
        shape.density = 7;
        shape.friction = 0.2f;
        shape.radius = radius/RATE;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x/RATE, y/RATE);
        body2 = world.createBody(bodyDef);
        body2.createShape(shape);
        body2.setMassFromShapes();
    }

    public void createCircle1(float x,float y,float radius){
        CircleDef shape = new CircleDef();
        shape.density = 7;
        shape.friction = 0.2f;
        shape.radius = radius/RATE;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x/RATE, y/RATE);
        body3 = world.createBody(bodyDef);
        body3.createShape(shape);
        body3.setMassFromShapes();
    }

    public void createBox1(float x,float y,float half_width,float half_height,
                           boolean isStatic){
        PolygonDef shape = new PolygonDef();
        if(isStatic){shape.density = 0;}
        else{shape.density = 2.0f;}
        shape.friction = 0.3f;
        shape.setAsBox(half_width/RATE, half_height/RATE);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x/RATE, y/RATE);
        body= world.createBody(bodyDef);
        body.createShape(shape);
        body.setMassFromShapes();
    }

}