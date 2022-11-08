package com.example.techno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.techno.info.HeatIndex;
import com.example.techno.info.Humidity;
import com.example.techno.info.RelativeHumidity;
import com.example.techno.info.Temperature;
import com.example.techno.vp2.ViewpagerItem;
import com.example.techno.vp2.VpAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    ArrayList<ViewpagerItem>viewpagerItemArrayList;
    private RelativeLayout humidity,temperature ,relativeHumidity ,heatIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.home);
//        bottomNavigationView.setBackgroundColor();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        humidity = findViewById(R.id.humidity);
        relativeHumidity = findViewById(R.id.relative_humidity);
        temperature = findViewById(R.id.temp);
        heatIndex = findViewById(R.id.heat_index);

        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Humidity.class));
            }
        });
        relativeHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RelativeHumidity.class));
            }
        });
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Temperature.class));
            }
        });
        heatIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HeatIndex.class));
            }
        });


        viewPager2 = findViewById(R.id.viewPager);
//        int[] images = {R.drawable.mobile_login,R.drawable.forget_pass,R.drawable.profile_top,R.drawable.signuppic};
//        String[] headings = {"Login","Forget password","Profile","SignUp"};
//        String[] descriptions = {getString(R.string.lorem),getString(R.string.lorem),getString(R.string.lorem),getString(R.string.lorem)};

        String[] day = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        String[] date = {"Nov 16,2022","Nov 17,2022","Nov 18,2022","Nov 19,2022","Nov 20,2022","Nov 21,2022","Nov 22,2022"};
        String[] humidity = {"30 g.m-3","31 g.m-3","32 g.m-3","33 g.m-3","34 g.m-3","45 g.m-3","46 g.m-3"};
        String[] temperature = {"50 °C","51 °C","52 °C","53 °C","54 °C","55 °C","56 °C"};

        viewpagerItemArrayList = new ArrayList<>();
        for (int i = 0 ; i<day.length ; i++){
            ViewpagerItem viewpagerItem = new ViewpagerItem(day[i],date[i],humidity[i],temperature[i]);
            viewpagerItemArrayList.add(viewpagerItem);
        }

        VpAdapter vpAdapter = new VpAdapter(viewpagerItemArrayList);
        viewPager2.setAdapter(vpAdapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setCurrentItem(3, true);
//
//
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_ALWAYS);
//
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer((new MarginPageTransformer(5)));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f+r*0.14f);

            }
        });
        viewPager2.setPageTransformer(transformer);
//
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.setSelectedItemId(R.id.home);
    }


}