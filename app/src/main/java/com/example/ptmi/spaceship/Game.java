package com.example.ptmi.spaceship;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;


import com.example.ptmi.spaceship.entity.Asteroid;
import com.example.ptmi.spaceship.entity.Entity;
import com.example.ptmi.spaceship.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Game extends Thread {
    public static Game game;
    public final String TAG = Game.class.getSimpleName();
    public int width;
    public int height;
    public int tick = 0;
    int b = 0;
    GameView gameView;
    GameActivity gameActivity;
    SurfaceHolder surfaceHolder;
    List<Entity> objects = new ArrayList<>(); // asteorids, bullets...
    Ship player;
    Intent i = new Intent();

    Random rnd = new Random();
    public Game(SurfaceHolder surfaceHolder) {

        this.surfaceHolder = surfaceHolder;
        game = this;
    }


    @Override
    public void run() {
        super.run();
        init();


        while (true) {


            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                canvas.drawBitmap(GameView.bitmap4, 0, 0, null);


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
        objects.add(player);

    }

    private void update() {

        tick++;


        if (tick % 50 == 0) {
            Entity asteroid = new Asteroid(rnd.nextInt(width + 1), -10);
            addEntity(asteroid);

        }

        // update
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }

        // utkozes

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                Entity e1 = objects.get(i);
                Entity e2 = objects.get(j);
                if (e1.isColliding(e2)) {
                    e2.collison(e1);
                    e1.collison(e2);
                }
                if (e2.isColliding(player)) {
                    player.collison(e2);
                    e2.collison(player);
                    objects.remove(j);


                }

            }

        }


        for (int i = 0; i < objects.size(); i++) {
            Entity entity = objects.get(i);
            if (entity.getY() > height + entity.getR() || entity.isDead()) {
                objects.remove(i);
            }
        }
    }

    public void addEntity(Entity e) {
        objects.add(e);

    }


    private void render(Canvas canvas) {
        for (b = 0; b < objects.size(); b++) {
            objects.get(b).render(canvas);
        }

        if (player.dead == true) {
            player.kill();
            if (game.tick % 5 == 0) {
                canvas.drawBitmap(GameView.expl1, player.getX() - 105, player.getY(), null);


            }
            if (game.tick % 15 == 0) {
                canvas.drawBitmap(GameView.expl2, player.getX() - 105, player.getY(), null);


            }
            if (game.tick % 20 == 0) {
                canvas.drawBitmap(GameView.expl3, player.getX() - 105, player.getY(), null);

            }
            if (game.tick % 25 == 0) {
                canvas.drawBitmap(GameView.expl4, player.getX() - 105, player.getY(), null);


            }
            if (game.tick % 45 == 0) {

                Intent i = new Intent(gameActivity.context, OverActivity.class);
                gameActivity.intent();



            }


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
