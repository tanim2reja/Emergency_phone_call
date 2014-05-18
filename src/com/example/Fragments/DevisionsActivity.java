package com.example.emergencyphonecall;

import java.util.ArrayList;
import java.util.List;

//import com.example.UtilClasses.String;
import com.example.database.DatabaseOperation;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DevisionsActivity extends ListActivity {
	
	DatabaseOperation dbOperation = new DatabaseOperation(this);
	List <String> devisionInfoList = new ArrayList<String> ();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devisions);
		
		populateArrayList ();		
	}

	public void onListItemClick (ListView parent, View v, int position, long id) {		
		String clickedItemNameDevision = devisionInfoList.get(position);
				
		Intent intent = new Intent(this,DistrictsActivity.class);
		intent.putExtra("Devision:", clickedItemNameDevision);
		startActivity(intent);	
	}
	
	
	//_________________________________________________________________________________ adapter method and data retrieve process
	public void populateArrayList () {
		List <String> devisionInfoList = getAllDevision();				
		ListView listView = getListView();
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,devisionInfoList);		
		listView.setAdapter(arrayAdapter);
	}
	
	public List <String> getAllDevision() {
		dbOperation.open();
		
		Cursor c= dbOperation.getDevisionName();
		c.moveToFirst();
		while (! c.isAfterLast()) {
			String devisionInfo= new String(c.getString(0));
			devisionInfoList.add(devisionInfo);

			c.moveToNext();
		}
		c.close();
		return devisionInfoList;
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.devisions, menu);
		return true;
	}

}
