package com.example.techno.loginSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.techno.Account;
import com.example.techno.Dashboard;
import com.example.techno.R;
import com.example.techno.model.Users;
import com.example.techno.retrofit.UserApi;
import com.example.techno.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputLayout username,password;
    AppCompatButton login,signUp,forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.checkName);
                password = findViewById(R.id.checkPassword);
                String userNameVal = username.getEditText().getText().toString();
                String passwordVal = password.getEditText().getText().toString();
                RetrofitService retrofitService = new RetrofitService();
                UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

                userApi.findByUsername(userNameVal)
                        .enqueue(new Callback<Users>() {
                            @Override
                            public void onResponse(Call<Users> call, Response<Users> response) {
                                Log.v("mass",response.body().toString());
                                Log.v("mass",passwordVal);
                                Log.v("mass",response.body().getPassword());
                                if(passwordVal.equals(response.body().getPassword())){
                                    Users user1 = response.body();
                                    user1.setIs_login(true);
                                    userApi.save(user1)
                                            .enqueue(new Callback<Users>() {
                                                @Override
                                                public void onResponse(Call<Users> call, Response<Users> response) {

                                                }

                                                @Override
                                                public void onFailure(Call<Users> call, Throwable t) {

                                                }
                                            });
                                    SharedPreferences srd = getSharedPreferences("demo",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = srd.edit();
                                    Log.v("mass", String.valueOf(srd.getBoolean("isLogin",false)));
                                    editor.putBoolean("isLogin",true);
                                    editor.putString("username",response.body().getUserName());
                                    editor.apply();

                                    Log.v("mass", String.valueOf(srd.getBoolean("isLogin",false)));

                                    Toast toast = Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT);
                                    toast.show();

                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                    finish();
                                }
                                else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Users> call, Throwable t) {

                            }
                        });

            }
        });

        signUp = findViewById(R.id.SignUp_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                finish();
            }
        });



        forget_password = findViewById(R.id.forgot_password);
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
                finish();
            }
        });



    }
}