package com.example.techno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Account extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView username,deviceId,location,email,phone;
    SharedPreferences srd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        srd= getSharedPreferences("demo",MODE_PRIVATE);
        username = findViewById(R.id.username);
        username.setText(srd.getString("username",""));
        location = findViewById(R.id.location);
        location.setText(srd.getString("location",""));
        deviceId = findViewById(R.id.deviceId);
        deviceId.setText(srd.getString("deviceId",""));
        email = findViewById(R.id.email);
        email.setText(srd.getString("email",""));
        phone = findViewById(R.id.phone);
        phone.setText(srd.getString("phone",""));


        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.account:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.setSelectedItemId(R.id.account);
    }
}