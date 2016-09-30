package com.alpharettafbla.kalendo;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import com.alpharettafbla.kalendo.R;

public class CalActivity extends Activity {

	CalendarView calendar;
	TextView selectedDate;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cal);

		//Sets up the calendar
		initializeCalendar();
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
	

	public void initializeCalendar() {
		//Prepares the calendar for viewing
		calendar = (CalendarView) findViewById(R.id.calendar_view);
		Calendar today = Calendar.getInstance();
		calendar.setDate(today.getTime().getTime(), false, true);
		
		//Whether to show the week number
		calendar.setShowWeekNumber(false);

		//Sets the first day of the week according to Calendar
		calendar.setFirstDayOfWeek(1);

		//Initializes the date selected on the calendar
		selectedDate = (TextView) findViewById(R.id.selected_date);
		selectedDate.setTextColor(Color.BLACK);
		selectedDate.setTypeface(null, Typeface.BOLD);
		selectedDate.setText(DateFormat.format("MMMM d, yyyy ", calendar.getDate()));

		//Sets the listener to be notified upon selected date change
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int day) {
				selectedDate.setText(DateFormat.format("MMMM d, yyyy ", calendar.getDate()));
			}
		});
	}

	
	public void toDayEvents(View view) {
		//Opens up the specific set of events for the selected date
		Calendar selectDate = Calendar.getInstance();
		selectDate.setTimeInMillis(calendar.getDate());

		//Sends data for the selected date
		Intent i = new Intent(this, DayEvents.class);
		i.putExtra("CalendarDate", selectDate.getTimeInMillis());
		startActivity(i);
	}
	
	
	public void toList(View view) {
		//Opens the list view
		Intent i = new Intent(this, ListActivity.class);
		startActivity(i);
		finish();
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

	
}
