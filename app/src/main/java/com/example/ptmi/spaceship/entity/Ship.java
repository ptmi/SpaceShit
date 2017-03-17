package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.ptmi.spaceship.GameView;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Ship extends Entity {
   public static float x,y,r;

    Paint paint =new Paint();


    public Ship(float x, float y){
        this.x=x;
        this.y=y;

        r=50;
    }


    @Override
    public void update() {
        x = GameView.x;
        y = GameView.y;



    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawCircle(x,y,r,paint);
        paint.setColor(Color.GREEN);
    }
}
