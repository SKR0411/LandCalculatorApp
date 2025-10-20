package com.skr.landcalculator;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtLength1, edtLength2, edtBreadth1, edtBreadth2, edtOperator, edtPrice;
    Spinner spinnerLengthUnit1, spinnerLengthUnit2, spinnerBreadthUnit1, spinnerBreadthUnit2, spinnerAreaUnit, spinnerAreaUnitForPrice, spinnerOperator;
    Double length1, length2, breadth1, breadth2, operator, price;
	String lengthUnit1, lengthUnit2, breadthUnit1, breadthUnit2, areaUnit, areaUnitForPrice, multiplier;
	Button btnCalculate, btnClear, btnHistory;
    TextView tvResult;
	String resultArea, resultAmount;
	Bundle resultData;

    String[] arrLengthUnits = {"Meter", "Centimeter", "Foot", "Inch", "Nol", "Haat"};
	String[] arrAreaUnits = {"Square Meter", "Square Centimeter", "Square Foot", "Square Inch", "Hectare", "Acre", "Bigha", "Kear", "Josti", "Raak", "Fon", "Kear_Josti_Raak_Fon", "Kata" };
	String[] arrOperators = {"Times"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setTitle("Area Calculator");
        setContentView(R.layout.activity_main);

        edtLength1 = findViewById(R.id.edtLength1);
		edtLength2 = findViewById(R.id.edtLength2);
        edtBreadth1 = findViewById(R.id.edtBreadth1);
		edtBreadth2 = findViewById(R.id.edtBreadth2);
		edtOperator = findViewById(R.id.edtOperator);
		edtPrice = findViewById(R.id.edtPrice);
		
        spinnerLengthUnit1 = findViewById(R.id.spinnerLengthUnit1);
		spinnerLengthUnit2 = findViewById(R.id.spinnerLengthUnit2);
        spinnerBreadthUnit1 = findViewById(R.id.spinnerBreadthUnit1);
		spinnerBreadthUnit2 = findViewById(R.id.spinnerBreadthUnit2);
		spinnerAreaUnit = findViewById(R.id.spinnerAreaUnit);
		spinnerAreaUnitForPrice = findViewById(R.id.spinnerAreaUnitForPrice);
		spinnerOperator = findViewById(R.id.spinnerOperator);
		
        btnCalculate = findViewById(R.id.btnCalculateAmount);
		btnClear = findViewById(R.id.btnClear);
		btnHistory = findViewById(R.id.btnHistory);
		
        tvResult = findViewById(R.id.tvResult);

		resultData = new Bundle();
		
		//Spinner Setup
        ArrayAdapter<String> arrLengthUnitsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrLengthUnits);
        ArrayAdapter<String> arrAreaUnitsAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, arrAreaUnits);
		ArrayAdapter<String> arrOperatorsAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, arrOperators);
	
		spinnerLengthUnit1.setAdapter(arrLengthUnitsAdapter);
		spinnerLengthUnit2.setAdapter(arrLengthUnitsAdapter);
        spinnerBreadthUnit1.setAdapter(arrLengthUnitsAdapter);
		spinnerBreadthUnit2.setAdapter(arrLengthUnitsAdapter);
		spinnerAreaUnit.setAdapter(arrAreaUnitsAdapter);
		spinnerAreaUnitForPrice.setAdapter(arrAreaUnitsAdapter);
		spinnerOperator.setAdapter(arrOperatorsAdapter);
		
		//Spinner Value Setup
		if (true) {
			SharedPreferences preferences = getSharedPreferences("SpinnerStatus", MODE_PRIVATE);
			
			spinnerLengthUnit1.setSelection(arrLengthUnitsAdapter.getPosition(preferences.getString("lengthUnit1", "Meter")));
			spinnerLengthUnit2.setSelection(arrLengthUnitsAdapter.getPosition(preferences.getString("lengthUnit2", "Centimeter")));
			spinnerBreadthUnit1.setSelection(arrLengthUnitsAdapter.getPosition(preferences.getString("breadthUnit1", "Meter")));
			spinnerBreadthUnit2.setSelection(arrLengthUnitsAdapter.getPosition(preferences.getString("breadthUnit2", "Centimeter")));
			//spinnerMultiplier.setSelection(arrMultiplier.getPosition(preferences.getString("Multiplier", "Times")));
			spinnerAreaUnitForPrice.setSelection(arrAreaUnitsAdapter.getPosition(preferences.getString("areaUnitForPrice", "Square Meter")));
			spinnerAreaUnit.setSelection(arrAreaUnitsAdapter.getPosition(preferences.getString("areaUnit", "Square Meter")));
		}
		
		//btnCalculateAmount.setOnClickListener( v -> calculateArea());
        
		btnCalculate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				calculateArea();
			}
		});
		
		tvResult.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent iResult = new Intent(MainActivity.this, ResultActivity.class);
				iResult.putExtras(resultData);
				
				if (resultData.getString("areaUnitForPrice") != null) {
					startActivity(iResult);
				} else {
					tvResult.setText("⚠ Please enter parameters and press calculate button.");
					Toast.makeText(MainActivity.this, "⚠ Please enter parameters and press calculate button.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btnClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clearAll();
				resultData.clear();
				Toast.makeText(MainActivity.this, "Cleared.", Toast.LENGTH_SHORT).show();
			}
		});
		
		btnHistory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, HistoryActivity.class));
			}
		});
	}

    public void calculateArea() {
        try {
            length1 = Double.parseDouble(edtLength1.getText().toString());
			length2 = Double.parseDouble(edtLength2.getText().toString());
            breadth1 = Double.parseDouble(edtBreadth1.getText().toString());
			breadth2 = Double.parseDouble(edtBreadth2.getText().toString());
			operator = Double.parseDouble(edtOperator.getText().toString());
			price = Double.parseDouble(edtPrice.getText().toString());
		
            lengthUnit1 = spinnerLengthUnit1.getSelectedItem().toString();
			lengthUnit2 = spinnerLengthUnit2.getSelectedItem().toString();
            breadthUnit1 = spinnerBreadthUnit1.getSelectedItem().toString();
			breadthUnit2 = spinnerBreadthUnit2.getSelectedItem().toString();
			areaUnit = spinnerAreaUnit.getSelectedItem().toString();
			areaUnitForPrice = spinnerAreaUnitForPrice.getSelectedItem().toString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currentDateAndTime = sdf.format(new Date());
			
			SharedPreferences preferences = getSharedPreferences("SpinnerStatus", MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
		
            double lengthInMeters1 = convertToMeter(length1, lengthUnit1);
			double lengthInMeters2 = convertToMeter(length2, lengthUnit2);
            double breadthInMeters1 = convertToMeter(breadth1, breadthUnit1);
			double breadthInMeters2 = convertToMeter(breadth2, breadthUnit2);
	
            double lengthInMeters = lengthInMeters1 + lengthInMeters2;
			double breadthInMeters = breadthInMeters1 + breadthInMeters2;

			double areaSqMeter = lengthInMeters * breadthInMeters;
			
			resultArea = resultArea(areaSqMeter, areaUnit);
			resultAmount = resultAmount(areaSqMeter, operator, price, areaUnitForPrice);
			
			//Shared Preference
			editor.putString("lengthUnit1", lengthUnit1);
			editor.putString("lengthUnit2", lengthUnit2);
			editor.putString("breadthUnit1", lengthUnit1);
			editor.putString("breadthUnit2", lengthUnit2);
			//editor.putString("multiplier", multiplier);
			editor.putString("areaUnitForPrice", areaUnitForPrice);
			editor.putString("areaUnit", areaUnit);
			
			editor.apply();
		
			//Bundle Passing
			resultData.putDouble("length1", length1);
			resultData.putDouble("length2", length2);
			resultData.putDouble("breadth1", breadth1);
			resultData.putDouble("breadth2", breadth2);
			resultData.putDouble("multiplier", operator);
			resultData.putDouble("price", price);			
			resultData.putString("lengthUnit1", lengthUnit1);
			resultData.putString("lengthUnit2", lengthUnit2);
			resultData.putString("breadthUnit1", breadthUnit1);
			resultData.putString("breadthUnit2", breadthUnit2);
			resultData.putString("result", resultArea);
			resultData.putString("resultAmount", resultAmount);
			resultData.putString("areaUnitForPrice", areaUnitForPrice);
			
			//SQLite
			LandDbHelper dbHelper = new LandDbHelper(MainActivity.this);
			dbHelper.addLandData(length1, lengthUnit1, length2, lengthUnit2, breadth1, breadthUnit1, breadth2, breadthUnit2, operator, price, areaUnitForPrice, resultArea, resultAmount, currentDateAndTime);
			
			//Set Data to tvResult
			tvResult.setText("Total Area: \n" + resultArea);
			tvResult.append("\n\nTotal Amount: \n" + resultAmount);
		
			Toast.makeText(MainActivity.this, "Calculated", Toast.LENGTH_SHORT).show();
		
        } catch (Exception e) {
            tvResult.setText("⚠ Please enter valid numbers for length and breadth.");
    		Toast.makeText(MainActivity.this, "⚠ Please enter valid numbers for length and breadth.", Toast.LENGTH_SHORT).show();
	    }
	}

    private double convertToMeter(double value, String lengthUnit) {
        switch (lengthUnit) {
            case "Meter": return value;
            case "Centimeter": return value * 0.01;
            case "Foot": return value * 0.3048;
            case "Inch": return value * 0.0254;
			case "Nol": return value * 3.6576;
            case "Haat": return value * 0.4572;
            default: return value;
        }
    }
	
	private String resultArea(double value, String areaUnit) {
		switch (areaUnit) {
			case "Square Meter": return String.format("%.2f m²", value);
			case "Square Centimeter": return String.format("%.1f cm²", value * 10000);
			case "Square Foot": return String.format("%.2f ft²", value * 10.7639);
			case "Square Inch": return String.format("%.1f In²", value * 1550.0031);
			case "Hectare": return String.format("%.5f Hectare", value / 10000);
			case "Acre": return String.format("%.5f Acre", value / 4046.86);
			case "Bigha": return String.format("%.5f Bigha", value / 1600);
			case "Kear": return String.format("%.4f Kear", value / (0.4572 * 0.4572 * 8 * 8 * 4 * 28));
			case "Josti": return String.format("%.3f Josti", value / (0.4572 * 0.4572 * 8 * 8 * 4));
			case "Raak": return String.format("%.3f Raak", value / (0.4572 * 0.4572 * 8 * 8));
			case "Fon": return String.format("%.2f Fon", value / (0.4572 * 0.4572));
			case "Kear_Josti_Raak_Fon" : if (value<100000*100000) {
				return String.format(
				"%d Kear," + " %d Josti," + " %d Raak," + " %.2f Fon",
				(int)(value / (0.4572 * 0.4572 * 8 * 8 * 4 * 28)),
				(int)(((value / (0.4572 * 0.4572 * 8 * 8 * 4 * 28)) - (int)(value / (0.4572 * 0.4572 * 8 * 8 * 4 * 28))) * 28),
				(int)(((value / (0.4572 * 0.4572 * 8 * 8 * 4)) - (int)(value / (0.4572 * 0.4572 * 8 * 8 * 4))) * 4),
				(((value / (0.4572 * 0.4572 * 8 * 8)) - (int)(value / (0.4572 * 0.4572 * 8 * 8))) * 8 * 8));
				} else {
					return "Area is too big for this unit.";
				}
			
			default: return "Select valid unit for area.";
		}
	}
	
	private String resultAmount(double areaSm, double operator, double price, String areaUnit) {
		switch (areaUnit) {
			case "Square Meter": return String.format("%.2f Rupees" + " (%.2f m²)", operator * areaSm * price, operator * areaSm);
			case "Square Centimeter": return String.format("%.2f Rupees" + " (%.1f cm²)", operator * price * areaSm * 10000, operator * areaSm * 10000);
			case "Square Foot": return String.format("%.2f Rupees" + " (%.2f ft²)", operator * price * areaSm * 10.7639, operator * areaSm * 10.7639);
			case "Square Inch": return String.format("%.2f Rupees" + " (%.1f In²)", operator * price * areaSm * 1550.0031, operator * areaSm * 1550.0031);
			case "Hectare": return String.format("%.2f Rupees" + " (%.5f Hectare)", operator * price * areaSm / 10000, operator * areaSm / 10000);
			case "Acre": return String.format("%.2f Rupees" + " (%.5f Acre)", operator * price * areaSm / 4046.86, operator * areaSm / 4046.86);
			case "Bigha": return String.format("%.2f Rupees" + " (%.5f Bigha)", operator * price * areaSm / 1600, operator * areaSm / 1600);
			case "Kear": return String.format("%.2f Rupees" + " (%.4f Kear)", operator * price * areaSm/ (0.4572 * 0.4572 * 8 * 8 * 4 * 28), operator * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4 * 28));
			case "Josti": return String.format("%.2f Rupees" + " (%.3f Josti)", operator * price * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4), operator * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4));
			case "Raak": return String.format("%.2f Rupees" + " (%.3f Raak)", operator * price * areaSm / (0.4572 * 0.4572 * 8 * 8), operator * areaSm / (0.4572 * 0.4572 * 8 * 8));
			case "Fon": return String.format("%.2f Rupees" + " (%.2f Fon)", operator * price * areaSm / (0.4572 * 0.4572), operator * areaSm / (0.4572 * 0.4572));
			default: return "Select valid unit for price.";
		}
	}
	
	private void clearAll() {
		tvResult.setText("");
		edtLength1.setText("");
		edtLength2.setText("");
		edtBreadth1.setText("");
		edtBreadth2.setText("");
		edtOperator.setText("");
		edtPrice.setText("");
	}
}