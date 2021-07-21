package com.example.gymdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout temperatura;
    LinearLayout privzeteNastavitve;
    LinearLayout zrak;
    LinearLayout vlaga;
    LinearLayout svetilnost;
    LinearLayout cas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperatura = (LinearLayout)findViewById(R.id.Temperatura);
        privzeteNastavitve = (LinearLayout)findViewById(R.id.PrivzeteNastavitve);
        zrak = (LinearLayout)findViewById(R.id.Zrak);
        vlaga = (LinearLayout)findViewById(R.id.Vlaga);
        svetilnost = (LinearLayout)findViewById(R.id.Svetilnost);
        cas = (LinearLayout)findViewById(R.id.Cas);



        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                temperatura.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, Temperatura.class);
            startActivity(intent);

            }
        });




        privzeteNastavitve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                privzeteNastavitve.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, PrivzeteNastavitve.class);
                startActivity(intent);
            }
        });

        cas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                cas.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, Cas.class);
                startActivity(intent);
            }
        });


        zrak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                zrak.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, Zrak.class);
                startActivity(intent);
            }
        });

        vlaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                vlaga.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, Vlaga.class);
                startActivity(intent);
            }
        });


        svetilnost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                svetilnost.startAnimation(animFadeOut);

                Intent intent = new Intent(MainActivity.this, Svetilnost.class);
                startActivity(intent);

            }
        });



    }
}
