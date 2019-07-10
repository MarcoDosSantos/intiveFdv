package com.example.fdvapp.model;

public class User {
    private String first, last, email, username, large, thumbnail;

    public User(String first, String last, String email, String username, String large, String thumbnail) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.username = username;
        this.large = large;
        this.thumbnail = thumbnail;
    }



    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getLarge() {
        return large;
    }

    public String getThumbnail() {
        return thumbnail;
    }


    @Override
    public String toString() {
        return "First Name: " + getFirst() + "\n"+
                "Last Name: " + getLast() + "\n"+
                "Username: " + getUsername() + "\n"+
                "Email: " + getEmail() + "\n\n";
    }
}
