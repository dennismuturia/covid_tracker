package com.dennis.authentication_app;

import com.google.firebase.auth.FirebaseUser;

public interface CreateUser {
    FirebaseUser createUser();
    void emailVerification();
}
