package com.example.emergencyphonecall;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class InputThanaActivity extends ListActivity {

	
	String[] presidents= {
			"a",
			"b",
			"c",
			"d",
			"e",
			"f",
			"g"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_input_thana);
		
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, presidents));
		
		
		
	}
	
	
	public void onListItemClick(
			ListView parent, View v, int position, long id)
			{
			Toast.makeText(this,
			"You have selected "+ presidents[position],
			Toast.LENGTH_SHORT).show();
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input_thana, menu);
		return true;
	}

}
