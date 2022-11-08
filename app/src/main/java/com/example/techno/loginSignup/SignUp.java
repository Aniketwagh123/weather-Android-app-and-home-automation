package com.example.techno.loginSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.techno.R;
import com.example.techno.model.Email;
import com.example.techno.model.Phone;
import com.example.techno.model.SensorData;
import com.example.techno.model.Users;
import com.example.techno.retrofit.RetrofitService;
import com.example.techno.retrofit.UserApi;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextInputLayout username,password,email,phoneNumber,deviceId,location;
    Users user;
    AppCompatButton signUpButton,already_has_account_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.create_account_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////
                username = findViewById(R.id.checkName);
                password = findViewById(R.id.checkPassword);
                email = findViewById(R.id.checkEmail);
                phoneNumber = findViewById(R.id.checkPhone);
                deviceId = findViewById(R.id.checkDevId);
                location = findViewById(R.id.checkLocation);

                //////
                String userNameVal = username.getEditText().getText().toString();
                String passwordVal = password.getEditText().getText().toString();
                String emailVal = email.getEditText().getText().toString();
                String phoneNumberVal = phoneNumber.getEditText().getText().toString();
                String deviceIdVal = deviceId.getEditText().getText().toString();
                String locationVal = location.getEditText().getText().toString();

                //////
                user = new Users();
                user.setUserName(userNameVal);
                user.setPassword(passwordVal);

                List<Email>emails = new ArrayList<>();
                Email email = new Email();
                email.setEmailId(emailVal);
                emails.add(email);
                user.setEmails(emails);

                List<Phone>phones = new ArrayList<>();
                Phone phone = new Phone();
                phone.setPhoneNumber(phoneNumberVal);
                phones.add(phone);
                user.setPhoneNumbers(phones);

                List<SensorData>sensorsData = new ArrayList<>();
                user.setSensor_data(sensorsData);

                user.setDeviceId(deviceIdVal);
                user.setUserLocation(locationVal);

                //////
                RetrofitService retrofitService = new RetrofitService();
                UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

                ////check unique id


                userApi.findByUsername(userNameVal)
                        .enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(Call<Users> call, Response<Users> response) {

                                if(response.body()!=null){
                                    Toast toast = Toast.makeText(getApplicationContext(), "UserName already exist", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Users> call, Throwable t) {
                                Log.v("user",t.toString());
                                Log.v("user","in");
                                userApi.isUniqueDeviceId(deviceIdVal)
                                        .enqueue(new Callback<Boolean>() {
                                            @Override
                                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                                Log.v("msg", "in on res"+response.body());
                                                if(response.body()){
                                                    if (passwordVal.length()>=5) {
                                                        userApi.save(user)
                                                                .enqueue(new Callback<Users>() {
                                                                    @Override
                                                                    public void onResponse(Call<Users> call, Response<Users> response) {
                                                                        Log.v("user", response.body().toString());
                                                                        Toast.makeText(SignUp.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                                                        startActivity(new Intent(getApplicationContext(), Login.class));
                                                                        finish();
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<Users> call, Throwable t) {
                                                                        Log.v("user", t.toString());
                                                                        Toast.makeText(SignUp.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                });
                                                    }
                                                    else {
                                                        Toast.makeText(SignUp.this, "Please enter minimum 5 character password", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                                else Toast.makeText(SignUp.this, "DeviceId already present", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<Boolean> call, Throwable t) {
                                                Log.v("msg", "in on fail");
                                            }
                                        });

                            }
                        });
//
            }
        });
        already_has_account_btn = findViewById(R.id.already_has_account_btn);
        already_has_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });



    }
}
