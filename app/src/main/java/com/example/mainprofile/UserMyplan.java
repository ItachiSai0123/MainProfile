package com.example.mainprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserMyplan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_myplan);

        //bar
        getSupportActionBar().setTitle("My Plans");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6C83F8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //add plan
        ImageButton addplan = findViewById(R.id.btnaddplan);
        addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(UserMyplan.this, "Add new plan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserMyplan.this, UserMyplanAdd.class);
                startActivity(intent);

            }
        });

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserMyplanAdapter mAdapter = new UserMyplanAdapter(new LinkedList<UserMyplanClass>(), null, this);
        mRecyclerView.setAdapter(mAdapter);

        FirebaseDatabase bk = FirebaseDatabase.getInstance();
        DatabaseReference eventsRef = bk.getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("MyPlan");

        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                LinkedList<UserMyplanClass> eventList = new LinkedList<>();

                for (DataSnapshot eventSnapshot : snapshot.getChildren()) {
                    UserMyplanClass event = eventSnapshot.getValue(UserMyplanClass.class);
                    eventList.addFirst(event);
                }
                mAdapter.planList = eventList;
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error

                Toast.makeText(UserMyplan.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });

    } // sec column

}