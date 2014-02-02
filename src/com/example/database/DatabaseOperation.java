package com.example.database;

import com.example.emergencyphonecall.DistrictsActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperation {
	
	static Context c;
	private SQLiteDatabase db;
	DatabaseCreate databaseCreate;
	
	long insertId;
	
	//static DistrictsActivity dActivity = new DistrictsActivity();
	
	private String D="Dhaka";
	private String DEVISION_NAME;
	
	private String SELECT_DISTRICT_NAME = "select district_name from districts where districts.devision_name like '%"+DEVISION_NAME+"%' ;";
	private String SELECT_THANA_NAME_MOBILE_TELIPHONE = "select thana_name, mobile_no, telephone_no from thanas where thanas.district_name like '%Dhaka%' ;";
	String recivedName;

	
	// Constructor
	 public DatabaseOperation (Context context) {
		 this.c=context;
		 databaseCreate = new DatabaseCreate(c);		 
	 }
	 
	 // default constructor
	 public DatabaseOperation(String DEV) {
		 this.DEVISION_NAME = DEV;
		 
	 }
	 public DatabaseOperation () {
		 
	 }
	 
	//________________________________________________setter and getter
		public String getDEVISION_NAME() {
			return DEVISION_NAME;
		}

		public void setDEVISION_NAME(String dEVISION_NAME) {
			this.DEVISION_NAME = dEVISION_NAME;
		}

	 

	 // Database open method
	 public DatabaseOperation open() throws SQLException {
		 db = databaseCreate.getWritableDatabase();
		 return this;
	 }

	 
	 // Database close method
	 public void close () {
		 databaseCreate.close();
	 }

//_________________________________________________________________________________insert query 	 
	 // data insert into Devision
	 
	 public long insertDevision (String devision_name) {
		 ContentValues cv = new ContentValues();
		 cv.put(DatabaseCreate.DEVISION_NAME, devision_name);
		 
		 return db.insert(DatabaseCreate.TABLE_DEVISION, null, cv);
	 }
	 
	 
	 // data insert into District
	 public long insertDistrict (String district_name, String devision_name){
		 ContentValues cv = new ContentValues();
		 cv.put(DatabaseCreate.DISTRICT_NAME, district_name);
		 cv.put(DatabaseCreate.DEVISION_NAME, devision_name);
		 return db.insert(DatabaseCreate.TABLE_DISTRICT, null, cv);
	 }
	 
	 // data insert into Thana 
	 public long insertThana (String thana_name, String mobile_no, String telephone_no, String address, String district_name ) {
		 ContentValues cv = new ContentValues();
		 cv.put(DatabaseCreate.THANA_NAME, thana_name);
		 cv.put(DatabaseCreate.MOBILE_NO, mobile_no);
		 cv.put(DatabaseCreate.TELEPHONE_NO, telephone_no);
		 cv.put(DatabaseCreate.ADDRESS, address);
		 cv.put(DatabaseCreate.DISTRICT_NAME, district_name);
		 return db.insert(DatabaseCreate.TABLE_THANA, null, cv);
	 }

//_____________________________________________________________________________________ retrieve query	 
	 // Retrieve all devision name 
	 
	 public Cursor getDevisionName () {
		 return db.query(DatabaseCreate.TABLE_DEVISION, new String []{DatabaseCreate.DEVISION_NAME}, null, null, null, null, null);
	 }
	 
	 // Retrieve all district name
	 public Cursor getDistrictName(String devision) {
		 String dd = devision;		 
		 String selectDistrictName = "select district_name from districts where districts.devision_name like '%"+dd+"%' ;";	
		 return db.rawQuery(selectDistrictName, null);
		
	 }
	 
	 // Retrieve all thana name, their mobile number and telephone number according to district
	 public Cursor getThanaInfo(String district) {
		 String districtName= district;
		 String selectThanaName = "select thana_name, mobile_no, telephone_no from thanas where thanas.district_name like '%"+districtName+"%' ;";
		 return db.rawQuery(selectThanaName, null);
	 }
	 
	 
	 
	 
	 
} // END CLASS
