package com.example.mainprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserMyplanDetails extends AppCompatActivity {

    private String tripId;
    private EditText titleEditText;
    private EditText fromDateEditText;
    private EditText toDateEditText;
    private EditText descEditText;
    private ImageView imageView;
    private Button updateButton;
    private Button deleteButton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_myplan_details);

        //bar
        getSupportActionBar().setTitle("My Plans Details");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6C83F8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the tripId from the Intent extras
        Intent intent = getIntent();
        tripId = intent.getStringExtra("tripId");

        // Find the views in the layout
        titleEditText = findViewById(R.id.titleEditText);
        fromDateEditText = findViewById(R.id.fromDateEditText);
        toDateEditText = findViewById(R.id.toDateEditText);
        descEditText = findViewById(R.id.descEditText);
        imageView = findViewById(R.id.imageView);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);


        // Load the trip details from Firebase
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("MyPlan")
                .child(tripId);

        tripRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get the trip data and set the values of the views in the layout
                UserMyplanClass trip = snapshot.getValue(UserMyplanClass.class);
                titleEditText.setText(trip.getTripTitle());
                fromDateEditText.setText(trip.getTripFromDate());
                toDateEditText.setText(trip.getTripToDate());
                descEditText.setText(trip.getTripDesc());
                Glide.with(UserMyplanDetails.this)
                        .load(trip.getTripImageUrl())
                        .into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(UserMyplanDetails.this, "Error loading trip details", Toast.LENGTH_SHORT).show();
            }
        });

        // Set an OnClickListener on the updateButton to update the trip details
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated values from the EditText views
                String newTitle = titleEditText.getText().toString();
                String newFromDate = fromDateEditText.getText().toString();
                String newToDate = toDateEditText.getText().toString();
                String newDesc = descEditText.getText().toString();


                // Update the trip details in Firebase
                tripRef.child("tripTitle").setValue(newTitle);
                tripRef.child("tripFromDate").setValue(newFromDate);
                tripRef.child("tripToDate").setValue(newToDate);
                tripRef.child("tripDesc").setValue(newDesc);

                // Show a success message
                Toast.makeText(UserMyplanDetails.this, "MyPlan details updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserMyplanDetails.this, UserMyplan.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener on the deleteButton to delete the trip
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the trip from Firebase
                tripRef.removeValue();

                // Show a success message and finish the activity
                Toast.makeText(UserMyplanDetails.this, "MyPlan deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserMyplanDetails.this, UserMyplan.class);
                startActivity(intent);
            }
        });
    }
}