package com.example.win.omar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver
{
	 
	@Override
	 public void onReceive(Context context, Intent intent)
	{
	   Intent service1 = new Intent(context, MyAlarmService.class);
	   
	   context.startService(service1);
	   
	   
	 }
	
}
