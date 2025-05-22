package com.example.layarkita;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AkunActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvNama, tvEmail;

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAMA = "user_name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        btnBack = findViewById(R.id.btnBack);
        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);

        // Ambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String nama = sharedPreferences.getString(KEY_NAMA, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (nama != null && email != null) {
            tvNama.setText(nama);
            tvEmail.setText(email);
        } else {
            tvNama.setText("Belum ada data nama");
            tvEmail.setText("Belum ada data email");
            Toast.makeText(this, "Data akun belum tersedia. Silakan registrasi dulu.", Toast.LENGTH_LONG).show();
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
