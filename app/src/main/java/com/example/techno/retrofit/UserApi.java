package com.example.techno.retrofit;

import com.example.techno.model.SensorData;
import com.example.techno.model.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/users/get-all")
    Call<List<Users>> getAllUsers();

    @GET("/users/username")
    Call<Users> findByUsername(@Query("username") String username);

    @GET("/users/deviceId")
    Call<Boolean> isUniqueDeviceId(@Query("deviceId") String deviceId);

    @POST("/users/save")
    Call<Users> save(@Body Users user);

    @GET("/users/sdata")
    Call<ArrayList<SensorData>> getSensorData(String username, Date startDate , Date endDate);

}
