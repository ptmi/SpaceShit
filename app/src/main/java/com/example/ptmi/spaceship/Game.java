package com.example.ptmi.spaceship;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;


import com.example.ptmi.spaceship.entity.Entity;
import com.example.ptmi.spaceship.entity.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Game extends Thread {
    public final String TAG = Game.class.getSimpleName();
    public int width;
    public int height;
    SurfaceHolder surfaceHolder;
    List<Entity> objects = new ArrayList<>(); // asteorids, bullets...
    Ship player;

    public Game(SurfaceHolder surfaceHolder) {

        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        super.run();
        init();

        while (true) {


            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                canvas.drawColor(Color.BLUE);

                update();
                render(canvas);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }


            try {
                sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void init() {
        // create player obj

        player = new Ship(width / 2, height / 2 + 400);

    }

    private void update() {


        player.update();

        // utkozes

        for (int i = 0; i < objects.size(); i++) {
            Entity e = objects.get(i);
           /* if (e instanceof Asteroid) {
                Asteroid a = (Asteroid) e;

                a.getRect().intersect(Ship.getRect());// utkozes
            }*/
        }

        // update
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
    }

    private void render(Canvas canvas) {
        player.render(canvas);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(canvas);
        }
    }

    public boolean onTouch(MotionEvent event) { // itt van a jatekhoz kapcsolodo logika
        Log.v(TAG, event.toString());

        if (event.getAction() == MotionEvent.ACTION_MOVE ||
                event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            //player.setX(event.getX());
            //   player.setY(event.getY());
            player.newX = event.getX();
            player.newY = event.getY();



            return true; // return true ha lekezeltuk az eventet
        }
        return false; // false ha nem es akkor tovabbadhatja mas view-knak
    }
}
