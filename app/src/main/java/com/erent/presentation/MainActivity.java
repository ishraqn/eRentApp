package com.erent.presentation;

import android.os.Bundle;
import com.erent.R;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.WindowCompat;

import android.view.Window;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import java.util.Objects;

//import com.erent.databinding.ActivityScrollingBinding;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;
    private HomepageFragment homeFrag = new HomepageFragment();
    private RentalsFragment rentalsFrag = new RentalsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.homepage);

        /* Set the homepage as our starting fragment */
        navBar = findViewById(R.id.nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFrag).commit();

        /* Override the default function to set the proper fragment when the navigation bar is selected. */
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem i) {
                boolean swapped = true;

                switch(i.getItemId())
                {
                    default:
                        swapped = false;
                    break;
                    case R.id.layout_homepage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFrag).commit();
                    break;
                    case R.id.layout_login:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, rentalsFrag).commit();
                    break;
                }
                return swapped;
            }
        });
    }
}