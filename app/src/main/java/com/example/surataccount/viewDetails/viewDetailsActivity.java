package com.example.surataccount.viewDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.surataccount.R;
import com.example.surataccount.billed.Fragment_Billed;
import com.example.surataccount.payment.PaymentFragment;
import com.example.surataccount.tranisition.Tranisition_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class viewDetailsActivity extends AppCompatActivity {
    public String balamt;
    BottomNavigationView bottomNavigationView;
    ImageView  backpesssicontansition;
    String stname,stcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_details);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        SharedPreferences sh = getSharedPreferences("stcodebmtname", MODE_PRIVATE);
        stname = sh.getString("stname", "");
        stcode = sh.getString("stcode", "");
        balamt = sh.getString("balamt", "");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedfragment=null;
               switch (menuItem.getItemId()){
                   case R.id.payment_item:

                    selectedfragment=new PaymentFragment();
                       Bundle bundles=new Bundle();
                       selectedfragment.setArguments(bundles);


                       break;
                   case R.id.billed_item:
                       selectedfragment=new Fragment_Billed();
                       Bundle bundlens=new Bundle();
                       selectedfragment.setArguments(bundlens);


                       break;
                   case R.id.trasition_item:
                       selectedfragment =new Tranisition_Fragment();
                       Bundle bundle=new Bundle();
                       selectedfragment.setArguments(bundle);

                        break;


               }

                getSupportFragmentManager().beginTransaction().replace(R.id.comtaine_item,selectedfragment).commit();

                return true;
            }

        });
        //as default main fragment show
        bottomNavigationView.setSelectedItemId(R.id.trasition_item);





    }

    @Override
    public void onBackPressed() {
        int seletedItemId = bottomNavigationView.getSelectedItemId();
        if (R.id.home != seletedItemId) {
            setHomeItem(viewDetailsActivity.this);
        } else {
            super.onBackPressed();
        }

    }
    private void setHomeItem(viewDetailsActivity viewDetailsActivity) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Intent intent=new Intent(viewDetailsActivity.this, PaymentModeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}


