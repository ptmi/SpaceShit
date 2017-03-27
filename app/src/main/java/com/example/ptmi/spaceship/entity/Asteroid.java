

package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.ptmi.spaceship.Game;
import com.example.ptmi.spaceship.GameView;

import java.util.Random;

import static com.example.ptmi.spaceship.Game.game;


public class Asteroid extends Entity {


    public int width = game.width;
    public int height = game.height;
    public RectF rect;
    GameView gameView;
    Paint paint = new Paint();

    public Asteroid(float x, float y) {
        super(x, y);
        r = 30;
        rect = new RectF(x, y, x + 10, y + 10);
    }

    public RectF getRect() {
        return rect;
    }

    @Override
    public void collison(Entity other) {

    }

    @Override
    public void update() {
        rect = new RectF(x, y, x + 10, y + 10);
        y = y + 10;

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(GameView.bitmap3, x, y, paint);

    }

    @Override
    public void hpDecrease(Canvas canvas) {

    }
}

