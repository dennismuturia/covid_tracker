package com.dennis.authentication_app;

import com.google.firebase.auth.FirebaseUser;

public class User {
    private FirebaseUser user;

    public User(FirebaseUser user){
        this.user = user;
    }

    public FirebaseUser getUser(){
        return user;
    }
}
