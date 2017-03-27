package com.example.ptmi.spaceship.entity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.ptmi.spaceship.Game;
import com.example.ptmi.spaceship.GameActivity;
import com.example.ptmi.spaceship.GameView;
import com.example.ptmi.spaceship.MainActivity;

import static com.example.ptmi.spaceship.Game.game;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Ship extends Entity {
    public float newX, newY;
    public float healthX;
    GameActivity gameActivity;


    Paint paint =new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    GameView gameView;
    private RectF rect;



    public Ship(float x, float y){
        super(x, y);
        newX = x;
        newY = y;
        healthX = 125;
        r=50;
        paint.setColor(Color.GREEN); // csak az elejen kell beallitani a szint, mert nem valtozik
        paint2.setColor(Color.BLACK);
        paint3.setColor(Color.TRANSPARENT);

    }

    @Override
    public void collison(Entity other) {
        healthX -= 25;





    }


    @Override
    public void update() {
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

    public void hpDecrease(Canvas canvas) {
        canvas.drawRect(x - 105, y + 280, x + healthX, y, paint3);

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(GameView.bitmap, x - 105, y, null);
        canvas.drawRect(x - 105, y + 280, x + 125, y + 270, paint2);

        canvas.drawRect(x - 105, y + 280, x + 125, y + 270, paint);
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
