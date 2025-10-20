package com.skr.landcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
	
	Intent iHome;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setTitle("Area Calculator");
        setContentView(R.layout.activity_welcome);
		
		iHome = new Intent(WelcomeActivity.this, MainActivity.class);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(iHome);
				finish();
			}
		}, 1000);
	}
}