package com.dennis.authentication_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUpFragment extends Fragment {
    private static final String TAG = "Sign up";

    private Button signUpButton;
    private EditText email, password, cpassword;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signInButton = v.findViewById(R.id.google_button);
        signUpButton = v.findViewById(R.id.sign_up_button);
        email = v.findViewById(R.id.email_sign_up);
        password = v.findViewById(R.id.password_sign_up);
        cpassword = v.findViewById(R.id.confirm_password_sign_up);

        signInButton.setOnClickListener(view -> {
            Intent googleLogin = new Intent(getContext(), GoogleAuthActivity.class);
            startActivity(googleLogin);
        });

        signUpButton.setOnClickListener(view -> {
            /*TO-DO
            * Change the edittext color if the confirm password is wrong with the password
            */
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String cpasswordText = cpassword.getText().toString();

            if(passwordText.equals(cpasswordText)){
                createAccount(emailText, passwordText);
                 //updateUI(new CreateUserFactory(emailText, passwordText, mAuth).createUser());
                 Intent intent = new Intent(getContext(), MainActivity.class);
                 startActivity(intent);
            }else Toast.makeText(getContext(), "The Passwords do not match", Toast.LENGTH_SHORT).show();
        });
        return v;
    }
    private void updateUI(FirebaseUser user) {
        new User(user);
    }
    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) requireContext(), (OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        Toast.makeText(getContext(), "Registration successful.",
                                Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getContext(), "Registration failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
        // [END create_user_with_email]
    }

}