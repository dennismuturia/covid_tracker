package com.dennis.authentication_app;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

//This class will be used to remove repetitive intent code
public class IntentClass extends AppCompatActivity {
    public void createIntent(Context context, Object obj){
        Intent intent = new Intent(context, obj.getClass());
        startActivity(intent);
    }
}
