package com.example.layarkita;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 4000; // 4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Animasi fade-in logo
        ImageView logo = findViewById(R.id.splash_logo);
        Animation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(3000);
        logo.startAnimation(fadeIn);



        // Delay 4 detik, lalu cek isRegistered dan arahkan ke activity yang sesuai
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences("login_pref", MODE_PRIVATE);
            boolean isRegistered = preferences.getBoolean("isRegistered", false);

            // Membuat intent berdasarkan status isRegistered
            Intent intent;
            if (!isRegistered) {
                intent = new Intent(SplashActivity.this, RegisterActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            // Tambahkan flag agar tidak kembali ke SplashActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            // Tutup SplashActivity agar tidak masuk ke recent apps
            finish();
        }, SPLASH_DELAY);
    }
}