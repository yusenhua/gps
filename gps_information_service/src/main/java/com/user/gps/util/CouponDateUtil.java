package com.user.gps.util;

import java.util.Calendar;
import java.util.Date;

public class CouponDateUtil {
	
	public static Date getDateAfter2359Date(Date date, int duration) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, duration);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);		
		return cal.getTime();
	}

}
