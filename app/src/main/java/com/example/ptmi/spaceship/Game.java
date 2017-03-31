package com.example.ptmi.spaceship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;


import com.example.ptmi.spaceship.entity.Asteroid;
import com.example.ptmi.spaceship.entity.Entity;
import com.example.ptmi.spaceship.entity.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ptmi on 2017.03.17..
 */

public class Game extends Thread {
    public static Game game;
    public final String TAG = Game.class.getSimpleName();
    public int width;
    public int height;
    public int tick = 0;
    public Context context;
    int b = 0;
    int score;
    GameView gameView;
    GameActivity gameActivity;
    SurfaceHolder surfaceHolder;
    List<Entity> objects = new ArrayList<>(); // asteorids, bullets...
    Ship player;
    Intent i = new Intent();
    Paint paint = new Paint();
    MediaPlayer exp = new MediaPlayer();
    MediaPlayer hit = new MediaPlayer();
    MediaPlayer over = new MediaPlayer();
    MediaPlayer explode = new MediaPlayer();





    Random rnd = new Random();

    public Game(Context context, SurfaceHolder surfaceHolder) {


        this.context = context;
        hit = MediaPlayer.create(this.context, R.raw.hit);
        exp = MediaPlayer.create(this.context, R.raw.exp);
        over = MediaPlayer.create(this.context, R.raw.over);
        explode = MediaPlayer.create(this.context, R.raw.explode);
        this.surfaceHolder = surfaceHolder;
        game = this;
        paint.setColor(Color.MAGENTA);
        score = 0;
        paint.setTextSize(100);


    }


    public void context(Context context) {
        context = gameActivity.context;

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


        if (tick % 40 == 0) {
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
                    score += 1;
                    hit.start();

                }
                if (e2.isColliding(player)) {
                    player.collison(e2);
                    e2.collison(player);
                    objects.remove(j);
                    exp.start();


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


        canvas.drawText("Score:" + String.valueOf(score), 2, 80, paint);



        if (player.dead == true) {
            player.kill();
            explode.start();
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


                GameActivity.gameActivity.intent();



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
