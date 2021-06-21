package com.example.surataccount.BuyOfferPhotoCover;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.surataccount.R;

public class PhotoCoverFullScreenActivity extends AppCompatActivity {
ImageView photo,backpess;
TextView mrp;
   String photo_id="";
    private float mScaleFactor = 1.0f;
   private ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_photo_cover_full_screen);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        //imageView
        photo=(ImageView)findViewById(R.id.photo_cover_full_screen_img);
        backpess=(ImageView)findViewById(R.id.photocoverfullscreen_back_press);
        //textview
        mrp=(TextView)findViewById(R.id.photo_cover_fully_screen_mrp);
        //get Shared perference
        SharedPreferences shared = getSharedPreferences("photo_id", MODE_PRIVATE);
    photo_id = (shared.getString("photoId", ""));
       // Toast.makeText(this, "pho id is: "+channel, Toast.LENGTH_SHORT).show();
        //get imge ul
        final Intent intent=getIntent();

        String URL = intent.getStringExtra("img");

        Glide.with(this).load(URL).into(photo);
        String MRP = intent.getStringExtra("mrp");
        mrp.setText("MRP -"+MRP);
        //bac press
        backpess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PhotoCoverFullScreenActivity.this,PhotoCovverActivity.class);
                intent1.putExtra("photo_id",photo_id);
                startActivity(intent1);
                finish();
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends  ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            photo.setScaleX(mScaleFactor);
            photo.setScaleY(mScaleFactor);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1=new Intent(PhotoCoverFullScreenActivity.this,PhotoCovverActivity.class);
        intent1.putExtra("photo_id",photo_id);
        startActivity(intent1);
        finish();
    }
}