package com.example.layarkita;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.widget.Toast;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPw;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPw = findViewById(R.id.editPw);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPw.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, getString(R.string.login), Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(LoginActivity.this);
                UserDao userDao = db.userDao();

                User user = userDao.login(email, password);

                runOnUiThread(() -> {
                    if (user != null){
                        Toast.makeText(LoginActivity.this, getString(R.string.login_berhasil), Toast.LENGTH_SHORT).show();

                        SharedPreferences preferences = getSharedPreferences("login_pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_name", user.getNama());
                        editor.putString("email", email);
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("login_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
    }
}