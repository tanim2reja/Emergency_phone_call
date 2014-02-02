package com.example.UtilClasses;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Caller extends PhoneStateListener {
	    
	Context context;
	public boolean isPhoneCalling = false;
	String LOG_TAG = "LOGGING 123";
	
	public Caller(Context context) {
		this.context = context;		
	}
	
	public Caller(CustomDialogBox customDialogBox) {
	
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
