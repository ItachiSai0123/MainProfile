package com.example.mainprofile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mainprofile.dto.TravelDto;
import com.example.mainprofile.helper.AdapterImage;
import com.example.mainprofile.helper.ImageCardHelper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDiscover extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView listRecycler;
    private RecyclerView.Adapter adap;
    private ArrayList<ImageCardHelper> cardList;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_discover);

        //bar
        getSupportActionBar().setTitle("Discover Place");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6C83F8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // make sure internet permit is on
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Spinner spinner = (Spinner) findViewById(R.id.state_spinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.state_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    private void initRecycler(String val, boolean refresh) {
        Firebase.setAndroidContext(this);
        Firebase databaseTrending =
                new Firebase("https://travelreviewapp-effb0-default-rtdb.asia-southeast1.firebasedatabase.app/state/" + val);


        listRecycler = findViewById(R.id.listRecyclerDiscover);
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (refresh) {
            cardList = new ArrayList<>();
        }

        // get the user enrolled event and add to card to display
        databaseTrending.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                String name = snapshot.getKey();
                System.out.println(snapshot);
                TravelDto dto = snapshot.getValue(TravelDto.class);
                dto.setState(val);
                dto.setKey(name);
                cardList.add(new ImageCardHelper(dto));
                adap = new AdapterImage(cardList, index -> {
                    Intent intent = new Intent(getApplicationContext(), UserDiscoverDetails.class);
                    intent.putExtra("dto", cardList.get(index).getTravelDto());
                    startActivity(intent);
                });
                listRecycler.setAdapter(adap);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        System.out.println(adapter.getItem(pos));
        if (pos > 0) {
            initRecycler((String) adapter.getItem(pos), true);
        } else {
            Random random = new Random();
            List<Integer> list = new ArrayList<>();
            cardList = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int randomInt = random.nextInt(adapter.getCount());
                if (!list.contains(randomInt)) {
                    list.add(randomInt);
                    initRecycler((String) adapter.getItem(randomInt), false);
                }
            }


        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bookmark, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
            case R.id.action_bookmark:
                System.out.println("CLICKED");
                listRecycler = findViewById(R.id.listRecyclerDiscover);
                listRecycler.setHasFixedSize(true);
                listRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                cardList = new ArrayList<>();

                FirebaseDatabase bk = FirebaseDatabase.getInstance();
                DatabaseReference ref = bk.getReference()
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("bookmark");

                ref.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull com.google.firebase.database.DataSnapshot snapshot, @Nullable String previousChildName) {
                        String key = snapshot.getKey();
                        String state = snapshot.getValue(String.class);

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference()
                                .child("state")
                                .child(state)
                                .child(key);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                                TravelDto dto = snapshot.getValue(TravelDto.class);
                                dto.setState(state);
                                dto.setKey(key);
                                cardList.add(new ImageCardHelper(dto));
                                adap = new AdapterImage(cardList, index -> {
                                    Intent intent = new Intent(getApplicationContext(), UserDiscoverDetails.class);
                                    intent.putExtra("dto", cardList.get(index).getTravelDto());
                                    startActivity(intent);
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        listRecycler.setAdapter(adap);
                    }

                    @Override
                    public void onChildChanged(@NonNull com.google.firebase.database.DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull com.google.firebase.database.DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull com.google.firebase.database.DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}