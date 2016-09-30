package com.alpharettafbla.kalendo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Spinner;
import com.alpharettafbla.kalendo.R;

public class TwitterFeed extends Activity {
    
	public static final String TAG = "TwitterFeed";	
	private static final String baseURl = "https://twitter.com";	
	private static String twitterAcc = "AHSRaiderFBLA";
	private Spinner spinner1;

        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_feed);
        
        //Sets up the twitter feed drop-down menu
    	addListenerOnButton();
    	
    	//Sets spinner to the saved state
        spinnerSavedState();  
        
        //Displays the twitter feed
        initializeTwitter();      
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
    
	
	public void addListenerOnButton() { 
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		Button submitBtn = (Button) findViewById(R.id.submit_btn);
 
		//Waits for new twitter feed selection
		submitBtn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				WebView webView = (WebView) findViewById(R.id.timeline_webview);
				twitterAcc = String.valueOf(spinner1.getSelectedItem());
				webView.loadDataWithBaseURL(baseURl, BuildURL.twitterURL(twitterAcc),
						"text/html", "UTF-8", null);
			}
 
		});
	}
	
	
	public void spinnerSavedState() {
		//Sets spinner to the saved value
		spinner1 = (Spinner) findViewById(R.id.spinner1);
        if (twitterAcc.equals("AHSRaiderFBLA"))
        	spinner1.setSelection(0);
        else if (twitterAcc.equals("FultonCoSchools"))
        	spinner1.setSelection(1);
        else
        	spinner1.setSelection(2);
	}
	
    
    public void initializeTwitter() {
        //Must load background color for webView
        loadBackgroundColor();

        WebView webView = (WebView) findViewById(R.id.timeline_webview);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
       
        //Forces webView to be scrolling but not clickable
        //No links can be chosen but more tweets can be loaded
        webView.setLongClickable(false);
        webView.setOnTouchListener(new View.OnTouchListener()
        {
          @Override
          public boolean onTouch(View arg0, MotionEvent arg1)
          {
        	  return arg1.getAction() == MotionEvent.ACTION_UP;
          }
        }
        );
        
        //Loads twitter feed
        webView.loadDataWithBaseURL(baseURl, BuildURL.twitterURL(twitterAcc), "text/html", "UTF-8", null);
    }

	
	private void loadBackgroundColor() {
		WebView webView = (WebView) findViewById(R.id.timeline_webview);
		//Sets webView to be transparent
		webView.setBackgroundColor(0);
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
	
	
	public void toCal(View view) {
		//Opens the calendar view
		Intent i = new Intent(this, CalActivity.class);
		startActivity(i);
		finish();
	}


}
