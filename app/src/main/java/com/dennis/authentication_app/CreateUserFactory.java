package com.dennis.authentication_app;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class CreateUserFactory implements CreateUser{
    private static final String TAG = "EmailPassword";
    private String email;
    private String password;
    private FirebaseAuth auth;
    public CreateUserFactory(String email, String password, FirebaseAuth mAuth){
        this.email = email;
        this.password = password;
        this.auth = mAuth;
    }
    @Override
    public FirebaseUser createUser() {
        final FirebaseUser[] res = new FirebaseUser[1];
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();
                        res[0] = user;

                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                    }
                });
        return res[0];
    }

    @Override
    public void emailVerification() {

    }

    @Override
    public FirebaseUser signIn() {
        final FirebaseUser[] res = new FirebaseUser[1];
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "signInWithEmail:success");
                        res[0] = auth.getCurrentUser();
                    }
                });
        return res[0];
    }
}
