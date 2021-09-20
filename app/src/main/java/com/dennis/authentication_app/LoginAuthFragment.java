package com.dennis.authentication_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginAuthFragment extends Fragment {
    private TextInputEditText email, password;
    private Button loginButton, registerButton;
    private SignInButton signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login_auth, container, false);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
        loginButton = v.findViewById(R.id.login_button);
        signInButton = v.findViewById(R.id.google_button);
        registerButton = v.findViewById(R.id.register);

        signInButton.setOnClickListener(view -> {
            Intent googleLogin = new Intent(getContext(), GoogleAuthActivity.class);
            startActivity(googleLogin);
        });

        loginButton.setOnClickListener(view -> {
            String emailText = Objects.requireNonNull(email.getText()).toString();
            String passwordText = Objects.requireNonNull(password.getText()).toString();

        });

        registerButton.setOnClickListener(view -> {
            Fragment regFragment = new SignUpFragment();
            String backStateName = regFragment.getClass().getName();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.authframe, regFragment);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
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