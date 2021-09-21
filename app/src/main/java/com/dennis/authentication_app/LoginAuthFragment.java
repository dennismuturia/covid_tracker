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
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;


public class LoginAuthFragment extends Fragment {
    private static final String TAG = "Login";

    private TextInputEditText email, password;
    private Button loginButton, registerButton;
    private SignInButton signInButton;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mAuth = FirebaseAuth.getInstance();
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
            new loginClass(emailText, passwordText).execute();
            //updateUI(new CreateUserFactory(emailText, passwordText, auth).createUser());

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

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) requireContext(), (OnCompleteListener<AuthResult>) task ->{
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(getContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });

    }

    private void updateUI(FirebaseUser user) {
        new User(user);
    }


    /*Any Transaction that calls an api to be conducted here*/
    class loginClass extends AsyncTask<Void, Void, String>{

        String email;
        String password;
        FirebaseUser user;
        loginClass(String email, String password){
            this.email = email;
            this.password = password;
        }

        @Override
        protected  void onPreExecute(){

        }

        @Override
        protected String doInBackground(Void... voids) {
            signIn(email, password);
            return "";
        }

        protected void onPostExecute(){
            if(user != null){
                IntentClass it = new IntentClass();
                it.createIntent(getContext(), MainActivity.class);
                Toast.makeText(getContext(), "SignIn successful", Toast.LENGTH_SHORT).show();
            }
        }




    }
}