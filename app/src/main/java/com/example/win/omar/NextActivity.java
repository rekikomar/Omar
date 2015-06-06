package com.example.win.omar;


import com.example.win.omar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.next);


	        String notifiedby = getIntent().getStringExtra("notifiedby");
		    TextView txt = (TextView)findViewById(R.id.textView1);
	        txt.setText(notifiedby);
	      
	}

}
