package com.example.mainprofile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserMyplanAdapter extends RecyclerView.Adapter<UserMyplanAdapter.ViewHolder> {

    LinkedList<UserMyplanClass> planList;
    List<UserMyplanClass> searchList;
    Context context;

    public UserMyplanAdapter(LinkedList<UserMyplanClass> planList, List<UserMyplanClass> searchList, Context context) {
        this.planList = planList;
        this.searchList = searchList;
        this.context = context;
    }

    public void setSearchList(List<UserMyplanClass> searchList) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserMyplanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserMyplanAdapter.ViewHolder holder, int position) {

        UserMyplanClass model = planList.get(position);

        // Set the values of the views in the ViewHolder to the data from the Trip object
        holder.titleTextView.setText(model.getTripTitle());
        holder.dateTextView.setText(model.getTripFromDate() + " - " + model.getTripToDate());
        holder.descriptionTextView.setText(model.getTripDesc());
        Glide.with(context)
                .load(model.getTripImageUrl())
                .into(holder.imageView);

        // Set an OnClickListener on the itemView to handle clicks
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another activity on click
                Intent intent = new Intent(context, UserMyplanDetails.class);
                intent.putExtra("tripId", model.getTripId());
                context.startActivity(intent);
            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another activity on click
                FirebaseDatabase bk = FirebaseDatabase.getInstance();
                DatabaseReference ref = bk.getReference()
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("MyPlan");
                String tripId = ref.push().getKey(); // Retrieve the auto-generated key
                Intent intent = new Intent(context, UserMyplanDetails.class);
                intent.putExtra("tripId", tripId); // Pass the auto-generated key as the tripId
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return planList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView dateTextView;
        public TextView descriptionTextView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.text_view_title);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }
}
