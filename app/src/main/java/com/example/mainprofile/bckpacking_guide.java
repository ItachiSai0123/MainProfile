package com.example.mainprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class bckpacking_guide extends AppCompatActivity {

    ImageButton bckBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backpacking_guide);

        setupEndActivityDemo();




    }
    private void setupEndActivityDemo(){
        ImageButton detailBackBG = (ImageButton) findViewById(R.id.backBtnBG);
        detailBackBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public static Intent updateIntent(Context context){
        return new Intent(context,Travel_tips.class );

    }



    public static Intent makeIntent(Context context){
        return new Intent(context,Travel_tips.class );

    }

}