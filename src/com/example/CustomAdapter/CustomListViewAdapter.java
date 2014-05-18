package com.example.CustomAdapter;

import java.util.List;

import com.example.Fragments.ThanaActivity;
import com.example.emergencyphonecall.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter {
	
	Context context ;	
	List <String> t;
	List <String> tn;	
	TextView thanaName;
	TextView thanaNumber;
	
	
	@SuppressWarnings("unchecked")
	public CustomListViewAdapter(Context context, List <String> tName, List <String> tNumber) {
		
		super (context, R.layout.custom_listview,tNumber);{
		
		this.context = context;
		this.t=tName;
		this.tn=tNumber;
		}
	}
	
	
	@Override
	public  View getView (int position, View convertView,ViewGroup parent) {
		
		View row = convertView;
		
		LayoutInflater intflater = ((Activity) context).getLayoutInflater();
		row = intflater.inflate(R.layout.custom_listview, parent, false);
		
		thanaName = (TextView)row.findViewById(R.id.textViewInstituateName);
		thanaNumber = (TextView)row.findViewById(R.id.textViewInstituateNumber);
		
		thanaName.setText(t.get(position));
		thanaNumber.setText(tn.get(position));
		
		return row;
		
		
	}
	
	
	


}
