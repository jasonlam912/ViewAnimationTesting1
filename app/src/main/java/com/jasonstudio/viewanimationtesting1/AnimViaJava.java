package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.jasonstudio.viewanimationtesting1.customview.FloatingImageView;

public class AnimViaJava extends AppCompatActivity {

    private FloatingImageView imageView;
    private float fromDegree = 0;
    private float toDegree =0;

    private Animation animation1;
    private Animation animation2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_via_java);
        imageView = findViewById(R.id.imageView);

    }

    public void fadeOut(View view) {
        Animation animation = new AlphaAnimation(1,0);
        animation.setDuration(2000);
        imageView.startAnimation(animation);
    }

    public void translation(View view) {
        Animation animation = new TranslateAnimation(0f,100f,0,-100f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    public void enlarge(View view) {
        Animation animation = new ScaleAnimation(
                0.7f,1f,0.7f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    public void lessen(View view) {
        Animation animation = new ScaleAnimation(
                1f,0.7f,1f,0.7f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);

        animation.setDuration(300);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }

    public void rotation(View view) {
        float rotatDegree = 90;
        toDegree = rotatDegree + fromDegree;
        RotateAnimation animation = new RotateAnimation(fromDegree,toDegree,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        fromDegree = toDegree;
        animation.setDuration(1000);
        animation.setFillAfter(true);

        Log.d("imageRotation",Float.toString(imageView.getRotation()));
        Log.d("imageRotation",Float.toString(imageView.getRotationX()));
        Log.d("imageRotation",Float.toString(imageView.getRotationY()));
        imageView.startAnimation(animation);
    }

    public void floating(View view) {

    }

    public void nextSample(View view) {
        Button button = findViewById(R.id.next_sample);
        Pair pair = new Pair<View,String>(button, button.getTransitionName());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,pair);
        startActivity(new Intent(AnimViaJava.this, SVGSampleActivity.class), optionsCompat.toBundle());
    }
}
