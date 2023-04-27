package com.example.mainprofile.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mainprofile.dto.TravelDto;

import java.net.URL;

/**
 * The type Image card helper.
 */
public class ImageCardHelper {

    /**
     * image resource
     */
    private TravelDto travelDto;
    private Bitmap bitmap;

    public ImageCardHelper(TravelDto travelDto) {
        this.travelDto = travelDto;
        Bitmap bitmap = null;
        try {
            URL u = new URL(travelDto.getUri());
            bitmap = BitmapFactory.decodeStream(u.openConnection().getInputStream());
            this.bitmap = bitmap;
        } catch (Exception e) {

        }
    }

    public TravelDto getTravelDto() {
        return travelDto;
    }

    public void setTravelDto(TravelDto travelDto) {
        this.travelDto = travelDto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
