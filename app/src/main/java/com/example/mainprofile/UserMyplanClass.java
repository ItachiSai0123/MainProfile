package com.example.mainprofile;

public class UserMyplanClass {

    String tripId;
    String tripTitle;
    String tripFromDate;
    String tripToDate;
    String tripDesc;
    String tripImageUrl;
    String tripStatus;

    public UserMyplanClass() {
    }

    public UserMyplanClass(String tripId, String tripTitle, String tripFromDate, String tripToDate, String tripDesc, String tripImageUrl, String tripStatus) {
        this.tripId = tripId;
        this.tripTitle = tripTitle;
        this.tripFromDate = tripFromDate;
        this.tripToDate = tripToDate;
        this.tripDesc = tripDesc;
        this.tripImageUrl = tripImageUrl;
        this.tripStatus = tripStatus;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getTripFromDate() {
        return tripFromDate;
    }

    public void setTripFromDate(String tripFromDate) {
        this.tripFromDate = tripFromDate;
    }

    public String getTripToDate() {
        return tripToDate;
    }

    public void setTripToDate(String tripToDate) {
        this.tripToDate = tripToDate;
    }

    public String getTripDesc() {
        return tripDesc;
    }

    public void setTripDesc(String tripDesc) {
        this.tripDesc = tripDesc;
    }

    public String getTripImageUrl() {
        return tripImageUrl;
    }

    public void setTripImageUrl(String tripImage) {
        this.tripImageUrl = tripImage;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
