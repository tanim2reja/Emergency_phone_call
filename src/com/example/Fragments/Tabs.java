package com.example.emergencyphonecall;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class Tab extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab, menu);
		return true;
	}

}
