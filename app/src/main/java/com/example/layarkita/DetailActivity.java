package com.example.layarkita;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imagePoster;
    TextView textTitle, textDesc, textTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imagePoster = findViewById(R.id.imagePoster);
        textTitle = findViewById(R.id.textTitle);
        textDesc = findViewById(R.id.textDesc);
        textTrailer = findViewById(R.id.textTrailer);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int imageRes = intent.getIntExtra("image", R.drawable.default_poster);
        String desc = intent.getStringExtra("desc");
        String trailerUrl = intent.getStringExtra("trailer");

        textTitle.setText(title);
        imagePoster.setImageResource(imageRes);
        textDesc.setText(desc);

        textTrailer.setOnClickListener(v -> {
            if (trailerUrl != null && !trailerUrl.trim().isEmpty()) {
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl));
                startActivity(youtubeIntent);
            } else {
                Toast.makeText(this, "Trailer belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
