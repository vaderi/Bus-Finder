package com.example.androidbusfinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Stop 
{
	int storeID;
	String stopName;
	String stopTime;
	String daysActive;
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.US);
	Calendar tempCalanderStopTime = null;
	Calendar tempComparisonTime = null;
	Calendar extra = null;
	
	public Stop( int id, String stop, String time, String days, String[] strings ) throws ParseException
	{
		storeID = id;
		stopName = stop;
		stopTime = time;
		daysActive = days;
		
//		if( daysActive.indexOf(",") != -1 )
//		{
//			daysActive = daysActive.replaceAll(",", " ");
//		}
//		
//		java.util.Date tempStopTime;
//		tempStopTime = sdf.parse("13:00:00");
//		tempComparisonTime.setTime(tempStopTime);
//		tempStopTime = sdf.parse("01:00:00");
//		extra.setTime(tempStopTime);
//		tempStopTime = sdf.parse(stopTime);
//		tempCalanderStopTime.setTime(tempStopTime);
//		
//		if( tempCalanderStopTime.getTimeInMillis() - tempComparisonTime.getTimeInMillis() > 0 )
//		{
//			tempCalanderStopTime.add(Calendar.HOUR, -12);
//			stopTime = "" + tempStopTime + " PM";
//		}
//		else if( tempCalanderStopTime.getTimeInMillis() - extra.getTimeInMillis() < 0 )
//		{
//			tempCalanderStopTime.add(Calendar.HOUR, 12);
//			stopTime = "" + tempStopTime + " AM";
//		}
//		else
//		{
//			stopTime = "" + tempStopTime + " AM";
//		}
	}
}
