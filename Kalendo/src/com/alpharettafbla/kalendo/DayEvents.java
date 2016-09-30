package com.alpharettafbla.kalendo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alpharettafbla.kalendo.R;

public class DayEvents extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_events);

		//Collects the selected date's information
		Intent intent = getIntent();
		getDayEvents(intent);
		
		//Formats back button
		initializeBackBtn();
	}
	
	
	public void getDayEvents(Intent intent) {
		
		//Retrieves the selected date as a millisecond value
		long cal = intent.getLongExtra("CalendarDate",
				Calendar.getInstance().getTimeInMillis());
		Calendar headerDate = Calendar.getInstance();
		headerDate.setTimeInMillis(cal);

		//Displays selected date
		initializeDateText(cal, headerDate);
		
		//Events will be added to this layout
		LinearLayout eventsView = (LinearLayout) findViewById(R.id.event_view);

		String calURL = BuildURL.dateURL(cal);
		RequestCal googleCal = (RequestCal) new RequestCal().execute(calURL);
			
		try {
			//Saves the returned object as a JSON Object
			JSONObject jsonObj = new JSONObject(googleCal.get());
			
			ArrayList<EventInfo> allEvents = EventList.analyzeData(jsonObj);
			
			if (allEvents.size() == 0) {
				TextView noEvents = new TextView(this);
				EventText.noEvents(noEvents, getString(R.string.no_selected));
				eventsView.addView(noEvents);
				return;
			}
			
			//Loops through events list to analyze them individually
			for (EventInfo indexEvent : allEvents) {
				
				//Event name
				TextView eventHeader = new TextView(this);
				EventText.header(eventHeader, indexEvent);
				eventsView.addView(eventHeader);

				//Event time
				TextView eventTime = new TextView(this);
				EventText.time(eventTime, indexEvent);
				eventsView.addView(eventTime);

				//Event description
				TextView eventDesc = new TextView(this);
				EventText.descrip(eventDesc, indexEvent);
				eventsView.addView(eventDesc);
				
				//Skips line between events
				TextView spacer = new TextView(this);
				EventText.spacer(new TextView(this));
				eventsView.addView(spacer);
			}

		} catch (JSONException e) {
			Log.e("JSONException", "Error: " + e.toString());
		} catch (ExecutionException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		} catch (InterruptedException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		}	
	}
	
	
	public void initializeDateText(long cal, Calendar headerDate) {
		//Displays the selected date
		TextView eventText = (TextView) findViewById(R.id.event_text);
		eventText.setMovementMethod(new ScrollingMovementMethod());
		TextView dateView = (TextView) findViewById(R.id.date_view);
		dateView.setGravity(Gravity.CENTER_HORIZONTAL);
		dateView.setTextColor(Color.BLACK);
		dateView.setTypeface(null, Typeface.BOLD);
		dateView.setText(DateFormat.format("MMMM d, yyyy ", headerDate));
	}
	
	
	public void initializeBackBtn() {
		//Resizes the back button
		ImageButton backBtn = (ImageButton) findViewById(R.id.back_btn);
		int btnHeight = getScreenHeight()/8;
		backBtn.setMinimumHeight(0);
		backBtn.setMinimumWidth(0);
		backBtn.setMaxHeight(btnHeight);
		backBtn.setMaxWidth(btnHeight);
	}
	

	public int getScreenHeight() {
		//Gets the height of the screen for formatting page elements
		return getResources().getDisplayMetrics().heightPixels;
	}
	

	public void backToCal(View view) {
		//Closes the event view and returns to calendar
		finish();
	}

	
}
