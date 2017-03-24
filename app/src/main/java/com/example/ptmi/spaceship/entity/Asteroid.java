

package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ptmi.spaceship.Game;
import com.example.ptmi.spaceship.GameView;

import java.util.Random;

import static com.example.ptmi.spaceship.Game.game;


public class Asteroid extends Entity {


    public int width = game.width;
    public int height = game.height;



    Paint paint = new Paint();

    public Asteroid(float x, float y) {
        super(x, y);
        r = 30;
    }

    @Override
    public void collison(Entity other) {

    }

    @Override
    public void update() {

        y = y + 10;

    }

    @Override
    public void render(Canvas canvas) {
      canvas.drawCircle(x,y,r,paint);

    }
}

