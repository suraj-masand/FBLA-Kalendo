package com.alpharettafbla.kalendo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.alpharettafbla.kalendo.R;

public class InfoActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		//Formats the back button
		initializeBackBtn();
		
		//Displays info text
		infoText();
	}
	
	
	public void infoText() {
		//Displays info text
		TextView info = (TextView) findViewById(R.id.info_text);
		info.setText("Developed for the 2015 FBLA National Leadership Conference\n\n");
		info.append("Created by: Katharine Nelson and Suraj Masand\n");
		info.append("School: Alpharetta High School\n");
		info.append("Event: Mobile Application Development\n\n");
		info.append("This app was developed to help Alpharetta High School announce " +
				"upcoming events to the students. It connects to the school's Google " +
				"calendar and displays the events in three formats: a today view showing " +
				"events for the current day, a list view showing events for the next 90 days, " +
				"and a calendar allowing students to select any date and view its events.");
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
	

	public void goBack(View view) {
		//Closes the event view and returns to the last activity
		finish();
	}
	
	
}