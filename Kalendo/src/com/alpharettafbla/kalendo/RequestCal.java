package com.alpharettafbla.kalendo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

class RequestCal extends AsyncTask<String, String, String> {
	

	@Override
	protected String doInBackground(String... uri) {
		//Makes connections with the API
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = client.execute(new HttpGet(uri[0]));
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				responseString = out.toString();
				out.close();
			} else {
				//Closes the connection
				response.getEntity().getContent().close();
				throw new IOException(status.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseString;
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
	
	
}