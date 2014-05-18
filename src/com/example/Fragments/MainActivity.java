package com.example.emergencyphonecall;


import java.util.ArrayList;
import java.util.List;

import com.example.UtilClasses.DevisionInfo;
import com.example.database.DatabaseCreate;
import com.example.database.DatabaseOperation;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	
	DatabaseOperation dbOperation = new DatabaseOperation();
	
	String dev ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dataInsertDevision();
		dataInsertDistict ();
		dataInsertThana ();
	//	Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Kokila.ttf");
		
		final Animation roate = AnimationUtils.loadAnimation(this, R.anim.rotate);
		
		Button ambulenceButton = (Button) findViewById(R.id.ambulance);
	//	policeButton.setTypeface(type.SANS_SERIF);
	
	}
	
	public void policeClick(View v) {

		final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
		v.startAnimation(rotate);
		Intent intent = new Intent(this,DevisionsActivity.class);
		startActivity(intent);
	}
	
	public void hospitalClick (View v) {
		Intent i2 = new Intent(this,DevisionsActivity.class);
		
		startActivity(i2);
	}
	
	public void fireServiceClick (View v) {
		Intent intent = new Intent(this,DevisionsActivity.class);
		startActivity(intent);
	}
	
	public void ambulenceClick (View v) {
		final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
		v.startAnimation(rotate);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	

	private void displayContent(Cursor c) {
		
		
		
		dev = c.getString(0);
	
		
	}
//____________________________________________________________________________________________data insert method	
	public void dataInsertDevision() {
		DatabaseOperation dbOperation = new DatabaseOperation(this);
		dbOperation.open();
		dbOperation.insertDevision("Dhaka");
		dbOperation.insertDevision("Rajshahi");
		dbOperation.insertDevision("Rangpur");
		dbOperation.insertDevision("Barisal");
		dbOperation.insertDevision("Chattagong");
		dbOperation.insertDevision("Khulna");
		dbOperation.insertDevision("Shylet");
		//dbOperation.insertDistrict("ManikGong","Dhaka");
		dbOperation.close();
		System.out.println("Devision is loaded");
	}

	public void dataInsertDistict () {
		String Dhaka= "Dhaka";
		DatabaseOperation dbOperation = new DatabaseOperation(this);
		dbOperation.open();
		dbOperation.insertDistrict("Dhaka", Dhaka);
		dbOperation.insertDistrict("Munshiganj ", Dhaka);
		dbOperation.insertDistrict("Faridpur", Dhaka);
		dbOperation.insertDistrict("Gazipur", Dhaka);
		dbOperation.insertDistrict("Gopalganj ", Dhaka);
		dbOperation.insertDistrict("Jamalpur", Dhaka);
		dbOperation.insertDistrict("Kishoreganj", Dhaka);
		dbOperation.insertDistrict("Madaripur ", Dhaka);
		dbOperation.insertDistrict("Manikganj ", Dhaka);
		dbOperation.insertDistrict("Mymensingh", Dhaka);
		dbOperation.insertDistrict("Narayanganj ", Dhaka);
		dbOperation.insertDistrict("Narsingdi ", Dhaka);
		dbOperation.insertDistrict("Netrokona", Dhaka);
		dbOperation.insertDistrict("Rajbari", Dhaka);
		dbOperation.insertDistrict("Shariatpur", Dhaka);
		dbOperation.insertDistrict("Sherpur", Dhaka);
		dbOperation.insertDistrict("Tangail ", Dhaka);
		dbOperation.close();
		System.out.println("District is loaded");
	}
	
	public void dataInsertThana (){
		String Dhaka= "Dhaka";
		DatabaseOperation dbOpertaion = new DatabaseOperation(this);
		dbOpertaion.open();
		dbOpertaion.insertThana("Badda", "01713373173", "01191001151", "Badda", Dhaka);
		dbOpertaion.insertThana("Mohammadpur", "01713373171", "01191001152", "Badda", Dhaka);
		dbOpertaion.insertThana("Romona", "01713373172", "01191001153", "Badda", Dhaka);
		dbOpertaion.insertThana("Gulshan", "01713373174", "01191001154", "Badda", Dhaka);
		dbOpertaion.insertThana("Airport", "01713373175", "01191001155", "Badda", Dhaka);
		dbOpertaion.insertThana("Kafrul", "01713373176", "01191001156", "Badda", Dhaka);
		dbOpertaion.insertThana("Mirpur", "01713373177", "01191001157", "Badda", Dhaka);
		dbOpertaion.insertThana("Uttara", "01713373178", "01191001158", "Badda", Dhaka);
		dbOpertaion.insertThana("Pollobi", "01713373179", "01191001159", "Badda", Dhaka);
		dbOpertaion.insertThana("Motizhil", "01713373110", "01191001110", "Badda", Dhaka);
		dbOpertaion.insertThana("Dhanmondi", "01713373111", "01191001111", "Badda", Dhaka);
		dbOpertaion.insertThana("paltan", "01713373112", "01191001112", "Badda", Dhaka);
		dbOpertaion.insertThana("Rampura", "01713373113", "01191001113", "Badda", Dhaka);
		dbOpertaion.insertThana("Symolli", "01713373114", "01191001114", "Badda", Dhaka);
		dbOpertaion.close();
		System.out.println("Thana is loaded");
	}
	
}
