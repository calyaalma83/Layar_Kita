package com.example.layarkita;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottom_nav);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        setupViewPager();

        // Handle Bottom Nav
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.nav_bookmark) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.nav_profile) {
                viewPager.setCurrentItem(2);
                return true;
            }
            return false;
        });

        // Sinkronisasi perubahan halaman dengan Bottom Nav
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNav.setSelectedItemId(R.id.nav_home);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.nav_bookmark);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.nav_profile);
                        break;
                }
            }
        });

        // Handle klik pada Navigation Drawer (Sidebar)
        navigationView.setNavigationItemSelectedListener(item -> {
            handleDrawerSelection(item);
            drawerLayout.closeDrawer(GravityCompat.END); // Tutup drawer setelah klik
            return true;
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new WatchListFragment());
        adapter.addFragment(new ProfileFragment());
        viewPager.setAdapter(adapter);
    }

    private void handleDrawerSelection(@NonNull MenuItem item) {
        String message = "";
        int id = item.getItemId();

        // Genre
        if (id == R.id.nav_horror) {
            message = "Genre: Horror";
        } else if (id == R.id.nav_romantis) {
            message = "Genre: Romantis";
        } else if (id == R.id.nav_kisah_hidup) {
            message = "Genre: Kisah Hidup";
        } else if (id == R.id.nav_dokumenter) {
            message = "Genre: Dokumenter";
        } else if (id == R.id.nav_detektif) {
            message = "Genre: Detektif";
        } else if (id == R.id.nav_kriminal) {
            message = "Genre: Kriminal";
        } else if (id == R.id.nav_fantasi) {
            message = "Genre: Fantasi";

            // Wilayah
        } else if (id == R.id.nav_asia) {
            message = "Wilayah: Asia";
        } else if (id == R.id.nav_amerika) {
            message = "Wilayah: Amerika";
        } else if (id == R.id.nav_malaysia) {
            message = "Wilayah: Malaysia";
        } else if (id == R.id.nav_korea) {
            message = "Wilayah: Korea";
        } else if (id == R.id.nav_jepang) {
            message = "Wilayah: Jepang";
        } else if (id == R.id.nav_thailand) {
            message = "Wilayah: Thailand";
        }

        if (!message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = newBase.getSharedPreferences("settings", Context.MODE_PRIVATE);
        String lang = preferences.getString("language", "id"); // default bahasa Indonesia
        LocalHelper.setLocale(newBase, lang);
        super.attachBaseContext(newBase);
    }
}