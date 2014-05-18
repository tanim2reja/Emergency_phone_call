package com.example.CustomAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.Fragments.District;
import com.example.Fragments.Thana;

public class TabsAdapter extends FragmentPagerAdapter {
	private int index;

	public TabsAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		this.index = arg0;
		switch (index) {
		case 0 :
			return new District ();
		case 1:
			return new Thana ();
				
		}
	 
		return null ;
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
