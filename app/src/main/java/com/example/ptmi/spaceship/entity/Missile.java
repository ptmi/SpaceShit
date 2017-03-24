

package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static com.example.ptmi.spaceship.Game.game;


public class Missile extends Entity{

    public int width = game.width;
    public int height = game.height;

    Paint paint = new Paint();


    public Missile(float x, float y){
        super(x, y);
        paint.setColor(Color.RED);
        r = 15;

    }

    public void collison(Entity other) {
        if (other instanceof Ship)
            return;
        kill();
        other.kill();
    }



    @Override
    public void update() {
        y -= 20;


    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawCircle(x, y, r, paint);

    }
}
