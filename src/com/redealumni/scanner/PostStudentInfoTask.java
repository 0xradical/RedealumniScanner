package com.redealumni.scanner;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


class PostStudentInfoTask extends AsyncTask<String,Void,String>
{

	private Activity mainActivity;
	private String postResult;
	private Boolean success;
	
	public PostStudentInfoTask(Activity activity)
	{
		super();
		this.mainActivity = activity;
		
	}
	
	@Override
	protected String doInBackground(String... studentCode)
	{
		// POST data
		
	    // Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://algumdominio.heroku.com/validate");

	    
	    try 
	    {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("student_code", studentCode[0]));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        // Read the content
	        String line = "";
	        StringBuilder total = new StringBuilder();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        
	        while((line = rd.readLine()) != null)
	        {
	        	total.append(line);
	        }
	        
	        // total has the return string
	        this.postResult = "www";
	        this.success = false;
	    } 
	    catch (ClientProtocolException e)
	    {
	    	this.postResult = "Error";
	        this.success = false;
	    }
	    catch (IOException e) 
	    {
	    	this.postResult = "Could not reach host";
	        this.success = false;
	    }
		
		return this.postResult;
	}
	

     protected void onPostExecute(String result) 
     {
		 TextView messageView = (TextView)mainActivity.findViewById(R.id.message_view);
		// Setting visibility of a View
		//messageView.setVisibility(TextView.GONE);

		 
    	 if(success)
    	 {
    		messageView.setTextColor(R.color.success_text_color);
    	 }
    	 else
    	 {
    		 messageView.setTextColor(R.color.failure_text_color); 
    	 }
    	 
    	 messageView.setText(result);
    	 
     }

	 
}
