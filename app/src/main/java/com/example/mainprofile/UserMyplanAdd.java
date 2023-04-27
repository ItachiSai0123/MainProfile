package com.example.mainprofile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class UserMyplanAdd extends AppCompatActivity {

    private EditText editTextTripTitle;
    private EditText editTextTripDesc;
    private EditText editTextTripStatus;
    private Button buttonChooseFromDate;
    private Button buttonChooseToDate;
    private Button buttonChooseImage;
    private ImageView imageViewTrip;
    private Calendar fromDate = Calendar.getInstance();
    private Calendar toDate = Calendar.getInstance();
    private static final int REQUEST_CODE_IMAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_plan);

        //bar
        getSupportActionBar().setTitle("Add Plans");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#6C83F8"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextTripTitle = findViewById(R.id.editTextTripTitle);
        editTextTripDesc = findViewById(R.id.editTextTripDesc);
        editTextTripStatus = findViewById(R.id.editTextTripStatus);
        buttonChooseFromDate = findViewById(R.id.buttonChooseFromDate);
        buttonChooseToDate = findViewById(R.id.buttonChooseToDate);
        buttonChooseImage = findViewById(R.id.buttonChooseImage);
        imageViewTrip = findViewById(R.id.imageViewTrip);

       /* buttonChooseFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserMyplanAdd.this, fromDateListener, fromDate
                        .get(Calendar.YEAR), fromDate.get(Calendar.MONTH),
                        fromDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        buttonChooseToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserMyplanAdd.this, toDateListener, toDate
                        .get(Calendar.YEAR), toDate.get(Calendar.MONTH),
                        toDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        buttonChooseFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserMyplanAdd.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Update the EditText with the selected date
                                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                buttonChooseFromDate.setText(date);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        buttonChooseToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserMyplanAdd.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Update the EditText with the selected date
                                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                buttonChooseToDate.setText(date);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        Button buttonAddTrip = findViewById(R.id.buttonAddTrip);
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripTitle = editTextTripTitle.getText().toString();
                String tripDesc = editTextTripDesc.getText().toString();
                String tripStatus = "Upcoming event";
                String tripFromDate = buttonChooseFromDate.getText().toString();
                String tripToDate = buttonChooseToDate.getText().toString();

                // Get a reference to the Firebase Storage location where you want to upload the image
                StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                        .child("MyPlan").child(UUID.randomUUID().toString());

                // Get the image URI from the imageView
                Uri imageUri = imageViewTrip.getTag() != null ? Uri.parse(imageViewTrip.getTag().toString()) : null;

                // If an image was selected, upload it to Firebase Storage
                if (imageUri != null) {
                    // Use a listener to track the upload progress and show a progress dialog if desired
                    UploadTask uploadTask = storageRef.putFile(imageUri);
                    uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // Show a progress dialog if desired
                        }
                    });

                    // Once the upload is complete, get the download URL and save it to the Firebase Realtime Database
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            // Get the download URL from the storage reference and return it
                            return storageRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                // Save the download URL to the Firebase Realtime Database
                                String tripImageUrl = task.getResult().toString();

                                FirebaseDatabase bk = FirebaseDatabase.getInstance();
                                DatabaseReference ref = bk.getReference()
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("MyPlan");

                                String tripId = ref.push().getKey();
                                UserMyplanClass trip = new UserMyplanClass(tripId, tripTitle, tripFromDate, tripToDate, tripDesc, tripImageUrl, tripStatus);

                                ref.child(tripId).setValue(trip).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(UserMyplanAdd.this, "MyPlan added successfully", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    Toast.makeText(UserMyplanAdd.this, "Failed to add MyPlan", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            } else {
                                // Handle errors
                            }
                        }
                    });
                } else {
                    // If no image was selected, save the trip without an image URL

                    FirebaseDatabase bk = FirebaseDatabase.getInstance();
                    DatabaseReference ref = bk.getReference()
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("MyPlan");

                    String tripId = ref.push().getKey();
                    UserMyplanClass trip = new UserMyplanClass(tripId,tripTitle, tripFromDate, tripToDate, tripDesc, "", tripStatus);

                    ref.child(tripId).setValue(trip)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserMyplanAdd.this, "MyPlan added successfully", Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(UserMyplanAdd.this, "Failed to add MyPlan", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageViewTrip.setImageURI(imageUri);
            imageViewTrip.setTag(imageUri.toString());
        }
    }
}