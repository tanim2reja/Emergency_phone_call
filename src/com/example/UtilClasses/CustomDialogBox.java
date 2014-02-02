package com.example.UtilClasses;


import com.example.emergencyphonecall.R;
import com.example.emergencyphonecall.ThanaActivity;
import com.example.emergencyphonecall.ThanaActivity.PhoneCallListener;




import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class CustomDialogBox {
	
	Context context;
	ThanaActivity thanaActivity;

	public CustomDialogBox (Context context) {
		super ();
		this.context=context;
		
	}
	
	public void dialogForThana (String name,  String number) {
		
		String clickedItemNameThana = name;
		String clickedItemNumberThana = number;
		
		final String clickedItemNumber =clickedItemNumberThana;
		
		final Dialog dialog = new Dialog (context);
		
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
        
        Caller callerObject = new Caller(this);
       // PhoneCallListener  phoneListener = new PhoneCallListener (this);
		TelephonyManager teleManager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
		teleManager.listen(callerObject, callerObject.LISTEN_CALL_STATE);
		//teleManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        
		call.setOnClickListener(new OnClickListener (){
        	
        	//String number =clickedItemNumber;
        	@Override
        	public void onClick (View v) {
        		
        		Intent callIntent = new Intent (Intent.ACTION_CALL);				
				//callIntent.setData(Uri.parse("tel:01724595314"));
        		callIntent.setData(Uri.parse("tel:"+clickedItemNumber));
				context.startActivity(callIntent);
        	}
        });
        dialog.show();
	}
//-------------------------------------------------------------------------------------	nested class
	public class PhoneCallListener extends PhoneStateListener {
		public boolean isPhoneCalling = false;
		String LOG_TAG = "LOGGING 123";
		
		public PhoneCallListener(Context context) {
			// TODO Auto-generated constructor stub
		}

		
		
		public PhoneCallListener(CustomDialogBox customDialogBox) {
			// TODO Auto-generated constructor stub
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
					Intent intents = ((ContextWrapper) context).getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(((ContextWrapper) context).getBaseContext().getPackageName());
								
						intents.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						context.startActivity(intents);
	 
						isPhoneCalling = false;
				}
			}
		}

	}

}
