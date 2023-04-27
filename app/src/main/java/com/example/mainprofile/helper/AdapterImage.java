package com.example.mainprofile.helper;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainprofile.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * The type Adapter image.
 */
public class AdapterImage extends RecyclerView.Adapter<AdapterImage.ImageViewHold> {

    /**
     * The Image location.
     */
    ArrayList<ImageCardHelper> imageLocation;

    final private ListItemClickListener OnCardClickListener;

    /**
     * Instantiates a new Adapter image.
     *
     * @param imageLocation the image location
     * @param listener      the listener
     */
    public AdapterImage(ArrayList<ImageCardHelper> imageLocation, ListItemClickListener listener) {
        this.imageLocation = imageLocation;
        OnCardClickListener = listener;
    }


    @Override
    public ImageViewHold onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card, parent, false);

        return new ImageViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageViewHold holder, int position) {
        ImageCardHelper imageCardHelper = imageLocation.get(position);
        holder.image.setImageBitmap(imageCardHelper.getBitmap());
        holder.textView.setText(imageCardHelper.getTravelDto().getKey());
    }

    @Override
    public int getItemCount() {
        return imageLocation.size();
    }

    /**
     * The interface List item click listener.
     */
    public interface ListItemClickListener {
        /**
         * On card list click.
         *
         * @param index the index
         */
        void onCardListClick(int index);
    }

    /**
     * The type Image view hold.
     */
    public class ImageViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView image;
        private final TextView textView;

        /**
         * Instantiates a new Image view hold.
         *
         * @param itemView the item view
         */
        public ImageViewHold(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_card);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);

        }

        /**
         * card view click
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            OnCardClickListener.onCardListClick(clickedPosition);
        }
    }

}


