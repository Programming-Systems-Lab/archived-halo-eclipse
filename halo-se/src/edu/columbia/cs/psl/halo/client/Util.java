package edu.columbia.cs.psl.halo.client;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.XMLGregorianCalendar;

public class Util {
	public static String formatDate(XMLGregorianCalendar d)
	{
		Date da = d.toGregorianCalendar().getTime();
		
		return DateFormat.getDateInstance().format(da);
	}
	public static int daysTillDate(XMLGregorianCalendar d)
	{
		long time_til = (d.toGregorianCalendar().getTimeInMillis() - System.currentTimeMillis())/1000;
		return (int) (time_til/DAY_SECS);
	}
	public static String getDueStrHuman(XMLGregorianCalendar dueDate) {
		if(dueDate != null)
		{
			boolean past = false;
			long time_til = (dueDate.toGregorianCalendar().getTimeInMillis() - System.currentTimeMillis())/1000;
			String ret = "";
			ArrayList<String> times = new ArrayList<String>();
			if(time_til < 0)
			{
				time_til *= -1;
				past = true;
			}
			if(time_til > MONTH_SECS)
			{
				int months = (int) (time_til/MONTH_SECS);
				time_til -= months * MONTH_SECS;
				times.add(months + " month" + (months > 1 ? "s" : ""));
			}
			if(time_til > WEEK_SECS)
			{
				int weeks = (int) (time_til/WEEK_SECS);
				time_til -= weeks * WEEK_SECS;
				times.add(weeks + " week" + (weeks > 1 ? "s" : ""));
			}
			if(time_til > DAY_SECS)
			{
				int days = (int) (time_til/DAY_SECS);
				time_til -= days * DAY_SECS;
				times.add(days + " day" + (days > 1 ? "s" : ""));
			}
			if(time_til > HOUR_SECS && times.size() == 0)
			{
				double hours = Math.round( 10 * ( (double) time_til/ (double) DAY_SECS))/10d;
				times.add(hours + " hour" + (hours != 1 ? "s" : ""));
			}
			for(int i = 0; i<times.size();i++)
			{
				ret += times.get(i);
				if(i < times.size() - 2)
					ret +=", ";
				else if(i == times.size() - 2)
				ret +=" and ";
			}
			if(past)
				ret += "ago";
			else
				ret = "in " + ret;
			return ret;
		}
		return "N/A";
	}
	private static final long MONTH_SECS = 2629743;
	private static final long WEEK_SECS = 604800;
	private static final long DAY_SECS = 86400;
	private static final long HOUR_SECS = 3600;

}
