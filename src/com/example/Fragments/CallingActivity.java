package com.example.Fragments;

import com.example.emergencyphonecall.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CallingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calling);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calling, menu);
		return true;
	}

}
