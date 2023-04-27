package com.example.mainprofile;


import android.graphics.Bitmap;

public class UserProfile {
    private String userEmail;
    private String userName;
    private String userBio;
    private String profilePicture;

    public UserProfile(){
    }

    public UserProfile(String userEmail, String userName, String userBio, String profilePicture){
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBio = userBio;
        this.profilePicture = profilePicture;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String email) {
        userEmail = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String bio) {
        userBio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
