package com.example.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.CustomAdapter.CustomListViewAdapter;
import com.example.UtilClasses.CustomDialogBox;
import com.example.database.DatabaseOperation;
import com.example.emergencyphonecall.R;

public class Thana extends Fragment implements OnItemClickListener{
	Context context;
	DatabaseOperation dbOperation = new DatabaseOperation(getActivity());
	public static List <String> thanaName = new ArrayList<String> ();
	public static List <String> thanaMobileNumber = new ArrayList<String> ();
	private String clickedItemNameDistrict = "Dhaka" ;
	
	View v ;
	
	public Thana() {
		
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {             
        
		View rootView = inflater.inflate(R.layout.activity_tha, container, false);
		this.v=rootView;
        
		//Intent intent = getActivity().getIntent();
		//Intent intent = new Intent();		
		//clickedItemNameDistrict = getActivity().getIntent().getStringExtra("District");
		TextView headingThana = (TextView)rootView.findViewById(R.id.heading_thana);
		
		ListView list = (ListView)rootView.findViewById(R.id.listViewInstituate);
		
		headingThana.setText(clickedItemNameDistrict);
		
		populateArrayListOfDhakaDistrict ();
               
        return rootView;
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String clickedItemNumberThana = thanaMobileNumber.get(arg2);
		String clickedItemNameThana = thanaName.get(arg2);
		final String clickedItemNumber =clickedItemNumberThana;
   //__________________________________________________________________________ Dialog Box code     
       
		new CustomDialogBox(getActivity()).dialogForThana(clickedItemNameThana , clickedItemNumberThana);
		
	}
	
	public void populateArrayListOfDhakaDistrict () {
		
		List <String> thanaInfoList = getAllThanaInfo();
		ListView listView = (ListView)v.findViewById(R.id.listViewInstituate);
		listView.setOnItemClickListener(this);			
		listView.setAdapter(new CustomListViewAdapter(getActivity(), thanaInfoList, thanaMobileNumber));

	}

	public List <String> getAllThanaInfo() {
	
		DatabaseOperation dbOperation = new DatabaseOperation(getActivity());
		dbOperation.open();
				
		Cursor c= dbOperation.getThanaInfo(clickedItemNameDistrict);
		c.moveToFirst();
		
		while (! c.isAfterLast()) {
			
		String thanaInfo= new String(c.getString(0));
		//String thanaInfoName = c.getString(c.getColumnIndex("thana_name"));
		String thanaInfoMobile = c.getString(c.getColumnIndex("mobile_no"));
		String thanaInfoTelephone = c.getString(c.getColumnIndex("telephone_no"));
		//String teleInfo = new String (c.getColumnIndex(thanaInfo));
		thanaName.add(thanaInfo);
		thanaMobileNumber.add(thanaInfoMobile);
		//Log.i(tag, thanaInfoTelephone, null);
		//System.out.println("Thana is:"+thanaInfoName);
		System.out.println("Mobile is:" +thanaInfoMobile);
		c.moveToNext();
		
		}
		
		c.close();
		dbOperation.close();
		System.out.println("thana name is:"+thanaName);
		return thanaName;
	
	}

}
