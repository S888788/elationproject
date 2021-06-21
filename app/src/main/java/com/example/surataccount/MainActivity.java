package com.example.surataccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.User.LoginActivity;
import com.example.surataccount.User.Session_Use;



public class MainActivity extends AppCompatActivity {
Animation zoom_anima;
ImageView imageView;
Connection_Internet_Classs connection_internet_classs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        connection_internet_classs=new Connection_Internet_Classs(this);
        zoom_anima= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
        imageView=(ImageView)findViewById(R.id.im_zoom_animatiomn);
        imageView.startAnimation(zoom_anima);
        if (connection_internet_classs.isConnected())
        {

            final Session_Use user=new Session_Use(MainActivity.this);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (user.getUsename()!="")  {
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }
            },5000);
        }
        else
        {
            Toast.makeText(this, "Check  Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

}
