package com.example.a22_animazioni;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    ImageView image;
    RelativeLayout root_view;
    Animation animazione_attuale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        root_view = findViewById(R.id.view_root);
    }

    public void onClickRotazione(View v) {
        animazione_attuale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotazione);
        image.startAnimation(animazione_attuale);
    }

    public void onClickTraslazione(View v) {
        animazione_attuale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.traslazione);
        image.startAnimation(animazione_attuale);
    }

    public void onClickScaling(View v) {
        animazione_attuale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaling);
        image.startAnimation(animazione_attuale);
    }

    public void onClickTrasparenza(View v) {
        animazione_attuale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trasparenza);
        image.startAnimation(animazione_attuale);
    }

    public void onClickCombo(View v) {
        animazione_attuale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.combo);
        animazione_attuale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                root_view.removeView(image);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        image.startAnimation(animazione_attuale);
    }

}