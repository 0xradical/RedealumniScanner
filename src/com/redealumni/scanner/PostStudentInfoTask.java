package com.redealumni.scanner;

import android.os.AsyncTask;

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


class PostStudentInfoTask extends AsyncTask<String,Void,Void>
{

	@Override
	protected Void doInBackground(String... studentCode)
	{
		// POST data
		
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

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
	        
	    } 
	    catch (ClientProtocolException e)
	    {
	        // TODO Auto-generated catch block
	    }
	    catch (IOException e) 
	    {
	        // TODO Auto-generated catch block
	    }
		
		return null;
	}
	
	 protected void onProgressUpdate(Integer... progress) 
	 {
		 // do something
     }

     protected void onPostExecute(Long result) 
     {
    	 // do something
     }

	 
}
