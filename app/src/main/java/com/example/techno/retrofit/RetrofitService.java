package com.example.techno.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.30.251:8080") // IP/url of system where server is running
//                .baseUrl("http://13.235.110.111:8080") // IP/url of system where server is running(AWS)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
