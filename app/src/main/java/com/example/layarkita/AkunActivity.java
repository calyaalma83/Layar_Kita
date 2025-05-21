package com.example.layarkita;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AkunActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageButton btnBack;
    private Button btnSimpan;
    private ImageView imgProfile;
    private TextView txtChangePhoto;
    private EditText editNama, editEmail;

    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        btnBack = findViewById(R.id.btnBack);
        btnSimpan = findViewById(R.id.btnSimpan);
        imgProfile = findViewById(R.id.imgProfile);
        txtChangePhoto = findViewById(R.id.txtChangePhoto);
        editNama = findViewById(R.id.editNama);
        editEmail = findViewById(R.id.editEmail);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadUserData();

        btnBack.setOnClickListener(v -> finish());

        txtChangePhoto.setOnClickListener(v -> openGallery());

        btnSimpan.setOnClickListener(v -> saveUserData());
    }

    private void loadUserData() {
        String nama = sharedPreferences.getString(KEY_NAMA, "");
        String email = sharedPreferences.getString(KEY_EMAIL, "");

        editNama.setText(nama);
        editEmail.setText(email);
    }

    private void saveUserData() {
        String nama = editNama.getText().toString().trim();
        String email = editEmail.getText().toString().trim();

        if (nama.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Nama dan Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAMA, nama);
        editor.putString(KEY_EMAIL, email);
        editor.apply();

        Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
    }

    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null){
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

}