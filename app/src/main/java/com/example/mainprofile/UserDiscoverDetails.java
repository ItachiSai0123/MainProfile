package com.example.mainprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainprofile.dto.TravelDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;

public class UserDiscoverDetails extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    TravelDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_discover_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView = findViewById(R.id.tvDetail);
        imageView = findViewById(R.id.imgDetail);

        Intent i = getIntent();
        dto = (TravelDto) i.getSerializableExtra("dto");
        textView.setText(constructPage(dto));

        Bitmap bitmap = null;
        try {
            URL u = new URL(dto.getUri());
            bitmap = BitmapFactory.decodeStream(u.openConnection().getInputStream());
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
            case R.id.action_bookmark:

                FirebaseDatabase database =
                        FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                DatabaseReference usersRef = ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("bookmark")
                        .child(dto.getKey());
                usersRef.setValue(dto.getState());
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();


                return true;

            case R.id.action_add_to_trip:

                // TODO enable this to intent add plan class
                Intent i = new Intent(this, UserMyplanAdd.class);
                i.putExtra("dto",dto);
                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    private String constructPage(TravelDto dto){

        StringBuilder sb = new StringBuilder();
        sb.append(dto.getName()).append("\n\n");
        sb.append(dto.getType()).append("\n\n");
        sb.append("Contact  : ").append("\n\n").append(dto.getContact()).append("\n\n");
        sb.append("Address  : ").append("\n\n").append(dto.getAddress()).append("\n\n");
        sb.append("Fee      : ").append("\n\n").append(dto.getFees()).append("\n\n");
        sb.append("Operation: ").append("\n\n").append(dto.getOperation()).append("\n\n");
        sb.append("Descripction     : ").append("\n\n").append(dto.getDesc());

        return sb.toString();
    }


}