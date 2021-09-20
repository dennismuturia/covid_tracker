package com.dennis.authentication_app;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.SignInButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        googleBtnUi();
    }

    private void googleBtnUi() {
        // TODO Auto-generated method stub
        /*

        SignInButton googleButton = findViewById(R.id.google_button);
        googleButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        });

         */
    }
}