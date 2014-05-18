package com.example.emergencyphonecall;

import java.util.ArrayList;
import java.util.List;

import com.example.database.DatabaseCreate;
import com.example.database.DatabaseOperation;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class DistrictsActivity extends ListActivity {

	Context context ;
	DatabaseCreate dbcreate = new DatabaseCreate(context);
	List <String> districtInfoList = new ArrayList<String> ();
	private String clickedItemNameDevesion ;
	DatabaseOperation dbOperation = new DatabaseOperation(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_districts);
		
		Intent intent = getIntent();		
		clickedItemNameDevesion = intent.getExtras().getString("Devision:");
		TextView headingDevisionName = (TextView)findViewById(R.id.headingDevision);
		headingDevisionName.setText(""+clickedItemNameDevesion+" Division");
		populateArrayList ();
				
	}
	
	public void onListItemClick (ListView patent, View v, int position, long id) {
		String clickedItemNameDistrict = districtInfoList.get(position);
		Toast.makeText(this, "District:"+clickedItemNameDistrict, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,ThanaActivity.class);
		intent.putExtra("District", clickedItemNameDistrict);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		getMenuInflater().inflate(R.menu.districts, menu);
		return true;
	}

	
	public void populateArrayList () {
		
		List <String> districtInfoList = getAllDistrict();	
		ListView listView = getListView();		
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,districtInfoList);		
		listView.setAdapter(arrayAdapter);
	}
	
	
	public List <String> getAllDistrict() {
		
		dbOperation.open();
		
		DatabaseCreate dbcreate = new DatabaseCreate(context);
		Cursor c= dbOperation.getDistrictName(clickedItemNameDevesion);
		
		c.moveToFirst();
		while (! c.isAfterLast()) {
			String districtInfo= new String(c.getString(0));
			districtInfoList.add(districtInfo);

			c.moveToNext();
		}
		c.close();
		return districtInfoList;
		
	}
	

	
}
