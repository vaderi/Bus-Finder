package com.example.busfinder;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	LocationManager localManager =  (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	
	LocationListener localListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		public void onProviderEnabled(String provider){
			
		}
		
		public void onProviderDisabled(String provider){
			
		}
	};
	
	public void main(String[] args) {
		localManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,localListener);
	}
	
}
