package com.example.techno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techno.info.HeatIndex;
import com.example.techno.info.Humidity;
import com.example.techno.info.RelativeHumidity;
import com.example.techno.info.Temperature;
import com.example.techno.model.HomeAtomation;
import com.example.techno.model.SensorData;
import com.example.techno.model.Users;
import com.example.techno.retrofit.RetrofitService;
import com.example.techno.retrofit.UserApi;
import com.example.techno.vp2.ViewpagerItem;
import com.example.techno.vp2.VpAdapter;
import com.github.anastr.speedviewlib.AwesomeSpeedometer;
import com.github.anastr.speedviewlib.PointerSpeedometer;
import com.github.mikephil.charting.data.Entry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.gusavila92.websocketclient.WebSocketClient;

public class Dashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager2;
    ArrayList<ViewpagerItem>viewpagerItemArrayList;
    private RelativeLayout humidity,temperature ,relativeHumidity ,heatIndex;
    private SwitchCompat sw1,sw2;
    UserApi userApi;
    PointerSpeedometer speedometer;
    AwesomeSpeedometer speedometer1;




//    private WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RetrofitService retrofitService = new RetrofitService();
        userApi = retrofitService.getRetrofit().create(UserApi.class);
        SharedPreferences srd = getSharedPreferences("demo",MODE_PRIVATE);
        String username = srd.getString("username","");



        WebView webView = findViewById(R.id.web);
        webView.loadUrl("http://13.235.110.111:4001");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
//        createWebSocketClient();
        Log.v("WebSocket", "working");


        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.home);
//        sw1 = findViewById(R.id.switch1);
//        sw1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String statusSwitch1;
//                if (sw1.isChecked())
//                    statusSwitch1 = sw1.getTextOn().toString();
//                else
//                    statusSwitch1 = sw1.getTextOff().toString();
//                Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1, Toast.LENGTH_LONG).show(); // display the current state for switch's
//                /////////////////////////////////
//                HomeAtomation homeAtomation = new HomeAtomation();
//                homeAtomation.setRowsNo(1);
//                homeAtomation.setR1(statusSwitch1);
//                homeAtomation.setR2("OFF");
//                userApi.status(homeAtomation)
//                        .enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                Log.v("relay",response.toString());
//                            }
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//
//                            }
//                        });
//            }
//        });
//        sw2 = findViewById(R.id.switch2);
//        sw2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String statusSwitch1;
//                if (sw2.isChecked())
//                    statusSwitch1 = sw2.getTextOn().toString();
//                else
//                    statusSwitch1 = sw2.getTextOff().toString();
//                Toast.makeText(getApplicationContext(), "Switch2 :" + statusSwitch1, Toast.LENGTH_LONG).show(); // display the current state for switch's
//                /////////////////////////////////
//                HomeAtomation homeAtomation = new HomeAtomation();
//                homeAtomation.setRowsNo(1);
//                homeAtomation.setR1("OFF");
//                homeAtomation.setR2(statusSwitch1);
//                userApi.status(homeAtomation)
//                        .enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                Log.v("relay",response.toString());
//                            }
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//
//                            }
//                        });
//            }
//        });





//            RetrofitService retrofitService = new RetrofitService();
//            UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
//
//            Log.v("eee","Hi i am live");
//            SharedPreferences srd = getSharedPreferences("demo",MODE_PRIVATE);
//            String username = srd.getString("username","");
//            userApi.findByUsername(username)
//                    .enqueue(new Callback<Users>() {
//                        @Override
//                        public void onResponse(Call<Users> call, Response<Users> response) {
//                            List<SensorData> data = response.body().getSensor_data();
//                            Log.v("eee",data.toString());
//                            for (int i = 0 ; i<data.size();i++){
//                                dataValues.add(new Entry(i,data.get(i).getHumidity()));
//                            }
//                            designChart();
//
//                        }
//                        @Override
//                        public void onFailure(Call<Users> call, Throwable t) {
//                            Log.v("eee",t+"ncnvc");
//                        }
//                    });
//            refresh(5000);
//        }
//        bottomNavigationView.setBackgroundColor();
        getLiveData(username);
