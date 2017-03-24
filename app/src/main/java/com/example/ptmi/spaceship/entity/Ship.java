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
    public float x, y, r;
    public float newX, newY;

    Paint paint =new Paint();


    public Ship(float x, float y){
        this.x=x;
        this.y=y;

        r=50;
        paint.setColor(Color.GREEN); // csak az elejen kell beallitani a szint, mert nem valtozik
    }


    @Override
    public void update() {
        float dx = newX - x;
        float dy = newY - y;
        float d = dx * dx + dy * dy;

        x += dx / 30;
        y += dy / 30;




    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawCircle(x,y,r,paint);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
