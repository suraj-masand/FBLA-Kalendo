package com.alpharettafbla.kalendo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

public class EventText {
	
	private static SimpleDateFormat displayDate = new SimpleDateFormat("MMMM d, yyyy",
			Locale.getDefault());
	private static SimpleDateFormat displayTime = new SimpleDateFormat("h:mm a",
			Locale.getDefault());
	private static int bgCol = Color.argb(255, 132, 218, 209);
	
	
	public static void header(TextView textView, EventInfo indexEvent) {
		//Formats the text for the event name
		textView.setBackgroundColor(bgCol);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(5, 5, 5, 2);
		textView.setText(indexEvent.getName());
	}
	
	
	public static void time(TextView textView, EventInfo indexEvent) {
		//Formats the text for the event time
		textView.setBackgroundColor(bgCol);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(5, 2, 5, 2);
		if (indexEvent.isAllDay())
			textView.setText("All-day event");
		else
			textView.setText(displayTime.format(indexEvent.getStartDate()) + " - "
					+ displayTime.format(indexEvent.getEndDate()));
	}
	
	
	public static void dateTime(TextView textView, EventInfo indexEvent) {
		//Formats the text for the event date and time
		textView.setBackgroundColor(bgCol);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTypeface(null, Typeface.BOLD);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(5, 2, 5, 2);
		if (indexEvent.isAllDay())
			textView.setText(displayDate.format(indexEvent.getStartDate())
					+ "\nAll-day event");
		else
			textView.setText(displayDate.format(indexEvent.getStartDate()) + "\n"
					+ displayTime.format(indexEvent.getStartDate()) + " - "
					+ displayTime.format(indexEvent.getEndDate()));
	}
	
	
	public static void descrip(TextView textView, EventInfo indexEvent) {
		//Formats the text for the event description
		textView.setBackgroundColor(bgCol);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTypeface(null, Typeface.ITALIC);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(5, 2, 5, 10);
		textView.setText(indexEvent.getDescrip());
	}
	
	
	public static void noEvents(TextView textView, String noVal) {
		//Text to show if there are no events
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
		textView.setTextColor(Color.BLACK);
		textView.setGravity(Gravity.CENTER);
		textView.setText(noVal);
	}
	
	
	public static void spacer(TextView spacer) {
		//Spaces between the events
		spacer.setBackgroundColor(Color.TRANSPARENT);
		spacer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
	}
	
	
}