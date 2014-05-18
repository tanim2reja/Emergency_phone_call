package com.example.Fragments;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.CustomAdapter.TabsAdapter;
import com.example.emergencyphonecall.R;

public class Tabs extends FragmentActivity implements ActionBar.TabListener {
	Context  mContext;
	
	private ViewPager viewPager;
	private TabsAdapter mAdapter;
	private ActionBar actionBar;
	private String [] tabName={ "Districts","Thana"};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		
		mContext= this;
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		FragmentManager fm = getSupportFragmentManager();
		mAdapter = new TabsAdapter(fm);
		
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for (String tab_name : tabName) {
			//Tab actionBars= actionBar.newTab().setText(tab_name).setTabListener(this);
			//actionBar.addTab(actionBars);
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	

	@Override
	public void onTabReselected(Tab tab,FragmentTransaction ft) {
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab,FragmentTransaction ft) {
			
		
		viewPager.setCurrentItem(tab.getPosition());
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft ) {
		// TODO Auto-generated method stub
		
	}
	

}
