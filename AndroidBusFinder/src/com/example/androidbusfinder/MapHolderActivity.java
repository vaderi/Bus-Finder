package com.example.androidbusfinder;

/*import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class MapHolderActivity extends Activity
{
	/*static final LatLng CHAMPLAIN = new LatLng( 0, 0 );
	static final LatLng SPINNER = new LatLng( 0, 0 );
	static final LatLng QUARRY = new LatLng( 0, 0 );
	private GoogleMap map;*/
	
	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_holder);
        
        Intent intent = getIntent();
        
        String location = intent.getStringExtra("Location");
        String time = intent.getStringExtra("Time");
        
        System.out.printf(location);
        System.out.printf(time);
        
        /*map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        if( map == null)
		{
			Marker Champlain = map.addMarker( new MarkerOptions().position(CHAMPLAIN) );
			Marker Spinner = map.addMarker( new MarkerOptions().position(SPINNER) );
			Marker QuarryHill = map.addMarker( new MarkerOptions().position(QUARRY) );
		}*/
	}
}