//        Log.v("cheack",arr.toString());
//        speedometer = findViewById(R.id.pointerSpeedometer);
////        speedometer.setSpeedAt(60);
//        speedometer.speedTo(arr[0],4000);
////        speedometer.setUnit("Hz");
//        speedometer.setPointerColor(R.color.white);
//        speedometer.setSpeedometerColor(R.color.white);
//        speedometer.setCenterCircleColor(R.color.black);
//        speedometer.setIndicatorLightColor(R.color.white);
////        speedometer.setBackgroundCircleColor(R.color.green);









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
        String[] date = {"Nov 13,2022","Nov 14,2022","Nov 15,2022","Nov 16,2022","Nov 10,2022","Nov 11,2022","Nov 12,2022"};
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

//    private void createWebSocketClient() {
//        URI uri;
//        try {
//            // Connect to local host http://13.235.110.111:8080/
//            uri = new URI("wss://13.235.110.111:4001");
//            Log.v("WebSocket", "connecting.. "+uri);
//        }
//        catch (URISyntaxException e) {
//            Log.v("WebSocket", "sorry.. "+e.toString());
//            e.printStackTrace();
//            return;
//        }
//
//        webSocketClient = new WebSocketClient(uri) {
//            @Override
//            public void onOpen() {
//                Log.v("WebSocket", "Session is starting");
//                webSocketClient.send("Hello World!");
//            }
//
//            @Override
//            public void onTextReceived(String s) {
//                Log.v("WebSocket", "Message received");
//                final String message = s;
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        try{
////                            TextView textView = findViewById(R.id.animalSound);
////                            textView.setText(message);
////                        } catch (Exception e){
////                            e.printStackTrace();
////                        }
////                    }
////                });
//            }
//
//            @Override
//            public void onBinaryReceived(byte[] data) {
//            }
//
//            @Override
//            public void onPingReceived(byte[] data) {
//            }
//
//            @Override
//            public void onPongReceived(byte[] data) {
//            }
//
//            @Override
//            public void onException(Exception e) {
//                System.out.println(e.getMessage());
//            }
//
//            @Override
//            public void onCloseReceived() {
//                Log.i("WebSocket", "Closed ");
//                System.out.println("onCloseReceived");
//            }
//        };
//
//        webSocketClient.setConnectTimeout(10000);
//        webSocketClient.setReadTimeout(60000);
//        webSocketClient.enableAutomaticReconnection(5000);
//        webSocketClient.connect();
//    }
//    public void sendMessage(View view) {
//        Log.i("WebSocket", "Button was clicked");
//
//        // Send button id string to WebSocket Server
//        switch(view.getId()){
//            case(R.id.dogButton):
//                webSocketClient.send("1");
//                break;
//
//            case(R.id.catButton):
//                webSocketClient.send("2");
//                break;
//
//            case(R.id.pigButton):
//                webSocketClient.send("3");
//                break;
//
//            case(R.id.foxButton):
//                webSocketClient.send("4");
//                break;
//        }
//    }

    private int[] getLiveData(String username){
        int[] arr = {0,0};
        userApi.findByUsername(username)
                .enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        List<SensorData> data = response.body().getSensor_data();
                        arr[0]=data.get(0).getHumidity();
                        arr[1]=data.get(0).getTemp();
                        Log.v("cheack","  "+arr[0]);
                        speedometer = findViewById(R.id.pointerSpeedometer);
                        speedometer.setSpeedAt(arr[0]);
                        speedometer.setUnit("g.m-3");
                        speedometer.setPointerColor(R.color.white);
                        speedometer.setSpeedometerColor(R.color.white);
                        speedometer.setCenterCircleColor(R.color.black);
                        speedometer.setIndicatorLightColor(R.color.white);

                        speedometer1 = findViewById(R.id.awesomeSpeedometer);
                        speedometer1.setSpeedAt(arr[1]);
                        speedometer1.setUnit("°C");


                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Log.v("eee",t+"ncnvc");
                    }
                });


        refresh(1000,username);
        return arr;
    }
    private void refresh(int milliSeconds,String username) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable(){
            @Override
            public void run(){

                getLiveData(username);
            }
        };
        handler.postDelayed(runnable,milliSeconds);
    }

}