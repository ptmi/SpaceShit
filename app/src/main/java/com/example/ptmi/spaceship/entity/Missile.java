

package com.example.ptmi.spaceship.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.ptmi.spaceship.GameView;

import static com.example.ptmi.spaceship.Game.game;


public class Missile extends Entity{

    public int width = game.width;
    public int height = game.height;
    GameView gameView;

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
        y -= 35;


    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(GameView.bitmap2, x, y, paint);

    }

    @Override
    public void hpDecrease(Canvas canvas) {

    }
}
