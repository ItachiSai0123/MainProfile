package com.example.mainprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class states_regions extends AppCompatActivity {

    ImageButton bckBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.states_regions);

        setupEndActivityDemo();




    }
    private void setupEndActivityDemo(){
        ImageButton detailBackSR = (ImageButton) findViewById(R.id.backBtnSR);
        detailBackSR.setOnClickListener(new View.OnClickListener() {
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