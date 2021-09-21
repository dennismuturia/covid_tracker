package com.dennis.authentication_app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private LinearLayout loadingLayout;

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
        loadingLayout = v.findViewById(R.id.lotielayer);

        loadingLayout.setVisibility(View.GONE);
        signInButton.setOnClickListener(view -> {
            Intent googleLogin = new Intent(getContext(), GoogleAuthActivity.class);
            startActivity(googleLogin);
        });

        signUpButton.setOnClickListener(view -> {
            /*TO-DO
            * Change the edittext color if the confirm password is wrong with the password
            */
            loadingLayout.setVisibility(View.VISIBLE);
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String cpasswordText = cpassword.getText().toString();

            if(passwordText.equals(cpasswordText)){
                createAccount(emailText, passwordText);
            }else Toast.makeText(getContext(), "The Passwords do not match", Toast.LENGTH_SHORT).show();

            FirebaseUser user = mAuth.getCurrentUser();
            if(user != null){
                Fragment loginFragment = new LoginAuthFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.authframe, loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        loadingLayout.setVisibility(View.GONE);
        return v;
    }
    private void updateUI(FirebaseUser user) {
        new User(user);
    }
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) requireContext(), (OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        Toast.makeText(getContext(), "Registration successful.",
                                Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getContext(), "Registration failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }



}