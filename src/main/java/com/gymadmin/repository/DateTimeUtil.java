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
	
	public static Date getNextDueDate(Integer monthsCount, Integer dueDateMonthDay)
    {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        cal.add(Calendar.MONTH, monthsCount);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), dueDateMonthDay);
        return cal.getTime();
    }
	
}
