package com.example.mainprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout homeView, myplanView, discoverView, accountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bar
        getSupportActionBar().setTitle("Main Menu");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6C83F8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        myplanView = findViewById(R.id.relativelayout2);
        discoverView = findViewById(R.id.relativelayout1);
        accountView = findViewById(R.id.relativelayout3);

        myplanView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "My plan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, UserMyplan.class));

            }
        });

        discoverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Discover place", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, UserDiscover.class));

            }
        });

        accountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "My account", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }
}