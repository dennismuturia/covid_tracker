package com.dennis.authentication_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {
    private Button signUpButton;
    private EditText email, password, cpassword;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        signInButton = v.findViewById(R.id.google_button);
        signUpButton = v.findViewById(R.id.sign_up_button);
        email = v.findViewById(R.id.email_sign_up);
        password = v.findViewById(R.id.password_sign_up);
        cpassword = v.findViewById(R.id.confirm_password_sign_up);

        signUpButton.setOnClickListener(view -> {
            Intent googleLogin = new Intent(getContext(), GoogleAuthActivity.class);
            startActivity(googleLogin);
        });

        signInButton.setOnClickListener(view -> {
            /*TO-DO
            * Change the edittext color if the confirm password is wrong with the password*/
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String cpasswordText = cpassword.getText().toString();

            if(passwordText.equals(cpasswordText)){
                 updateUI(new CreateUserFactory(emailText, passwordText, mAuth).createUser());
            }else Toast.makeText(getContext(), "The Passwords do not match", Toast.LENGTH_SHORT).show();


        });

        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
           Intent intent = new Intent(getContext(), MainActivity.class);
           startActivity(intent);
        }
    }

    private void updateUI(FirebaseUser user) {
        new User(user);
    }
}