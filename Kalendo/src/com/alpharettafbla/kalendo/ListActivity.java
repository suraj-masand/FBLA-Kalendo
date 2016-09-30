package com.alpharettafbla.kalendo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alpharettafbla.kalendo.R;

public class ListActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		//Retrieve events up to 3 months in advance
		initializeEventsList();
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		//Creates the menu for app info
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		//Opens activity to display app info
        switch (item.getItemId()) {
	        case R.id.app_info:
	            Intent i = new Intent(this, InfoActivity.class);
	            startActivity(i);
	            break;
	    }
        return super.onOptionsItemSelected(item);
    }
	
	
	public void initializeEventsList() {
		
		//Events will be added to this layout
		LinearLayout listLayout = (LinearLayout) findViewById(R.id.list_events);
		listLayout.setPadding(0, 18, 0, 0);
		
		String calURL = BuildURL.listURL();
		RequestCal googleCal = (RequestCal) new RequestCal().execute(calURL);
		
		try {
			//Saves the returned object as a JSON Object
			JSONObject jsonObj = new JSONObject(googleCal.get());
			
			ArrayList<EventInfo> allEvents = EventList.analyzeData(jsonObj);
			
			if(allEvents.size() == 0) {
				TextView noEvents = new TextView(this);
				EventText.noEvents(noEvents, getString(R.string.no_upcoming));
				listLayout.addView(noEvents);
				return;
			}
			
			//Loops through events list to analyze them individually
			for (EventInfo indexEvent : allEvents) { 

				//Event name
				TextView eventHeader = new TextView(this);
				EventText.header(eventHeader, indexEvent);
				listLayout.addView(eventHeader);
								
				//Event time
				TextView eventTime = new TextView(this);
				EventText.dateTime(eventTime, indexEvent);
				listLayout.addView(eventTime);
				
				//Event description
				TextView eventDesc = new TextView(this);
				EventText.descrip(eventDesc, indexEvent);
				listLayout.addView(eventDesc);

				//Skips line between events
				TextView spacer = new TextView(this);
				EventText.spacer(spacer);
				listLayout.addView(spacer);
			}
			
		} catch (JSONException e) {
			Log.e("JSONException", "Error: " + e.toString());
		} // catch (JSONException e)
		catch (ExecutionException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		} catch (InterruptedException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		}
	}

	
	public void toHome(View view) {
		//Returns to the home page
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
	
	
	public void toTwitterFeed(View view) {
		//Opens the twitter view
		Intent i = new Intent(this, TwitterFeed.class);
		startActivity(i);
		finish();
	}
	
	
	public void toCal(View view) {
		//Opens the calendar view
		Intent i = new Intent(this, CalActivity.class);
		startActivity(i);
		finish();
	}

	
}
