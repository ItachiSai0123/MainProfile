package com.example.mainprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Local_Language extends AppCompatActivity {

    ImageButton bckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_language);


        setupEndActivityDemo();


    }
    private void setupEndActivityDemo(){
        ImageButton detailBackLL = (ImageButton) findViewById(R.id.backBtnLL);
        detailBackLL.setOnClickListener(new View.OnClickListener() {
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