package com.skr.landcalculator;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
	
	TextView tvResult;
	Bundle bundle;
	Double length1, length2, breadth1, breadth2, multiplier, price;
	String lengthUnit1, lengthUnit2, breadthUnit1, breadthUnit2, areaUnitForPrice, result, resultAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Result");
		setContentView(R.layout.activity_result);
		
		tvResult = findViewById(R.id.tvResult);
		bundle = getIntent().getExtras();
			
		length1 = bundle.getDouble("length1");
		length2 = bundle.getDouble("length2");
		breadth1 = bundle.getDouble("breadth1");
		breadth2 = bundle.getDouble("breadth2");
		multiplier = bundle.getDouble("multiplier");
		price = bundle.getDouble("price");
		
		lengthUnit1 = bundle.getString("lengthUnit1");
		lengthUnit2 = bundle.getString("lengthUnit2");
		breadthUnit1 = bundle.getString("breadthUnit1");
		breadthUnit2 = bundle.getString("breadthUnit2");
		areaUnitForPrice = bundle.getString("areaUnitForPrice");
		result = bundle.getString("result");
		resultAmount = bundle.getString("resultAmount");
		
		tvResult.setText("Lenght: " + length1 + " " + lengthUnit1 + " " + length2 + " " + lengthUnit2 
						+ "\n" + "Breadth: " + breadth1 + " " + breadthUnit1 + " " + breadth2 + " " + breadthUnit2 + "\n" 
						+ "Multipluer: " + multiplier + " times" + "\n" 
						+ "Price: " + price + "/" + areaUnitForPrice + "\n" 
						+ "Area: " + result + "\n"
						+ "Amount: " + resultAmount + "\n" + "\n");
	
	}
}