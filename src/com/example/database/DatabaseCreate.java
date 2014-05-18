package com.example.database;

import com.example.Fragments.MainActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseCreate extends SQLiteOpenHelper {

	// Database name and Database version
	private static final String DATABASE_NAME = "Epc";
	private static final int DATABASE_VERSION = 1;
	
	// All Table name
	static final String TABLE_DEVISION ="devisions";		
	static final String TABLE_DISTRICT="districts";	
	static final String TABLE_THANA="thanas";
	static final String TABLE_POLICE ="police";
	static final String TABLE_HOSPITAL="hospital";
	static final String TABLE_RAB="rab";
	static final String TABLE_FIRE_SERVICE="fire_service";
	static final String TABLE_AMBULENCE="ambulence";
	static final String TABLE_OTHERS="others";
	
	// All Column name
	static final String ID="id";
	public static final String DEVISION_NAME="devision_name";
	public static final String DISTRICT_NAME="district_name";
	static final String THANA_NAME="thana_name";
	static final String HOSPITAL_NAME="hospital_name";
	static final String RAB_SECTION ="rab_section";
	static final String FIRE_SERVICE_OFICE_NAME="fire_service_ofice_name";
	static final String AMBULANCE_SERVICE_NAEM="ambulance_service_name";
	static final String MOBILE_NO="mobile_no";
	static final String TELEPHONE_NO="telephone_no";
	static final String ADDRESS="address";
	static final String EMAIL="email";
	static final String WEBSITE="website";
	
	static final String TBC="create table devisions " +
			"(id integer primary key, " +
			"devision_name text not null unique)";
	
	static final String TBCDIS ="create table districts " +
			"(id integer primary key, " +
			"district_name text not null unique, " +
			"devision_name text not null)";
	
	static final String GET_DISTRICT_NAME = "select district_name from districts where districts.devision_name like '%Dhaka%' ;";
	
	
	// select district_name from districts where districts.devision_name=Dhaka ;
	
	// Table (devision) create statement 
	static final String TABLE_DEVISION_CREATE = "create table " +TABLE_DEVISION+
			"(" +ID+ " integer primary key, "
				+DEVISION_NAME+ " text not null unique "+
			");" ;
	
	// Table (district) create statement 
	static final String TABLE_DISTRICT_CREATE = "create table " +TABLE_DISTRICT+
			"(" +ID+ " integer primary key, "
				+DISTRICT_NAME+ " text not null unique, "
				+DEVISION_NAME+ " text not null " +
				//	+ "foreign key ("+DEVISION_NAME+") references "+TABLE_DEVISION+" ("+DEVISION_NAME+")" +
			");" ;
			
	// Table (thana ) create statement 
	static final String TABLE_THANA_CREATE = "create table " +TABLE_THANA+
			"(" +ID+ " integer primary key, "
			 	+THANA_NAME+ " text not null unique, "
			 	+MOBILE_NO+ " text not null unique, "
			 	+TELEPHONE_NO+ " text not null unique, "
			 	+ADDRESS+ " text not null, " 
			 	+DISTRICT_NAME+ " text not null  "+
			 	//+"foreign key ("+DISTRICT_NAME+") references "+TABLE_DEVISION+" ("+DISTRICT_NAME+" )" +
			
			");" ;
	
	// Table (hospital ) create statement
	static final String TABLE_HOSPITAL_CREATE ="create table " +TABLE_HOSPITAL+
			"(" 	+ID+" integer primary key, " 
					+HOSPITAL_NAME+ " text not null, " 
					+DISTRICT_NAME+ " text not null, " 
					+THANA_NAME+" text not null, " 
					+MOBILE_NO+" text not null unique, " 
					+TELEPHONE_NO+" text not null unique, " 
					+ADDRESS+" text not null "+
			");";	
	
	// Table (rab) create statement 
	static final String TABEL_RAB_CREATE = " create table " +TABLE_RAB+
			"(" +ID+ " integer primary key, "
				+RAB_SECTION+ " text not null," 
				+DISTRICT_NAME+ " text not null,"
				+THANA_NAME+ " text not null,"
				+MOBILE_NO+ " text not null unique,"
				+TELEPHONE_NO+ "text not null nuique,"
				+ADDRESS+ " text not null" +
			");";
	
	// Table (fire service ) create statement
	static final String TABEL_FIRE_SERVICE_CREATE = " create table " +TABLE_FIRE_SERVICE+
			"(" +ID+ " integer primary key, "
				+FIRE_SERVICE_OFICE_NAME+ " text not null," 
				+DISTRICT_NAME+ " text not null,"
				+THANA_NAME+ " text not null,"
				+MOBILE_NO+ " text not null unique,"
				+TELEPHONE_NO+ "text not null nuique,"
				+ADDRESS+ " text not null" +
			");";
	
	// Table (ambulance) create statement
	static final String TABEL_AMBULANCE_CREATE = " create table " +TABLE_AMBULENCE+
			"(" +ID+ " integer primary key, "
				+AMBULANCE_SERVICE_NAEM+ " text not null," 
				+HOSPITAL_NAME+ " text not null, "
				+DISTRICT_NAME+ " text not null,"
				+THANA_NAME+ " text not null,"
				+MOBILE_NO+ " text not null unique,"
				+TELEPHONE_NO+ "text not null nuique,"
				+ADDRESS+ " text not null" +
			");";
	

	
	
	
	
	
	// Constructor 
	public DatabaseCreate(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase Epc) {
		System.out.println("onCreate");
		
		Epc.execSQL(TABLE_DEVISION_CREATE);
		Epc.execSQL(TABLE_DISTRICT_CREATE);
		Epc.execSQL(TABLE_THANA_CREATE);

		
		System.out.println("create table devision and district and thana");
		//new MainActivity().dataInsertDevision();
		//new MainActivity().dataInsertDistict ();
		//new MainActivity().dataInsertThana ();
		//Epc.execSQL(TBCDIS);

		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase Epc, int oldVersion, int newVersion) {
		
		Log.w(DatabaseCreate.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
		Epc.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVISION);
		Epc.execSQL("DROP TABLE IF EXISTS "+TABLE_DISTRICT);
		Epc.execSQL("DROP TABLE IF EXISTS "+TABLE_THANA);
	    onCreate(Epc);
		// TODO Auto-generated method stub
		
	}
	
	
	

}
