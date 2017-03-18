package com.example.ptmi.spaceship;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.ptmi.spaceship.entity.Ship;

/**
 * Created by ptmi on 2017.03.17..
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener{


public static float x2,y2;
    public static MotionEvent event;


    private Canvas canvas;


    Game game;
    private final SurfaceHolder surfaceHolder;



    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        game = new Game(surfaceHolder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        game.width = width;
        game.height= height;

        game.start(); // itt mar tudjuk a kepernyo meretet




    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public static float getX2() {
        return x2;
    }

    public static float getY2() {
        return y2;
    }

    @Override
    public boolean onTouch(View v,MotionEvent event) {
        game.onTouch(event);
         x2 = event.getX();
         y2 = event.getY();
        this.event = event;




        return false;
        }


}
