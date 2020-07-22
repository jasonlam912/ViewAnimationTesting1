package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasonstudio.viewanimationtesting1.customview.CustomImageView;

import static android.view.MotionEvent.ACTION_UP;

public class MainActivity extends AppCompatActivity {

    private TextView txtMessage;
    private Button btn;
    private Animation animFadein;

    private CustomImageView imageView;

    private Button nextSampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessage = findViewById(R.id.text);
        btn = findViewById(R.id.btn);
        animFadein = AnimationUtils.loadAnimation(
                this.getApplicationContext(), R.anim.fade_in);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMessage.setVisibility(View.VISIBLE);
                txtMessage.startAnimation(animFadein);
            }
        });

        imageView = findViewById(R.id.imageView);

        nextSampleButton = findViewById(R.id.next_sample);


        nextSampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,Main2Activity.class);
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this, nextSampleButton, ViewCompat.getTransitionName(nextSampleButton));
                startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    private void springAnimation(){

    }
}
