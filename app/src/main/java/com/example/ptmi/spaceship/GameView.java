package com.example.ptmi.spaceship;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.example.ptmi.spaceship.entity.Ship;

import static android.content.ContentValues.TAG;

/**
 * Created by ptmi on 2017.03.17..
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {


    public static Bitmap bitmap;
    public static Bitmap bitmap2;
    public static Bitmap bitmap3;
    public static Bitmap bitmap4;
    public static Bitmap bitmap5;
    public static Bitmap bitmap6;
    public static Bitmap bitmap7;
    public static Bitmap expl1;
    public static Bitmap expl2;
    public static Bitmap expl3;
    public static Bitmap expl4;

    private final SurfaceHolder surfaceHolder;
    Game game;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid);
        bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hp);
        bitmap6 = bitmap5.copy(Bitmap.Config.ARGB_8888, true);
        expl1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.expl1);
        expl2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.expl2);
        expl3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.expl3);
        expl4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.expl4);
        bitmap7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);



        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        //touch
        setFocusable(true);
        setOnTouchListener(this);

        game = new Game(context, surfaceHolder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        game.width = width;
        game.height = height;

        game.start(); // itt mar tudjuk a kepernyo meretet


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return game.onTouch(event);

    }


}
