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
    private ImageView imgProfile;
    private TextView tvAkun, tvNama, tvEmail;

    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        setContentView(R.layout.fragment_profile);

        btnBack = findViewById(R.id.btnBack);
        imgProfile = findViewById(R.id.imgProfile);
        tvAkun = findViewById(R.id.tvAkun);
        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Set nama & email default saat activity dibuka
        tvNama.setText("-");
        tvEmail.setText("-");

        btnBack.setOnClickListener(v -> finish());

        tvAkun.setOnClickListener(v -> {
            String nama = sharedPreferences.getString(KEY_NAMA, "Belum ada data nama");
            String email = sharedPreferences.getString(KEY_EMAIL, "Belum ada data email");

            tvNama.setText(nama);
            tvEmail.setText(email);

            Toast.makeText(this, "Informasi akun ditampilkan", Toast.LENGTH_SHORT).show();
        });
    }
}