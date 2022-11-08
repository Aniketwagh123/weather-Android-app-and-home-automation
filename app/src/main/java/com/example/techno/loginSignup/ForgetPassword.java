package com.example.techno.loginSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.techno.Dashboard;
import com.example.techno.R;

public class ForgetPassword extends AppCompatActivity {
    AppCompatButton return_ligin_forgot_pass_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        return_ligin_forgot_pass_btn=findViewById(R.id.return_ligin_forgot_pass_btn);
        return_ligin_forgot_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        
    }
}