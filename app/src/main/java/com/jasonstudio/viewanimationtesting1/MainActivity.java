package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
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
    private Button expandLVButton;
    private Button revButton;
    private Button lvButton;

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
        revButton = findViewById(R.id.expandableView);
        revButton.setOnClickListener(b -> {
            Intent intent = new Intent(MainActivity.this, RecyclerExpandableView.class);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    MainActivity.this, revButton, revButton.getTransitionName());
            startActivity(intent, activityOptions.toBundle());
        });
        expandLVButton = findViewById(R.id.expandableListView);
        expandLVButton.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, ExpandLVActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, new Pair<View,String>(expandLVButton, expandLVButton.getTransitionName()));
            startActivity(intent, optionsCompat.toBundle());
        });
        lvButton = findViewById(R.id.expandListView);
        lvButton.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, LVActivity.class);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    new Pair<>(lvButton, lvButton.getTransitionName()));
            startActivity(intent, optionsCompat.toBundle());
        });
    }
}
