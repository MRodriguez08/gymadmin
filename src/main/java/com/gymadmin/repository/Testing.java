package com.gymadmin.repository;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTimeUtils;

public class Testing {
	
	private static final Logger logger = Logger.getLogger(Testing.class);

	public static void main(String[] args) {
		
		Date now = new Date();
		Date future = DateTimeUtil.addDays(now, 1);
		logger.info("Diff = " + DateTimeUtil.getRemainingDays(future, now));

	}

}
