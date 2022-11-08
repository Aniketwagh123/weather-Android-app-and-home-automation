package com.example.techno.retrofit;

import com.example.techno.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/Users/get-all")
    Call<List<Users>> getAllUsers();

    @GET("/Users/username")
    Call<Users> findByUsername(@Query("username") String username);

    @GET("/Users/deviceId")
    Call<Boolean> isUniqueDeviceId(@Query("deviceId") String deviceId);

    @POST("/Users/save")
    Call<Users> save(@Body Users user);

}
