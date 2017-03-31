package com.example.ptmi.spaceship;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static com.example.ptmi.spaceship.R.layout.activity_game;


public class OverActivity extends AppCompatActivity {

    Button button2;
    TextView textView2;
    TextView textView3;
    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_over);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);


        final Intent i = new Intent(OverActivity.this, GameActivity.class);
        button2 = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView2.setTextSize(40);
        textView3.setTextSize(40);
        textView5.setTextSize(20);


        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        if (Game.game.score > highScore) {
            textView5.setText(String.valueOf(Game.game.score));
            textView2.setText(String.valueOf(Game.game.score));

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", Game.game.score);
            editor.commit();

        } else {
            textView5.setText(String.valueOf(highScore));
            textView2.setText(String.valueOf(Game.game.score));

        }



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });


    }
}


