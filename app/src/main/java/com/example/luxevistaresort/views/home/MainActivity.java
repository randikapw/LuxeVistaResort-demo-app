package com.example.luxevistaresort.views.home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.luxevistaresort.OffersFragment;
import com.example.luxevistaresort.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.luxevistaresort.views.notification.NotificationFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        
        // Load default fragment only if this is the first creation
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();
        }
        
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();
            
            if (itemId == R.id.home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.profile) {
                fragment = new ProfileFragment();
            } else if (itemId == R.id.notification) {
                fragment = new NotificationFragment();
            }
            
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
                return true;
            }
            return false;
        });

        // Set Home as selected item
        bottomNavigationView.setSelectedItemId(R.id.home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}