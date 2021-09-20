package com.dennis.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GenAuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_auth);

        getSupportFragmentManager().beginTransaction().replace(R.id.authframe, new LoginAuthFragment()).commit();
    }
}