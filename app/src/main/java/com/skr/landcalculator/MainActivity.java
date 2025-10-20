package com.skr.landcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    EditText etLength1, etLength2, etBreadth1, etBreadth2, etTimes, etPrice;
    Spinner spinnerLengthUnit1, spinnerLengthUnit2, spinnerBreadthUnit1, spinnerBreadthUnit2, spinnerAreaUnit, spinnerPricePerUnit, spinnerMultiplier;
    Button btnCalculate, btnCalculateAmount, btnClear;
    TextView tvResult, tvResultAmount;

    String[] units = {"Meter", "Centimeter", "Foot", "Inch", "Nol", "Haat"};
	String[] areaUnits = {"Square Meter", "Square Centimeter", "Square Foot", "Square Inch", "Hectare", "Acre", "Bigha", "Kear", "Josti", "Raak", "Fon", "Kear_Josti_Raak_Fon", "Kata" };
	String[] multiplier = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setTitle("Area Calculator");
        setContentView(R.layout.activity_main);

        etLength1 = findViewById(R.id.etLength1);
		etLength2 = findViewById(R.id.etLength2);
        etBreadth1 = findViewById(R.id.etBreadth1);
		etBreadth2 = findViewById(R.id.etBreadth2);
		etTimes = findViewById(R.id.etTimes);
		etPrice = findViewById(R.id.etPrice);
		
        spinnerLengthUnit1 = findViewById(R.id.spinnerLengthUnit1);
		spinnerLengthUnit2 = findViewById(R.id.spinnerLengthUnit2);
        spinnerBreadthUnit1 = findViewById(R.id.spinnerBreadthUnit1);
		spinnerBreadthUnit2 = findViewById(R.id.spinnerBreadthUnit2);
		spinnerAreaUnit = findViewById(R.id.spinnerAreaUnit);
		spinnerPricePerUnit = findViewById(R.id.spinnerPricePerUnit);
		spinnerMultiplier = findViewById(R.id.spinnerMultiplier);
		
        btnCalculate = findViewById(R.id.btnCalculate);
		btnCalculateAmount = findViewById(R.id.btnCalculateAmount);
		btnClear = findViewById(R.id.btnClear);
		
        tvResult = findViewById(R.id.tvResult);
		tvResultAmount = findViewById(R.id.tvResultAmount);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        spinnerLengthUnit1.setAdapter(adapter);
		spinnerLengthUnit2.setAdapter(adapter);
        spinnerBreadthUnit1.setAdapter(adapter);
		spinnerBreadthUnit2.setAdapter(adapter);
		
		ArrayAdapter<String> areAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, areaUnits);
		spinnerAreaUnit.setAdapter(areAdapter);
		spinnerPricePerUnit.setAdapter(areAdapter);
		
		ArrayAdapter<String> arrMultiplier = new ArrayAdapter<> (this, android.R.layout.simple_spinner_dropdown_item, multiplier);
		spinnerMultiplier.setAdapter(arrMultiplier);
		
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				
                calculateArea();
				
            }
        });
		
		
		btnCalculateAmount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				calculateAmount();
				
			}
		});
		
		btnClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				clearAll();
				
			}
		});
    }

    public double calculateArea() {
        try {
            double length1 = Double.parseDouble(etLength1.getText().toString());
			double length2 = Double.parseDouble(etLength2.getText().toString());
            double breadth1 = Double.parseDouble(etBreadth1.getText().toString());
			double breadth2 = Double.parseDouble(etBreadth2.getText().toString());

            String lengthUnit1 = spinnerLengthUnit1.getSelectedItem().toString();
			String lengthUnit2 = spinnerLengthUnit2.getSelectedItem().toString();
            String breadthUnit1 = spinnerBreadthUnit1.getSelectedItem().toString();
			String breadthUnit2 = spinnerBreadthUnit2.getSelectedItem().toString();
			String AreaUnit = spinnerAreaUnit.getSelectedItem().toString();

            double lengthInMeters1 = convertToMeter(length1, lengthUnit1);
			double lengthInMeters2 = convertToMeter(length2, lengthUnit2);
            double breadthInMeters1 = convertToMeter(breadth1, breadthUnit1);
			double breadthInMeters2 = convertToMeter(breadth2, breadthUnit2);

            double lengthInMeters = lengthInMeters1 + lengthInMeters2;
			double breadthInMeters = breadthInMeters1 + breadthInMeters2;

			double areaSqMeter = lengthInMeters * breadthInMeters;

			String result = resultArea(areaSqMeter, AreaUnit);

            tvResult.setText(result);
			return areaSqMeter;
        } catch (Exception e) {
            tvResult.setText("⚠ Please enter valid numbers for length and breadth.");
        }
		
		double areaSqMeter = calculateArea();
		return areaSqMeter;
    }
	
	private void calculateAmount() {
		
		double times = Double.parseDouble(etTimes.getText().toString());
		double price = Double.parseDouble(etPrice.getText().toString());
		
		String PricePerUnit = spinnerPricePerUnit.getSelectedItem().toString();
		
		double areaSqMeter = calculateArea();
		
		String result = resultAmount(areaSqMeter, times, price, PricePerUnit);
		
		tvResultAmount.setText(result);
	}

    private double convertToMeter(double value, String unit) {
        switch (unit) {
            case "Meter": return value;
            case "Centimeter": return value * 0.01;
            case "Foot": return value * 0.3048;
            case "Inch": return value * 0.0254;
			case "Nol": return value * 3.6576;
            case "Haat": return value * 0.4572;
            default: return value;
        }
    }
	
	private String resultArea(double value, String unit) {
		switch (unit) {
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
	
	private String resultAmount(double areaSm, double times, double price, String unit) {
		switch (unit) {
			case "Square Meter": return String.format("%.2f m²\n" + "%.2f Rupees", times * areaSm, times * areaSm * price);
			case "Square Centimeter": return String.format("%.1f cm²\n" + "%.2f Rupees", times * areaSm * 10000, times * price * areaSm * 10000);
			case "Square Foot": return String.format("%.2f ft²\n" + "%.2f Rupees", times * areaSm * 10.7639, times * price * areaSm * 10.7639);
			case "Square Inch": return String.format("%.1f In²\n" + "%.2f Rupees", times * areaSm * 1550.0031, times * price * areaSm * 1550.0031);
			case "Hectare": return String.format("%.5f Hectare\n" + "%.2f Rupees", times * areaSm / 10000, times * price * areaSm / 10000);
			case "Acre": return String.format("%.5f Acre\n" + "%.2f Rupees", times * areaSm / 4046.86, times * price * areaSm / 4046.86);
			case "Bigha": return String.format("%.5f Bigha\n" + "%.2f Rupees", times * areaSm / 1600, times * price * areaSm / 1600);
			case "Kear": return String.format("%.4f Kear\n" + "%.2f Rupees", times * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4 * 28), times * price * areaSm/ (0.4572 * 0.4572 * 8 * 8 * 4 * 28));
			case "Josti": return String.format("%.3f Josti\n" + "%.2f Rupees", times * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4), times * price * areaSm / (0.4572 * 0.4572 * 8 * 8 * 4));
			case "Raak": return String.format("%.3f Raak\n" + "%.2f Rupees", times * areaSm / (0.4572 * 0.4572 * 8 * 8), times * price * areaSm / (0.4572 * 0.4572 * 8 * 8));
			case "Fon": return String.format("%.2f Fon\n" + "%.2f Rupees", times * areaSm / (0.4572 * 0.4572), times * price * areaSm / (0.4572 * 0.4572));
			default: return "Select valid unit for price.";
			
		}
	}
	
	private void clearAll() {
		tvResult.setText("");
		tvResultAmount.setText("");
		etLength1.setText("0");
		etLength2.setText("0");
		etBreadth1.setText("0");
		etBreadth2.setText("0");
		etTimes.setText("1");
		etPrice.setText("0");
		
	}
}