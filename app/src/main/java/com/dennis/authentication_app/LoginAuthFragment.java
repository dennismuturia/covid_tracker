package com.dennis.authentication_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginAuthFragment extends Fragment {
    private TextInputEditText email, password;
    private Button loginButton, registerButton;
    private SignInButton signInButton;
    private FirebaseAuth auth;
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
            new loginClass(emailText, passwordText);
            updateUI(new CreateUserFactory(emailText, passwordText, auth).createUser());
            IntentClass it = new IntentClass();
            it.createIntent(getContext(), MainActivity.class);
            Toast.makeText(getContext(), "SignIn successful", Toast.LENGTH_SHORT).show();
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
    private void updateUI(FirebaseUser user) {
        new User(user);
    }

    /*Any Transaction that calls an api to be conducted here*/
    class loginClass extends AsyncTask<Void, Void, String>{

        String email;
        String password;
        loginClass(String email, String password){
            this.email = email;
            this.password = password;
        }

        @Override
        protected  void onPreExecute(){

        }

        @Override
        protected String doInBackground(Void... voids) {
            //do stuff
            return null;
        }

        protected void onPostExecute(){

        }
    }
}