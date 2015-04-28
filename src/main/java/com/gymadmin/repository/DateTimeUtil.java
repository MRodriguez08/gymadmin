package com.gymadmin.repository;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	private DateTimeUtil(){}

	public static Date addDays(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the days
        return cal.getTime();
    }
	
}
