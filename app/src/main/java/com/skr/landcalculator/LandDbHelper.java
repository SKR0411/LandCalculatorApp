package com.skr.landcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;

public class LandDbHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "LandDb";
	private static final int DATABASE_ID = 1;
	
	private static final String TABLE_NAME = "LandHistory";
	
	private static final String KEY_ID = "id";
	private static final String KEY_LENGTH1 = "length1";
	private static final String KEY_LENGTH_UNIT1 = "lengthUnit1";
	private static final String KEY_LENGTH2 = "length2";
	private static final String KEY_LENGTH_UNIT2 = "lengthUnit2";
	private static final String KEY_BREADTH1 = "breadth1";
	private static final String KEY_BREADTH_UNIT1 = "breadthUnit1";
	private static final String KEY_BREADTH2 = "breadth2";
	private static final String KEY_BREADTH_UNIT2 = "breadthUnit2";
	private static final String KEY_MULTIPLIER = "multiplier";
	private static final String KEY_PRICE = "price";
	private static final String KEY_AREA_UNIT_FOR_PRICE = "areaUnitForPrice";
	private static final String KEY_AREA_AND_UNIT = "areaAndUnit";
	private static final String KEY_PRICE_AND_UNIT = "priceAndUnit";
	private static final String KEY_CURRENT_DATE_AND_TIME = "currentDateAndTime";
	
	public LandDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_ID);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + "("
		+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		+ KEY_LENGTH1 + " TEXT,"
		+ KEY_LENGTH_UNIT1 + " TEXT,"
		+ KEY_LENGTH2 +  " TEXT,"
		+ KEY_LENGTH_UNIT2 +  " TEXT,"
		+ KEY_BREADTH1 + " TEXT,"
		+ KEY_BREADTH_UNIT1 + " TEXT,"
		+ KEY_BREADTH2 + " TEXT,"
		+ KEY_BREADTH_UNIT2 + " TEXT,"
		+ KEY_MULTIPLIER + " TEXT,"
		+ KEY_PRICE +  " TEXT,"
		+ KEY_AREA_UNIT_FOR_PRICE + " TEXT,"
		+ KEY_AREA_AND_UNIT +  " TEXT,"
		+ KEY_PRICE_AND_UNIT + " TEXT,"
		+ KEY_CURRENT_DATE_AND_TIME + " TEXT" + ")");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void addLandData(
	double length1, String lengthUnit1,
	double length2, String lengthUnit2,
	double breadth1, String breadthUnit1,
	double breadth2, String breadthUnit2,
	double multiplier,
	double price, String areaUnitForPrice,
	String areaAndUnit,
	String priceAndUnit,
	String currentDateAndTime)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_LENGTH1, length1);
		values.put(KEY_LENGTH_UNIT1, lengthUnit1);
		values.put(KEY_LENGTH2, length2);
		values.put(KEY_LENGTH_UNIT2, lengthUnit2);
		values.put(KEY_BREADTH1, breadth1);
		values.put(KEY_BREADTH_UNIT1, breadthUnit1);
		values.put(KEY_BREADTH2, breadth2);
		values.put(KEY_BREADTH_UNIT2, breadthUnit2);
		values.put(KEY_MULTIPLIER, multiplier);
		values.put(KEY_PRICE, price);
		values.put(KEY_AREA_UNIT_FOR_PRICE, areaUnitForPrice);
		values.put(KEY_AREA_AND_UNIT, areaAndUnit);
		values.put(KEY_PRICE_AND_UNIT, priceAndUnit);
		values.put(KEY_CURRENT_DATE_AND_TIME, currentDateAndTime);
		
		db.insert(TABLE_NAME, null, values);
	}
	
	public ArrayList<LandModel> fetchHistory() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		
		ArrayList<LandModel> arrHistory = new ArrayList<>();
		
		while (cursor.moveToNext()) {
			
			LandModel model = new LandModel();
			
			model.id = cursor.getInt(0);
			model.length1 = cursor.getDouble(1);
			model.lengthUnit1 = cursor.getString(2);
			model.length2 = cursor.getDouble(3);
			model.lengthUnit2 = cursor.getString(4);
			model.breadth1 = cursor.getDouble(5);
			model.breadthUnit1 = cursor.getString(6);
			model.breadth2 = cursor.getDouble(7);
			model.breadthUnit2 = cursor.getString(8);
			model.multiplier = cursor.getDouble(9);
			model.price = cursor.getDouble(10);
			model.areaUnitForPrice = cursor.getString(11);
			model.areaAndUnit = cursor.getString(12);
			model.priceAndUnit = cursor.getString(13);
			model.currentDateAndTime = cursor.getString(14);
			
			arrHistory.add(model);
		}
		return arrHistory;
	}
}