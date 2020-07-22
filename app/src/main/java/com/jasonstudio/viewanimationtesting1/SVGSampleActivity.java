package com.jasonstudio.viewanimationtesting1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SVGSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_v_g_sample);
    }

    public void changeShape(View view) {
        ImageView image = findViewById(R.id.imageView2);
        AnimatedVectorDrawable anim = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.svg_rect_anim);
        image.setImageDrawable(anim);
        anim.start();
    }

    public void drawJ(View view) {
        ImageView j = findViewById(R.id.j_text);
        Drawable image = j.getDrawable();
        Log.d("drawJ", Boolean.toString(image instanceof Animatable));
        if(image instanceof Animatable){
            ((Animatable)image).start();
        }
        /*AnimatedVectorDrawable anim = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.svg_line_j_anim);
        j.setImageDrawable(anim);
        anim.start();*/
    }

    public void showMyName(View view) {
        List<Integer> temp = new ArrayList<>(Arrays.asList(R.id.j_text, R.id.a_text, R.id.s_text, R.id.o_text, R.id.n_text));
        for(Integer id:temp){
            ImageView imageView = findViewById(id);
            ((Animatable)imageView.getDrawable()).start();
        }
    }
}
