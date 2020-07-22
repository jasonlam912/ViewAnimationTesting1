package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private ImageView imageView;
    private Button nextSampleButton;

    private Animation animation1;
    private Animation animation2;
    private Animation animation3;
    private Animation animation4;
    private AnimationSet animSet1;
    private AnimationSet animSet2;

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.imageView);
        nextSampleButton = findViewById(R.id.next_sample);

        animation1 = new TranslateAnimation(0f, 0f, 0f,-50f);
        animation1.setDuration(600);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animSet2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation2 = new TranslateAnimation(0f,0f,-50f,0f);
        animation2.setDuration(600);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animSet1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation3 = new ScaleAnimation(
                1f,0.8f,1f,0.8f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation3.setDuration(600);

        animation4 = new ScaleAnimation(
                0.8f,1f,0.8f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation4.setDuration(600);

        animSet1 = new AnimationSet(true);
        animSet1.addAnimation(animation1);
        animSet1.addAnimation(animation4);
        animSet2 = new AnimationSet(true);
        animSet2.addAnimation(animation2);
        animSet2.addAnimation(animation3);
        imageView.startAnimation(animSet1);

        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        Adapter adapter = new Adapter(this);
        rv.setAdapter(adapter);
    }

    public void fadeOut(View view) {
        Animation anim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade_out);
        imageView.startAnimation(anim);
    }

    public void translation(View view) {
        Animation anim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.translate);
        imageView.startAnimation(anim);
    }

    public void enlarge(View view) {
        Animation anim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.enlarge);
        imageView.startAnimation(anim);
    }

    public void rotation(View view) {
        Animation anim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.rotate);
        imageView.startAnimation(anim);
    }

    public void Lessen(View view) {
        Animation anim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.lessen);
        imageView.startAnimation(anim);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(Main2Activity.this, AnimViaJava.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                Main2Activity.this, new Pair<View, String>(nextSampleButton, nextSampleButton.getTransitionName()));
        startActivity(intent, optionsCompat.toBundle());
    }
}
