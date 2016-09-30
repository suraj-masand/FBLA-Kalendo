package com.alpharettafbla.kalendo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class EventInfo {
	
	private String name;
	private String descrip;
	private Date startDate;
	private Date endDate;
	private String startTime;
	private String endTime;
	private boolean isAllDay = false;
	
	SimpleDateFormat forDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	SimpleDateFormat forDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
	
	public EventInfo (JSONObject singleEvent) {
		//Will store the name, description, and timings for each event
		try {
			
			//Gets event name
			name = singleEvent.getString("summary");

			//Checks if description is available
			if (singleEvent.has("description"))
				descrip = singleEvent.getString("description");
			else
				descrip = "There is no description for this event";
			
			//Fetches timings of the event
			if (singleEvent.getJSONObject("start").has("date")) {
				//If the event has a start date then it is an all-day event
				isAllDay = true;
				String s = singleEvent.getJSONObject("start").getString("date");
				startDate = forDate.parse(s);
			}
			else {
				//Else, there must be a start dateTime and end dateTime
				startTime = singleEvent.getJSONObject("start").getString("dateTime");
				startDate = forDateTime.parse(startTime);
				endTime = singleEvent.getJSONObject("end").getString("dateTime");
				endDate = forDateTime.parse(endTime);
			}
			
		} catch (JSONException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getName() {
		return name;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	
	public String getStartTime() {
		return startTime;
	}

	
	public String getEndTime() {
		return endTime;
	}

	
	public String getDescrip() {
		return descrip;
	}

	
	public boolean isAllDay() {
		return isAllDay;
	}
	
	
}