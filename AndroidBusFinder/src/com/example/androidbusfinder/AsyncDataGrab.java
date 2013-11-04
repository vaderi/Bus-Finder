package com.example.androidbusfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class AsyncDataGrab extends AsyncTask<URL, Integer, ArrayList<Stop>>
{
	@Override
	protected ArrayList<Stop> doInBackground(URL... params) 
	{
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		
		StringBuilder jsonBuilder;
		String line;
		String json;
		
		ArrayList<Stop> locations = new ArrayList<Stop>();
		
		try 
		{
			url = new URL("http://www.dumud.net/~slane/vasily/?data=true");
			conn = (HttpURLConnection) url.openConnection();
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			jsonBuilder = new StringBuilder();
			
			while((line = rd.readLine()) != null) 
			{
				jsonBuilder.append(line); 
				json = jsonBuilder.toString(); 
				
				JSONObject jsonObj = new JSONObject(json); 
				JSONArray jsonLocations = jsonObj.getJSONArray("champlain");
				
				for (int i = 0; i < jsonLocations.length()-1; ++i) 
				{
					JSONObject location = jsonLocations.getJSONObject(i); 
					
					int id = location.getInt("id"); 
					String stop = location.getString("location"); 
					String time = location.getString("time"); 
					String days = location.getString("days"); 
					
					locations.add( new Stop(id, stop, time, days, new String[]{}) ); 
				}
			}
		} 
		catch (IOException e) 
		{
			System.out.printf("Failed to create a Stop");
			e.printStackTrace();
		} 
		catch (JSONException e) 
		{
			System.out.printf("Failed to create a Stop");
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			System.out.printf("Failed to create a Stop");
			e.printStackTrace();
		}
		
		return locations;
	}

	protected void onPostExecute(ArrayList<Stop> photoList)
    {
        
    }
}
