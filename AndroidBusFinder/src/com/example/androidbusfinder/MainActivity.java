package com.example.androidbusfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class MainActivity extends Activity 
{	
	ArrayList<Stop> locations = new ArrayList<Stop>();
	String closestStopLocation;
	String closestStopTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void main(String[] args) 
	{
		//This sets up the auto-completing text box in the xml
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_location);
		String[] stops = getResources().getStringArray(R.array.locations_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops);
		textView.setAdapter(adapter);
		
		//this loads the JSON data from the database
		try {
			getData();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This calculates the earliest bus going to the destination
	public void getClosestStop( String destination, String time ) throws ParseException
	{
		//These variables let me parse the time strings into variables that I can do math on
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.US);
		Calendar tempCurrentStopTime = null;
		Calendar tempLowestStopTime = null;
		Calendar inputTime = null;
		
		Date tempTime = sdf.parse("00:00:00");
		tempLowestStopTime.setTime(tempTime);
		inputTime.setTime(sdf.parse(time));
		
		for( int i = 0; i < locations.size(); ++i )
		{
			if( locations.get(i).stopName == destination )
			{
				tempCurrentStopTime.setTime((sdf.parse(locations.get(i - 1).stopTime)));
				if( 	tempCurrentStopTime.getTimeInMillis() - tempLowestStopTime.getTimeInMillis() > 0 &&  
						tempCurrentStopTime.getTimeInMillis() - inputTime.getTimeInMillis() < 0 )
				{
					closestStopLocation = locations.get(i - 1).stopName;
					tempLowestStopTime.setTime(sdf.parse(locations.get(i - 1).stopTime));
					closestStopTime = "" + locations.get(i - 1).stopTime;
				}
			}
		}
		
		//setNodeValue( "bus_stop_name", closestStopLocation, nodes );
	}
	
	//Bruno, Eric. "Creating and Modifying XML in Java." Dr. Dobb's. N.p., 14 Mar. 2013. Web. 21 Oct. 2013.
	protected void setNodeValue(String tagName, String value, NodeList nodes) {
	    Node node = getNode(tagName, nodes);
	    if ( node == null )
	        return;
	 
	    // Locate the child text node and change its value
	    NodeList childNodes = node.getChildNodes();
	    for (int y = 0; y < childNodes.getLength(); y++ ) {
	        Node data = childNodes.item(y);
	        if ( data.getNodeType() == Node.TEXT_NODE ) {
	            data.setNodeValue(value);
	            return;
	        }
	    }
	}
	
	//Bruno, Eric. "Creating and Modifying XML in Java." Dr. Dobb's. N.p., 14 Mar. 2013. Web. 21 Oct. 2013.
	protected Node getNode(String tagName, NodeList nodes) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            return node;
	        }
	    }
	 
	    return null;
	}
	
	public void getData() throws ParseException, JSONException, IOException
	{
		URL url = new URL("http://www.dumud.net/~slane/vasily/?data=true");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		StringBuilder jsonBuilder = new StringBuilder(); 
		String line;
		String json;
		
		while((line = rd.readLine()) != null) 
		{
			jsonBuilder.append(line); 
			json = jsonBuilder.toString(); 
			
			JSONObject jsonObj = new JSONObject(json); 
			JSONArray jsonLocations = jsonObj.getJSONArray("champlain");
			
			for (int i = 0; i < jsonLocations.length(); ++i) 
			{
				JSONObject location = jsonLocations.getJSONObject(i); 
				int id = location.getInt("stop_id"); 
				String stop = location.getString("stop_name"); 
				String time = location.getString("stop_location"); 
				String days = location.getString("stop_days"); 
				
				locations.add( new Stop(id, stop, time, days, new String[]{}) ); 
			}
		}
	}
}