package com.redealumni.scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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


class PostStudentInfoTask extends AsyncTask<String,Void,String>
{

	private Activity mainActivity;
	private String postResult;
	private Boolean success;
	private ProgressDialog progressDialog;
	private AlertDialog alertDialog;
	
	public PostStudentInfoTask(Activity activity)
	{
		super();
		this.mainActivity = activity;
		
	}
	
	protected void onPreExecute()
	{
		// build progress dialog
        this.progressDialog = new ProgressDialog(this.mainActivity);
        this.progressDialog.setMessage("Validando carta ...");
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
        
        // build alert dialog
        this.alertDialog = new AlertDialog.Builder(this.mainActivity).create();
        this.alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            return;
          } });
        
	}
	
	@Override
	protected String doInBackground(String... studentCode)
	{
		// POST data
		
	    // Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://someherokusite.heroku.com/validate");
	    
	    try 
	    {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("studentCode", studentCode[0]));
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
	        this.postResult = total.toString();
	        this.success = true;
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
    	 
    	 if(this.success)
    	 {
    	       
    		 this.alertDialog.setTitle("Sucesso");
        	 this.alertDialog.setIcon(android.R.drawable.ic_dialog_info);

    	 }
    	 else
    	 {
    		 this.alertDialog.setTitle("Erro");
    		 this.alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
    	 }
    	 
    	 this.alertDialog.setMessage(result);
         this.alertDialog.show();
         
    	 progressDialog.dismiss();
     }

	 
}
