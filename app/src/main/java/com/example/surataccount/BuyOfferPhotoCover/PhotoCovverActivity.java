package com.example.surataccount.BuyOfferPhotoCover;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoCovverActivity extends AppCompatActivity {
    RecyclerView photo_cover_recyclerView;
    ImageView back_pess;
    ProgressBar progressBar;
//    LinearLayoutManager linearLayoutManager;
    List<PhotoCoverTable.All_Photo> Show_data;
    PhotoCoverAdpterClass photoCoverAdpterClass;
    PhotoCoverTable photoCoverTable;
    String photo_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_photo_covver);
        //ImageView id'g
        back_pess=(ImageView)findViewById(R.id.photo_cover_back_button);
        //Recycler view
       photo_cover_recyclerView=(RecyclerView)findViewById(R.id.photo_cover_recycler_view);
       //progress bar
        progressBar=(ProgressBar)findViewById(R.id.photocoverprogreeBar);
       photo_cover_recyclerView.setHasFixedSize(true);
       photo_cover_recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
     // photo_cover_recyclerView.setLayoutManager(linearLayoutManager);
        //back press
        back_pess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhotoCovverActivity.this, OfferPageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //get data
        Intent i=getIntent();
        photo_id=i.getStringExtra("photo_id");
        SharedPreferences sharedPreferences=getSharedPreferences("photo_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("photoId",photo_id);
        editor.commit();
        //viewData
        viewdatas();
    }

    private void viewdatas() {
        ApiInterfaceClass apiInterfaceClass = BaseUrlClass.getClient(PhotoCovverActivity.this).create(ApiInterfaceClass.class);
       Call<PhotoCoverTable> photoCoverTableCall=apiInterfaceClass.getPhotoCover(photo_id);
       photoCoverTableCall.enqueue(new Callback<PhotoCoverTable>() {
           @Override
           public void onResponse(Call<PhotoCoverTable> call, Response<PhotoCoverTable> response) {
               photoCoverTable=response.body();
               progressBar.setVisibility(View.GONE);
               if(response.isSuccessful())
               {
                   Show_data = response.body().datals;
                   photoCoverAdpterClass=new PhotoCoverAdpterClass(PhotoCovverActivity.this,Show_data);
                   photo_cover_recyclerView.setAdapter(photoCoverAdpterClass);
               }
           }

           @Override
           public void onFailure(Call<PhotoCoverTable> call, Throwable t) {
               Toast.makeText(PhotoCovverActivity.this, "Some thing worrng..", Toast.LENGTH_SHORT).show();

           }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PhotoCovverActivity.this, OfferPageActivity.class);
        startActivity(intent);
        finish();
    }
}