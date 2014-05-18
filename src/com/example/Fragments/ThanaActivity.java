package com.example.emergencyphonecall;


import java.util.ArrayList;
import java.util.List;



import com.example.CustomAdapter.CustomListViewAdapter;
import com.example.UtilClasses.CustomDialogBox;
//import com.example.calltesting.MainActivity.PhoneCallListener;
import com.example.database.DatabaseOperation;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ThanaActivity extends Activity implements OnItemClickListener {

	Context context;
	DatabaseOperation dbOperation = new DatabaseOperation(this);
	public static List <String> thanaName = new ArrayList<String> ();
	public static List <String> thanaMobileNumber = new ArrayList<String> ();
	private String clickedItemNameDistrict ;
		
	public ThanaActivity() {
		
	}
	
	public ThanaActivity (Context context) {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thana);				
		
		Intent intent = getIntent();
		clickedItemNameDistrict = intent.getExtras().getString("District");
		TextView headingThana = (TextView)findViewById(R.id.heading_thana);
		headingThana.setText(clickedItemNameDistrict);
		
		populateArrayListOfDhakaDistrict ();	
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String clickedItemNumberThana = thanaMobileNumber.get(arg2);
		String clickedItemNameThana = thanaName.get(arg2);
		final String clickedItemNumber =clickedItemNumberThana;
   //__________________________________________________________________________ Dialog Box code     
        new CustomDialogBox(this).dialogForThana(clickedItemNameThana , clickedItemNumberThana);
		
/*		final Dialog dialog = new Dialog (context);
        dialog.setContentView(R.layout.dialog_box_thana);
        dialog.setTitle(""+clickedItemNameThana+" Thana");
        
        TextView mobileNumber = (TextView)dialog.findViewById(R.id.mobile_number_txt_view);
        TextView telephoneNumber = (TextView)dialog.findViewById(R.id.telephone_number_txt_view);
        TextView emaiAddress =(TextView)dialog.findViewById(R.id.email_address);
        TextView address = (TextView)dialog.findViewById(R.id.address);        
        Button call = (Button)dialog.findViewById(R.id.call_button);
        Button nearestThana = (Button)dialog.findViewById(R.id.nearest_thana_button);
        
        mobileNumber.setText("Mobile: "+clickedItemNumberThana);
        telephoneNumber.setText("Telephone: ");
        call.setText("Call");
        nearestThana.setText("See on Map");
        emaiAddress.setText("Email: ");
        address.setText("Address: ");
        
        
        PhoneCallListener  phoneListener = new PhoneCallListener (context);
		TelephonyManager teleManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		teleManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        call.setOnClickListener(new OnClickListener (){
        	
        	//String number =clickedItemNumber;
        	@Override
        	public void onClick (View v) {
        		
        		Intent callIntent = new Intent (Intent.ACTION_CALL);				
				//callIntent.setData(Uri.parse("tel:01724595314"));
        		callIntent.setData(Uri.parse("tel:"+clickedItemNumber));
				startActivity(callIntent);
        	}
        });
        dialog.show();*/
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thana, menu);
		return true;
	}

	public void populateArrayListOfDhakaDistrict () {
		
		List <String> thanaInfoList = getAllThanaInfo();
		ListView listView = (ListView)findViewById(R.id.listViewInstituate);
		listView.setOnItemClickListener(this);			
		listView.setAdapter(new CustomListViewAdapter(this, thanaInfoList, thanaMobileNumber));

		}

	public List <String> getAllThanaInfo() {
		dbOperation.open();
		
		Cursor c= dbOperation.getThanaInfo(clickedItemNameDistrict);
		c.moveToFirst();
		while (! c.isAfterLast()) {
			//String thanaInfo= new String(c.getString(0));
			String thanaInfoName = c.getString(c.getColumnIndex("thana_name"));
			String thanaInfoMobile = c.getString(c.getColumnIndex("mobile_no"));
			String thanaInfoTelephone = c.getString(c.getColumnIndex("telephone_no"));
			//String teleInfo = new String (c.getColumnIndex(thanaInfo));
			thanaName.add(thanaInfoName);
			thanaMobileNumber.add(thanaInfoMobile);
			//Log.i(tag, thanaInfoTelephone, null);
			System.out.println("Thana is:"+thanaInfoName);
			System.out.println("Mobile is:" +thanaInfoMobile);
			c.moveToNext();
		}
		c.close();
		return thanaName;
		
	}
 
	
	// __________________________________________________________________________Calling class
	public class PhoneCallListener extends PhoneStateListener {
		public boolean isPhoneCalling = false;
		String LOG_TAG = "LOGGING 123";
		
		public PhoneCallListener(Context context) {
			
		}
				
		public PhoneCallListener(CustomDialogBox customDialogBox) {
			
		}

		@Override
		public void onCallStateChanged (int state, String incomingNumber) {
			if (TelephonyManager.CALL_STATE_RINGING==state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number:" +incomingNumber);
			}
			
			if (TelephonyManager.CALL_STATE_OFFHOOK==state){
				//active
				Log.i(LOG_TAG, "OFFHOOK");
				isPhoneCalling= true ;
			}
			if (TelephonyManager.CALL_STATE_IDLE==state){
				//run when class initial and phone call ended
				//nee detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");
				if (isPhoneCalling) {
					Log.i(LOG_TAG,"restart app");
					
					// restart app 
					Intent intents = getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(
								getBaseContext().getPackageName());
						intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intents);
	 
						isPhoneCalling = false;
				}
			}
		}

	}
	
	
	

}
