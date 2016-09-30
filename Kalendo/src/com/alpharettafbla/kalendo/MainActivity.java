package com.alpharettafbla.kalendo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alpharettafbla.kalendo.R;

public class MainActivity extends Activity {

	private LinearLayout horizLayout;
	private HorizontalScrollView horizScroll;
	private int scrollMax;
	private int scrollPos = 0;
	private TimerTask scrollerSchedule;
	private Timer scrollTimer = null;
	private TextView todayEvents;
	private boolean hasInternet = false;
	
	//imageNameArray contains references to pictures of Alpharetta High School students at various events
	private String[] imageNameArray = { "ahs", "swim_team", "fbla_comp", "basketball_team", 
				"science_fair", "science_fair2", "speech", "fbla_sign", "college_fair", 
				"hosa", "fccla", "basketball_game", "raiders", "speech_nationals", 
				"speech_debate", "fbla_hug", "experimental_kitchen", "prom" };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Sets up banner image scroller
		initializeBannerScroller();
		
		//Displays today's date
		initializeDateText();
		
		//Checks network before accessing the calendar
		checkInternet();
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
	
	
	public void initializeDateText() {
		//Formats text for the current date
		Date date = new Date();
		todayEvents = (TextView) findViewById(R.id.today_view);
		todayEvents.setGravity(Gravity.CENTER);
		todayEvents.setTextColor(Color.BLACK);
		todayEvents.setTextSize(28);
		todayEvents.setTypeface(null, Typeface.BOLD);
		todayEvents.setText(DateFormat.format("MMMM d, yyyy ", date)+"\n");
	}
	
	
	public void checkInternet() {
		//Internet is required to load calendar data
		if (CheckNetwork.isInternetAvailable(getApplicationContext())) {
			//Okay to proceed with app processes
			hasInternet = true;
			todayEvents.append("Today's Events:\n");
			initializeTodayEvents();		
		}
		else {
			hasInternet = false;
			//Cannot do required processes without connection
			todayEvents.append("\nThere is no Internet connection.\nPlease connect and restart the app.");
		}
	}

	
	public void initializeTodayEvents() {
		
		//Events will be added to this layout
		LinearLayout todayLayout = (LinearLayout) findViewById(R.id.today_layout);
		
		String calURL = BuildURL.todayURL();
		RequestCal googleCal = (RequestCal) new RequestCal().execute(calURL);
	
		try {
			//Saves the returned object as a JSON Object
			JSONObject jsonObj = new JSONObject(googleCal.get());
			
			ArrayList<EventInfo> allEvents = EventList.analyzeData(jsonObj);
			
			if(allEvents.size() == 0) {
				TextView noEvents = new TextView(this);
				EventText.noEvents(noEvents, getString(R.string.no_today));
				todayLayout.addView(noEvents);
				return;
			}
			
			//Loops through events list to analyze them individually
			for (EventInfo indexEvent : allEvents) {
				
				//Event name
				TextView eventHeader = new TextView(this);
				EventText.header(eventHeader, indexEvent);
				todayLayout.addView(eventHeader);
	
				//Event time
				TextView eventTime = new TextView(this);
				EventText.time(eventTime, indexEvent);
				todayLayout.addView(eventTime);
	
				//Event description
				TextView eventDesc = new TextView(this);
				EventText.descrip(eventDesc, indexEvent);
				todayLayout.addView(eventDesc);
	
				//Skips line between events
				TextView spacer = new TextView(this);
				EventText.spacer(spacer);
				todayLayout.addView(spacer);
			}
	
		} catch (JSONException e) {
			Log.e("JSONException", "Error: " + e.toString());
		} catch (ExecutionException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		} catch (InterruptedException e) {
			Log.e("ExecutionException", "Error: " + e.toString());
		}
	}


	public void initializeBannerScroller() {
		//Banner image scroller setup
		horizScroll = (HorizontalScrollView) findViewById(R.id.horiz_scroll);
		horizLayout = (LinearLayout) findViewById(R.id.horiz_layout);
		horizScroll.setHorizontalScrollBarEnabled(false);
		addImagesToView();
		ViewTreeObserver vto = horizLayout.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				resetScroll();

			}
		});
		horizScroll.fullScroll(View.FOCUS_LEFT);
	}

	
	public void addImagesToView() {
		//Adds images to the banner scroller
		//Images are of Alpharetta High School students at various events
		for (int i = 0; i < imageNameArray.length; i++) {
			final Button imageButton = new Button(this);
			int imageResourceId = getResources().getIdentifier(
					imageNameArray[i], "drawable", getPackageName());
			Drawable image = this.getResources().getDrawable(imageResourceId);
			imageButton.setBackground(image);
			imageButton.setTag(i);
			int height = getScreenHeight()/5;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height, height);
            params.setMargins(1, 0, 1, 5);
            imageButton.setLayoutParams(params);
            horizLayout.addView(imageButton);
		}
	}
	
	
	public void startAutoScrolling() {
		//Starts the banner scroller and sets speed
		if (scrollTimer == null) {
			scrollTimer = new Timer();
			final Runnable Timer_Tick = new Runnable() {
				public void run() {
					moveScrollView();
				}
			};
			scrollerSchedule = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(Timer_Tick);
				}
			};
			scrollTimer.schedule(scrollerSchedule, 30, 30);
		}
	}


	public void moveScrollView() {
		scrollPos = (horizScroll.getScrollX() + 1);
		if (scrollPos >= scrollMax) {
			//Sets the position back to zero
			scrollPos = 0;
			horizScroll.setScrollX(0);
			resetScroll();
		}
		else {
			//Continues with scrolling
			horizScroll.smoothScrollTo(scrollPos, 0);
		}
	}
	
	
	public void resetScroll() {
		//Get max distance and restart scrolling
		getScrollMaxAmount();
		startAutoScrolling();
	}

	
	public void getScrollMaxAmount() {
		//Determines width of the array of images
		int width = ((horizLayout.getWidth()/horizScroll.getWidth()-1)*horizScroll.getWidth())
				+ (horizLayout.getWidth() % horizScroll
						.getWidth());
		scrollMax = width;
	}

	
	private int getScreenHeight() {
		//Gets the height of the screen for formatting page elements
		return getResources().getDisplayMetrics().heightPixels;
	}


	public void toCal(View view) {
		//Opens the calendar view
		if (hasInternet) {
			Intent i = new Intent(this, CalActivity.class);
			startActivity(i);
		}
		else {
			//No Internet disables app functions
			Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
		}
	}

	
	public void toList(View view) {
		//Opens the list view
		if (hasInternet) {
			Intent i = new Intent(this, ListActivity.class);
			startActivity(i);
		}
		else {
			//No Internet disables app functions
			Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
		}
	}
	

	public void toTwitterFeed(View view) {
		//Opens the twitter view
		if (hasInternet) {
			Intent i = new Intent(this, TwitterFeed.class);
			startActivity(i);
		}
		else {
			//No Internet disables app functions
			Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
		}
	}
	

}