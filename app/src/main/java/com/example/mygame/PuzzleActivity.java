package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class PuzzleActivity extends AppCompatActivity {

    ImageView Img;
    boolean isChanged = false;

    TranslateAnimation left = new TranslateAnimation(0.f, 100.f, 0.f, 0.f);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.cl1);
        Img = new ImageView(this);
        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChanged) {
                    Img.setImageResource(R.drawable.timg1);
                } else {
                    Img.setImageResource(R.drawable.timg);
                }
                isChanged = !isChanged;
            }
        });
        Img.setImageResource(R.drawable.timg);

        Img.setScaleType(ImageView.ScaleType.MATRIX);
        Matrix matrix = new Matrix();
        matrix.preScale(0.5f, 0.5f);
        matrix.preTranslate(-500, -500);
        Img.setImageMatrix(matrix);
        ConstraintLayout.LayoutParams ImgParams = new ConstraintLayout.LayoutParams(
                500,
                500);
        ImgParams.leftToLeft = R.id.cl1;
        ImgParams.topToTop = R.id.cl1;
        // ImgParams.topMargin = height / 2;
        // ImgParams.leftMargin = width / 2;
        layout.addView(Img, ImgParams);
    }
}
