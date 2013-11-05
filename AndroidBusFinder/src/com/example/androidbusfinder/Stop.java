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
	Calendar tempCalanderStopTime;
	Calendar tempComparisonTime;
	Calendar extra;
	
	public Stop( int id, String stop, String time, String days, String[] strings ) throws ParseException
	{
		storeID = id;
		stopName = stop;
		stopTime = time;
		daysActive = days;
		
		//convert to Date format use -h\
		// TODO http://www.tutorialspoint.com/java/java_date_time.htm
		tempCalanderStopTime = new Calendar();
		tempComparisonTime = new Calendar();
		extra = new Calendar();
		
		System.out.print(storeID);
		
		if( daysActive.indexOf(",") != -1 )
		{
			daysActive = daysActive.replaceAll(",", " ");
		}
		
		Date tempStopTime = new Date();
		tempStopTime = sdf.parse("13:00:00");
		tempComparisonTime.setTime(tempStopTime);
		tempStopTime = sdf.parse("01:00:00");
		extra.setTime(tempStopTime);
		tempStopTime = sdf.parse(stopTime);
		tempCalanderStopTime.setTime(tempStopTime);
		
		if( tempCalanderStopTime.getTimeInMillis() - tempComparisonTime.getTimeInMillis() > 0 )
		{
			tempCalanderStopTime.add(Calendar.HOUR, -adjustment);
			stopTime = "" + tempStopTime;
			System.out.printf(stopTime);
		}
		else if( tempCalanderStopTime.getTimeInMillis() - extra.getTimeInMillis() < 0 )
		{
			tempCalanderStopTime.add(Calendar.HOUR, adjustment);
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
