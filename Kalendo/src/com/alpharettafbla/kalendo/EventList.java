package com.alpharettafbla.kalendo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventList {
	
	
	public static ArrayList<EventInfo> analyzeData(JSONObject jsonObj) {
		ArrayList<EventInfo> listOfEventInfo = new ArrayList<EventInfo>();
		try {			
			//Get the array of events
			JSONArray jArray = jsonObj.getJSONArray("items");
			
			//Get the name, description, start time, and end time for each event
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject jObject = jArray.getJSONObject(i);
				listOfEventInfo.add(new EventInfo(jObject));
			}				
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listOfEventInfo;
	}
	
	
}
