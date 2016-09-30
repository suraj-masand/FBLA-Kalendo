package com.alpharettafbla.kalendo;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class BuildURL {
	
	//School's google calendar id
	private static final String cal_id = "b3ugdgfdvqf5ldgns33drkn314%40group.calendar.google.com";
		
	//API Key to allow API call
	private static final String myApiKey = "AIzaSyA21YKyARNKTxphFz95aYmGv7SQSWnEX3k";
	
	
	public static String todayURL() {
		Calendar todayDate = Calendar.getInstance();
		Calendar tomorrowDate = Calendar.getInstance();
		
		//Sets up max to end of current date
		tomorrowDate.add(Calendar.DATE, 1);
		
		//Gets time zone info
		String tz = timeZone(todayDate);
		
	
		//Limits results based on today
		String minTime = todayDate.get(Calendar.YEAR) + "-"
				+ (todayDate.get(Calendar.MONTH) + 1) + "-"
				+ todayDate.get(Calendar.DATE) + tz;
		String maxTime = tomorrowDate.get(Calendar.YEAR) + "-"
				+ (tomorrowDate.get(Calendar.MONTH) + 1) + "-"
				+ tomorrowDate.get(Calendar.DATE) + tz;
		
		return createBase(minTime, maxTime);
	}
	
	
	public static String listURL() {
		Calendar todayCal = Calendar.getInstance();
		Calendar endDateCal = Calendar.getInstance();

		//Sets up max to 90 days in the future
		endDateCal.add(Calendar.DATE, 90);
		
		//Gets time zone info
		String tz = timeZone(todayCal);
		
		//Limits results to 90 days in the future
		String minTime = todayCal.get(Calendar.YEAR) + "-"
				+ (todayCal.get(Calendar.MONTH) + 1) + "-" 
				+ todayCal.get(Calendar.DATE) + tz;
		String maxTime = endDateCal.get(Calendar.YEAR) + "-"
				+ (endDateCal.get(Calendar.MONTH) + 1) + "-"
				+ endDateCal.get(Calendar.DATE) + tz;
		
		return createBase(minTime, maxTime);
	}
	
	
	public static String dateURL(long cal) {
		Calendar startDateCal = Calendar.getInstance();
		Calendar endDateCal = Calendar.getInstance();
		
		//Determines selected date using parameter (millisecond value)
		startDateCal.setTimeInMillis(cal);
		endDateCal.setTimeInMillis(cal);
		
		//Sets up max to end of selected date
		endDateCal.add(Calendar.DATE, 1);
		
		//Gets time zone info
		String tz = timeZone(startDateCal);
		
		//Limits results based on selected date
		String minTime = startDateCal.get(Calendar.YEAR) + "-"
				+ (startDateCal.get(Calendar.MONTH) + 1) + "-"
				+ startDateCal.get(Calendar.DATE) + tz;
		String maxTime = endDateCal.get(Calendar.YEAR) + "-"
				+ (endDateCal.get(Calendar.MONTH) + 1) + "-"
				+ endDateCal.get(Calendar.DATE) + tz;
		
		return createBase(minTime, maxTime);
	}
	
	
	public static String twitterURL(String twitterAcc) {
		if (twitterAcc.equals("AHSRaiderFBLA"))
			return "<a unselectable=\"on\" class=\"twitter-timeline\"" +
			    "data-dnt=\"true\" href=\"https://twitter.com/AHSRaiderFBLA\"" +
				" data-widget-id=\"594912738868129792\">Tweets by @AHSRaiderFBLA</a>" +
			    "<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0]," +
			    "p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id))" +
			    "{js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";" +
			    "fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";
		else if (twitterAcc.equals("FultonCoSchools"))
			return "<a unselectable=\"on\" class=\"twitter-timeline\"" +
	    		"data-dnt=\"true\" href=\"https://twitter.com/FultonCoSchools\"" +
				" data-widget-id=\"563474509945401346\">Tweets by @FultonCoSchools</a>" +
	            "<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0]," +
	    		"p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id))" +
	            "{js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";" +
	    		"fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";
		else
			return "<a unselectable=\"on\" class=\"twitter-timeline\"" +
				"data-dnt=\"true\" href=\"https://twitter.com/RaiderSport\"" +
				" data-widget-id=\"594913254289313792\">Tweets by @RaiderSport</a>" +
				"<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0]," +
				"p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id))" +
				"{js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";" +
				"fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";
	}
	
	
	private static String timeZone(Calendar cal) {
		//Gets the user's time zone
		int tz = TimeZone.getDefault().getOffset(cal.getTimeInMillis());
		int offsetHours = tz/3600000;
		int offsetMinutes = (tz%3600000)/60000;
		String timeZone = "T00%3A00%3A00" + (new DecimalFormat("00").format(offsetHours)) + ":"
				+ (new DecimalFormat("00").format(offsetMinutes));
		return timeZone;
	}
	
	
	private static String createBase(String min, String max) {
		//Calendar URL with all parameters filled in
		return "https://www.googleapis.com/calendar/v3/calendars/" +
			cal_id + "/events?timeMin=" + min + "&timeMax=" + max +
			"&max-results=100&singleEvents=true&orderBy=startTime&key=" + myApiKey;
	}
	
	
}