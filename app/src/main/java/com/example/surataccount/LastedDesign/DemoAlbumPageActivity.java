package com.example.surataccount.LastedDesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surataccount.ApiInterface.ApiInterfaceClass;
import com.example.surataccount.BaseUrl.BaseUrlClass;
import com.example.surataccount.Dashboard.Dashboard;
import com.example.surataccount.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoAlbumPageActivity extends AppCompatActivity {
    ImageView back_presh;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_demo_album_page);
        init();
        back_presh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DemoAlbumPageActivity.this, LastedDesignActivity.class);
                startActivity(intent);
                finish();
            }
        });
        linearLayoutManager=new LinearLayoutManager(DemoAlbumPageActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewData();
    }

    private void viewData() {
        ProgressDialog progressDialog=new ProgressDialog(DemoAlbumPageActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        ApiInterfaceClass apiInterfaceClass= BaseUrlClass.getClient(DemoAlbumPageActivity.this).create(ApiInterfaceClass.class);
        Call<DemoDesignModel> call=apiInterfaceClass.getDemoAlbumData("1");
        call.enqueue(new Callback<DemoDesignModel>() {
            @Override
            public void onResponse(Call<DemoDesignModel> call, Response<DemoDesignModel> response) {
                DemoDesignModel demoDesignModel=response.body();
                if(response.isSuccessful())
                {
                    if(demoDesignModel.status.equals("Success"))
                    {
                        List<DemoDesignModel.DESIGNData> data=new ArrayList<>();
                        data=response.body().getDesihn;
                        DemoDesignAdpterClass adpterClass=new DemoDesignAdpterClass(DemoAlbumPageActivity.this,data);
                        if(adpterClass.getItemCount()>0)
                        {
                            recyclerView.setAdapter(adpterClass);
                            progressDialog.dismiss();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            final Dialog dialog = new Dialog(DemoAlbumPageActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                            dialog.setContentView(R.layout.nodara_found_layout_file);
                            dialog.setCancelable(true);

                            ((Button) dialog.findViewById(R.id.nodata_btn)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            });

                            dialog.show();

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<DemoDesignModel> call, Throwable t) {
                Toast.makeText(DemoAlbumPageActivity.this, "failure working..", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void init() {
        recyclerView=(RecyclerView)findViewById(R.id.demo_design_recylerview);
        back_presh=(ImageView)findViewById(R.id.im_demo_design);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(DemoAlbumPageActivity.this, LastedDesignActivity.class);
        startActivity(intent);
        finish();
    }
}