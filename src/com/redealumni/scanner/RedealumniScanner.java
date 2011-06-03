package com.redealumni.scanner;

import com.biggu.barcodescanner.client.android.Intents;
import com.redealumni.scanner.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RedealumniScanner extends Activity
{
	
	private static final int SCANNER_REQUEST_CODE = 0;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button)findViewById(R.id.btn);
	    
		String result = "";
		
		new PostStudentInfoTask(this).execute(result);
		
		button.setOnClickListener(new OnClickListener() 
		{

			public void onClick(View v) 
			{

				Intent intent = new Intent(v.getContext(), com.redealumni.scanner.ScannerActivity.class);
				intent.putExtra(Intents.Preferences.ENABLE_BEEP, true);
				intent.putExtra(Intents.Preferences.ENABLE_VIBRATE, true);

				((Activity)v.getContext()).startActivityForResult(intent, SCANNER_REQUEST_CODE);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		
		if (resultCode == Activity.RESULT_OK && requestCode == SCANNER_REQUEST_CODE) 
		{
			
			//sample code to generate JSON
			StudentInfo stdInfoSample = new StudentInfo();
			String sample = stdInfoSample.toJson();
			
			//code to read stuff from JSON
			StudentInfo stdInfo = StudentInfo.fromJson(sample);
			String result = stdInfo.getVerificationCode();
			
			//Bundle extras = data.getExtras();
			//String result = extras.getString("SCAN_RESULT");
//			TextView textView = (TextView)findViewById(R.id.txt);
//			textView.setText(result);
			
			// this method should post result to an url
			new PostStudentInfoTask(this).execute(result);

		}
	}
}