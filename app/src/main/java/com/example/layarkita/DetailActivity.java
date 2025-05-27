package com.example.layarkita;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetailActivity extends AppCompatActivity {

    ImageView imagePoster, buttonPlay, buttonFullscreen;
    TextView textTitle, textDesc, textTrailer;
    Button btnKembali;
    FrameLayout playerContainer;
    YouTubePlayerView youtubePlayerView;
    boolean isFullscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);

        imagePoster = findViewById(R.id.imagePoster);
        textTitle = findViewById(R.id.textTitle);
        textDesc = findViewById(R.id.textDesc);
        textTrailer = findViewById(R.id.textTrailer);
        btnKembali = findViewById(R.id.btnKembali);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonFullscreen = findViewById(R.id.buttonFullscreen);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);
        playerContainer = findViewById(R.id.playerContainer);

        getLifecycle().addObserver(youtubePlayerView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int imageRes = intent.getIntExtra("image", R.drawable.default_poster);
        String desc = intent.getStringExtra("desc");
        String trailerUrl = intent.getStringExtra("trailer");

        textTitle.setText(title);
        imagePoster.setImageResource(imageRes);
        textDesc.setText(desc);

        youtubePlayerView.setVisibility(View.GONE);
        buttonFullscreen.setVisibility(View.GONE);

        buttonPlay.setOnClickListener(v -> {
            if (trailerUrl != null && !trailerUrl.trim().isEmpty()) {
                String videoId = extractYoutubeVideoId(trailerUrl);

                imagePoster.setVisibility(View.GONE);
                buttonPlay.setVisibility(View.GONE);
                youtubePlayerView.setVisibility(View.VISIBLE);
                buttonFullscreen.setVisibility(View.VISIBLE);
                buttonFullscreen.bringToFront();

                youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            } else {
                Toast.makeText(this, getString(R.string.trailer2), Toast.LENGTH_SHORT).show();
            }
        });

        buttonFullscreen.setOnClickListener(v -> {
            toggleFullscreenView(!isFullscreen);
        });

        textTrailer.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.tomb_play), Toast.LENGTH_SHORT).show();
        });

        btnKembali.setOnClickListener(v -> finish());
    }

    private void toggleFullscreenView(boolean fullscreen) {
        if (fullscreen) {
            youtubePlayerView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            if (getSupportActionBar() != null) getSupportActionBar().hide();

            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );

            btnKembali.setVisibility(View.GONE);
            textTitle.setVisibility(View.GONE);
            textDesc.setText(View.GONE);
            textTrailer.setVisibility(View.GONE);
        } else {
            youtubePlayerView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dpToPx(200)
            ));

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            if (getSupportActionBar() != null) getSupportActionBar().show();

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

            btnKembali.setVisibility(View.VISIBLE);
            textTitle.setVisibility(View.VISIBLE);
            textDesc.setVisibility(View.VISIBLE);
            textTrailer.setVisibility(View.VISIBLE);
        }
        isFullscreen = fullscreen;
    }

    private String extractYoutubeVideoId(String url) {
        String videoId = "";
        if (url.contains("v=")) {
            videoId = url.substring(url.indexOf("v=") + 2);
            int ampIndex = videoId.indexOf("&");
            if (ampIndex != -1) {
                videoId = videoId.substring(0, ampIndex);
            }
        } else if (url.contains("youtu.be/")) {
            videoId = url.substring(url.lastIndexOf("/") + 1);
            int queryIndex = videoId.indexOf("?");
            if (queryIndex != -1) {
                videoId = videoId.substring(0, queryIndex);
            }
        }
        return videoId;
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
