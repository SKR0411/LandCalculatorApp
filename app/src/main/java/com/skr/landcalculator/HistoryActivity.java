package com.skr.landcalculator;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("History");
		setContentView(R.layout.activity_history);

		textView = findViewById(R.id.textView);

		LandDbHelper dbHelper = new LandDbHelper(this);
		ArrayList<LandModel> arrHistory = dbHelper.fetchHistory();

		if (arrHistory.size() > 0) {
			
			for (int i = 0; i < arrHistory.size(); i++) {

				textView.append("Time: " + arrHistory.get(i).currentDateAndTime + "\n" + "Lenght: " 
						+ arrHistory.get(i).length1 + " " + arrHistory.get(i).lengthUnit1 + " "
						+ arrHistory.get(i).length2 + " " + arrHistory.get(i).lengthUnit2 + "\n" + "Breadth: "
						+ arrHistory.get(i).breadth1 + " " + arrHistory.get(i).breadthUnit1 + " "
						+ arrHistory.get(i).breadth2 + " " + arrHistory.get(i).breadthUnit2 + "\n" + "Multipluer: "
						+ arrHistory.get(i).multiplier + " times" + "\n" + "Price: " + arrHistory.get(i).price + "/"
						+ arrHistory.get(i).areaUnitForPrice + "\n" + "Area: " + arrHistory.get(i).areaAndUnit + "\n"
						+ "Amount: " + arrHistory.get(i).priceAndUnit + "\n" + "\n");
			
			}
		
		} else {
			textView.setText("No History.");
		}

		((Button) findViewById(R.id.btnCHistory)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.delete("LandHistory", null, null);
				textView.setText("No History.");
				Toast.makeText(HistoryActivity.this, "History Cleared", Toast.LENGTH_LONG).show();
			}
		});
	}
}