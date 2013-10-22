package com.example.androidbusfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MapHolderActivity extends Activity
{
	private GoogleMap map;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_holder);
        
        Intent intent = getIntent();
        
        String location = intent.getStringExtra("Location");
        String time = intent.getStringExtra("Time");
        
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	}
}
