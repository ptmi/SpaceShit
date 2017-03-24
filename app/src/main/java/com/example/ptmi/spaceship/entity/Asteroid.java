

package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ptmi.spaceship.Game;
import com.example.ptmi.spaceship.GameView;

import java.util.Random;



public class Asteroid extends Entity {
    Game game;

    public int width = game.width;
    public int height = game.height;
    float x,y,r;


    Paint paint = new Paint();
    Random rnd = new Random();


    public Asteroid(){
        r = 10;
        y = height;
        x = 0;
    }

    @Override
    public void update() {
        x = rnd.nextInt(width + 1);
        y = y+20;

    }

    @Override
    public void render(Canvas canvas) {
      canvas.drawCircle(x,y,r,paint);

    }
}

