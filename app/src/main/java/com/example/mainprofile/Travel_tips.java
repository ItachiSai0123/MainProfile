package com.example.mainprofile;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Travel_tips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_tips);

        Button myButton1 = findViewById(R.id.btn1);
        Button myButton2 = findViewById(R.id.btn2);
        Button myButton3 = findViewById(R.id.btn3);
        Button myButton4 = findViewById(R.id.btn4);
        ImageButton backBtn = findViewById(R.id.back_button);

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Travel_tips.this, Culture.class);
                startActivity(intent);
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Travel_tips.this, Local_Language.class);
                startActivity(intent);


            }
        });

        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Travel_tips.this, bckpacking_guide.class);
                startActivity(intent);

            }
        });

        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Travel_tips.this,states_regions.class);
                startActivity(intent);

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Travel_tips.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}