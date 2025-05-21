package com.example.layarkita;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);

        SharedPreferences preferences = getSharedPreferences("login_pref", MODE_PRIVATE);
        boolean isRegistered = preferences.getBoolean("isRegistered", false);

        if (!isRegistered){
            startActivity(new Intent(this, RegisterActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}
