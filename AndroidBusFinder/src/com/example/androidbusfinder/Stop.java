package com.example.androidbusfinder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;

public class Stop 
{
	int storeID;
	int adjustment = 12;
	String stopName;
	String stopTime;
	String daysActive;
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss", Locale.US);
	Date tempCalanderStopTime;
	Date tempComparisonTime;
	Date extra;
	
	public Stop( int id, String stop, String time, String days, String[] strings ) throws ParseException
	{
		storeID = id;
		stopName = stop;
		stopTime = time;
		daysActive = days;
		
		//convert to Date format use -h\
		// TODO http://www.tutorialspoint.com/java/java_date_time.htm
		tempCalanderStopTime = new Date();
		tempComparisonTime = new Date();
		extra = new Date();
		
		if( daysActive.indexOf(",") != -1 )
		{
			daysActive = daysActive.replaceAll(",", " ");
		}
		
		Date tempStopTime = new Date();
		tempStopTime = sdf.parse("13:00:00");
		tempComparisonTime = sdf.parse("13:00:00");
		tempStopTime = sdf.parse("01:00:00");
		extra = sdf.parse("01:00:00");
		tempStopTime = sdf.parse(stopTime);
		tempCalanderStopTime = sdf.parse(stopTime);
		
		if( tempCalanderStopTime.getTime() - tempComparisonTime.getTime() > 0 )
		{
			//tempCalanderStopTime.add(Calendar.HOUR, -adjustment);
			stopTime = "" + tempStopTime;
			System.out.printf(stopTime);
		}
		else if( tempCalanderStopTime.getTime() - extra.getTime() < 0 )
		{
			//tempCalanderStopTime.add(Calendar.HOUR, adjustment);
			stopTime = "" + tempStopTime;
			System.out.printf(stopTime);
		}
		else
		{
			stopTime = "" + tempStopTime;
			System.out.printf(stopTime);
		}
	}
}
