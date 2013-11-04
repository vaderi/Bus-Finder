package com.example.androidbusfinder;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity 
{	
	ArrayList<Stop> locations = new ArrayList<Stop>();
	Destination closestDestination;
	
	Intent intent;
	
	private static final String TAG =  "Transition"; 
	
	private View.OnClickListener onClick = new View.OnClickListener() 
	{	
		public void onClick(View v) 
		{
			Log.d(TAG, String.valueOf((char)v.getId()));
			
			try 
			{
				closestDestination = new Destination(String.valueOf(R.id.time_input), String.valueOf(R.id.LocationInput));
				getClosestStop( closestDestination );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			intent.putExtra("Location", closestDestination.location);
			intent.putExtra("Time", closestDestination.time);
			startActivity(intent);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intent = new Intent(this, MapHolderActivity.class);
		
		setupData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setupData() 
	{
		//This sets up the auto-completing text box in the xml
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_location);
		String[] stops = getResources().getStringArray(R.array.locations_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops);
		textView.setAdapter(adapter);
		
		//This sets up the Layout and the Button which will take the user to GoogleMaps
		LinearLayout question = (LinearLayout) this.findViewById(R.id.TimeInput);
		
		Button button = new Button(this.getApplicationContext());
		button.setText("Enter");
		button.setId('E');
		button.setOnClickListener(onClick);
		question.addView(button);
		
		
		//this loads the JSON data from the database
		try {
			getData();
		} catch (ParseException e) 
		{
			System.out.printf("Failed to load Data");
			e.printStackTrace();
		} catch (JSONException e) 
		{
			System.out.printf("Failed to load Data");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.printf("Failed to load Data");
			e.printStackTrace();
		}
	}
	
	//This calculates the earliest bus going to the destination
	public void getClosestStop( Destination newDestination ) throws ParseException
	{
		//These variables let me parse the time strings into variables that I can do math on
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.US);
		Calendar tempCurrentStopTime = Calendar.getInstance(Locale.US);
		Calendar tempLowestStopTime = Calendar.getInstance(Locale.US);
		Calendar inputTime = Calendar.getInstance(Locale.US);
		
		Date tempTime = sdf.parse("00:00:00");
		tempLowestStopTime.setTime(tempTime);
		inputTime.setTime(sdf.parse(newDestination.time));
		
		//In the Full version this should be corrected, it will do for now
		for( int i = 1; i < locations.size(); ++i )
		{
			if( locations.get(i).stopName == newDestination.location )
			{
				tempCurrentStopTime.setTime((sdf.parse(locations.get(i - 1).stopTime)));
				if( 	tempCurrentStopTime.getTimeInMillis() - tempLowestStopTime.getTimeInMillis() > 0 &&  
						tempCurrentStopTime.getTimeInMillis() - inputTime.getTimeInMillis() < 0 )
				{
					newDestination.location = locations.get(i - 1).stopName;
					tempLowestStopTime.setTime(sdf.parse(locations.get(i - 1).stopTime));
					newDestination.time = "" + locations.get(i - 1).stopTime;
				}
			}
		}
	}
	
	
	
	public void getData() throws ParseException, JSONException, IOException
	{
		URL url1 = null;
		new AsyncDataGrab().execute(url1);
	}
}