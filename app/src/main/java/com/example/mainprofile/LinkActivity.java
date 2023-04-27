package com.example.mainprofile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LinkActivity extends AppCompatActivity{
    Button booking, agoda, airbnb, grab, rapidkl, moovit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        booking = findViewById(R.id.bookingbtn);
        agoda = findViewById(R.id.agodabtn);
        grab = findViewById(R.id.grabbtn);
        rapidkl = findViewById(R.id.rapidklbtn);
        moovit = findViewById(R.id.moovitbtn);
        airbnb = findViewById(R.id.airbnbbtn);

        ImageButton backBtn = findViewById(R.id.back_button);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.booking.com/index.en-gb.html?aid=373420&label=country-my-pgjOIuQiP5NOfxkF52QwMgS388510664155%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg%3Afi%3Atikwd-172220002%3Alp1029512%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9Ydj4ck6nIj5YRjPru0SpaEk&sid=e31b206a8c8d5b9f12b35a1e8df37d78&click_from_logo=1");
            }
        });
        agoda.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 gotoUrl("https://www.agoda.com/country/malaysia.html?site_id=1891464&tag=44e4664c-8af2-1e9a-ef57-3db4bf5cedcf&device=c&network=g&adid=577548742774&rand=1420346767726186373&expid=&adpos=&aud=kwd-42486924214&gclid=CjwKCAjw0ZiiBhBKEiwA4PT9z12bKp6rrgCYxsWmuI0KaX75evfTk4mqKJ1KbEY01YFQRvkWCTHPRRoC-GIQAvD_BwE&pslc=1");
             }
        });
        airbnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://play.google.com/store/apps/details?id=com.airbnb.android&hl=en&gl=US");
            }
        });
        grab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://play.google.com/store/apps/details?id=com.grabtaxi.passenger&referrer=af_tranid%3Dwl7tI1eeNNSOJD1ODmCdtw%26pid%3DMY-Website-ADR-Install_Button%26c%3DWebsite_Download%26af_web_id%3De29470e2-554c-4a7f-a4c5-de6bc7ead73f-p%26utm_source%3DMY-Website-ADR-Install_Button%26utm_campaign%3DWebsite_Download&pli=1");
            }
        });
        rapidkl.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 gotoUrl("https://myrapid.com.my/");
                    }
        });

        moovit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://moovitapp.com/");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LinkActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    void gotoUrl(String s) {
        try {
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No Website Linked", Toast.LENGTH_SHORT).show();
        }
    }
}

