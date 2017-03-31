package com.example.ptmi.spaceship.entity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.ViewDebug;

import com.example.ptmi.spaceship.Game;
import com.example.ptmi.spaceship.GameActivity;
import com.example.ptmi.spaceship.GameView;
import com.example.ptmi.spaceship.MainActivity;
import com.example.ptmi.spaceship.R;

import static com.example.ptmi.spaceship.Game.game;
import static java.lang.Thread.currentThread;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Ship extends Entity {
    public static Ship ship;
    public float newX, newY;
    public float healthX, hp;
    public boolean dead = false;
    GameActivity gameActivity;
    Canvas canvas;
    int tick = 0;
    Paint paint =new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    GameView gameView;
    MediaPlayer over = new MediaPlayer();
    private RectF rect;



    public Ship(float x, float y){
        super(x, y);
        over = MediaPlayer.create(Game.game.context, R.raw.over);
        newX = x;
        newY = y;
        healthX = 125;
        hp = 150;
        r=50;
        paint.setColor(Color.GREEN); // csak az elejen kell beallitani a szint, mert nem valtozik
        paint2.setColor(Color.RED);
        paint3.setColor(Color.TRANSPARENT);

    }


    @Override
    public void collison(Entity other) {

        hp -= 50;
        Log.v("asd", Float.toString(healthX));


    }


    @Override
    public void update() {
        tick++;
        float dx = newX - x;
        float dy = newY - y;
        float d = dx * dx + dy * dy;
        rect = new RectF(x - 105, y + 280, x + 125, y);


        x += dx / 30;
        y += dy / 30;


        if (Game.game.tick % 20 == 0) {
            Entity missile = new Missile(x, y - r);
            Game.game.addEntity(missile);
        }
    }

    public RectF getRect() {
        return rect;
    }


    @Override
    public void render(Canvas canvas) {


        if (hp != -150) {
            canvas.drawBitmap(GameView.bitmap, x - 110, y, null);
            canvas.drawRect(x + 150, y + 270, x - 150, y + 280, paint);
            canvas.drawRect(x - hp, y + 270, x - 150, y + 280, paint2);


        } else {

            dead = true;
            over.start();



        }
    }


    @Override
    public void hpDecrease(Canvas canvas) {

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
