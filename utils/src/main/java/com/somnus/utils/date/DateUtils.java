package com.somnus.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final String[] DATE_TIME_FORMAT = { "yyyy-MM-dd", "hh:mm",
		"hh:mm:ss", "yyyy-MM-dd hh:mm", "yyyy-MM-dd hh:mm:ss", };

public static String parseToStr(Date date) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return sdf.format(date);
}

public static Date parseToDate(String dateStr) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
		return sdf.parse(dateStr);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

	public static Timestamp currentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp parseToTimestampe(Date occurredOn) {
		return new Timestamp(occurredOn.getTime());
	}

}
