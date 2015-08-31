/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bj58.sfft.json.jsontools.mapper.helper.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bj58.sfft.json.jsontools.mapper.MapperException;
import com.bj58.sfft.json.jsontools.model.JSONString;
import com.bj58.sfft.json.jsontools.model.JSONValue;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class DateMapper extends AbstractMapper {
	private static boolean timeZoneIgnored = true;

	public static boolean isTimeZoneIgnored() {
		return timeZoneIgnored;
	}

	public static void setTimeZoneIgnored(boolean timeZoneIgnored) {
		timeZoneIgnored = timeZoneIgnored;
	}

	@Override
	public Class getHelpedClass() {
		return Date.class;
	}

	@Override
	public JSONValue toJSON(Object aPojo) throws MapperException {
		return new JSONString(toDotNetDate((Date) aPojo));
	}

	@Override
	public Object toJava(JSONValue aValue, Class aRequestedClass) throws MapperException {
		if (!aValue.isString()) {
			throw new MapperException("DateMapper cannot map class: " + aValue.getClass().getName());
		}

		return fromDotNetDate(((JSONString) aValue).getValue().trim());
	}

	public static String toDotNetDate(Date date) {
		if (date == null) {
			date = new Date();
		}
		return "\\/Date(" + date.getTime() + "+0000)\\/";
	}

	public static Date fromDotNetDate(String timestampString) {
		timestampString = timestampString.substring(timestampString.indexOf("(") + 1, timestampString.indexOf("+"));
		return new Date(Long.parseLong(timestampString));
	}

	public static String toRFC3339(Date date) {
		return toRFC3339(date, true);
	}

	public static String toRFC3339(Date date, boolean timezoneIgnored) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dateFormat.setLenient(false);

		String dateString = dateFormat.format(date);
		int length = dateString.length();
		if (timezoneIgnored) {
			dateString = dateString.substring(0, length - 5);
		} else {
			dateString = dateString.substring(0, length - 2) + ":" + dateString.substring(length - 2);
		}
		return dateString;
	}

	public static Date fromISO8601(String timestampString) throws MapperException {
		return fromISO8601(timestampString, true);
	}

	public static Date fromISO8601(String timestampString, boolean timezoneIgnored) throws MapperException {
		if ((timestampString == null) || (timestampString.trim().length() < 1)) {
			throw new MapperException("time stamp string can't be empty.");
		}
		timestampString = timestampString.trim();

		GregorianCalendar calendar = new GregorianCalendar();
		String separator = " ";
		if (timestampString.indexOf("T") != -1) {
			separator = "T";
		}
		String[] dateAndTime = timestampString.split(separator);
		String dateString = dateAndTime[0];
		String timeString = null;
		if (dateAndTime.length > 1) {
			timeString = dateAndTime[1];
		}

		Pattern timePattern = Pattern
				.compile("^(\\d{4})((-?(\\d{2})(-?(\\d{2}))?)|(-?(\\d{3}))|(-?W(\\d{2})(-?([1-7]))?))?$");
		Matcher timeMatcher = timePattern.matcher(dateString);
		if (timeMatcher.find()) {
			int year = Integer.parseInt(timeMatcher.group(1));
			int month = timeMatcher.group(4) == null ? 0 : Integer.parseInt(timeMatcher.group(4));
			int dayOfMonth = timeMatcher.group(6) == null ? 1 : Integer.parseInt(timeMatcher.group(6));
			int dayOfYear = timeMatcher.group(8) == null ? -1 : Integer.parseInt(timeMatcher.group(8));
			int week = timeMatcher.group(10) == null ? -1 : Integer.parseInt(timeMatcher.group(10));
			int dayOfWeek = timeMatcher.group(12) == null ? 2 : Integer.parseInt(timeMatcher.group(12)) + 1;
			if (dayOfWeek == 8) {
				dayOfWeek = 1;
				week++;
			}
			calendar.set(1, year);
			if (week != -1) {
				calendar.set(3, week);
				calendar.set(7, dayOfWeek);
			} else if (dayOfYear != -1) {
				calendar.set(6, dayOfYear);
			} else {
				calendar.set(2, month - 1);
				calendar.set(5, dayOfMonth);
			}
		} else {
			throw new MapperException("invalid date string:" + dateString);
		}

		int hour = 0;
		int minute = 0;
		int second = 0;
		int milliSecond = 0;
		String timezoneString = null;
		String localTimeString = timeString;
		if (timeString != null) {
			Pattern timezonePattern = Pattern.compile("(([-+])(\\d{2})(:?(\\d{2}))?)$");
			Matcher timezoneMatcher = timezonePattern.matcher(timeString);
			if (timezoneMatcher.find()) {
				timezoneString = timezoneMatcher.group(0);
				localTimeString = timeString.substring(0, timeString.length() - timezoneString.length());
			}
			Pattern localTimePattern = Pattern.compile("^(\\d{2})(:?(\\d{2})(:?(\\d{2})(.(\\d+))?)?)?$");
			Matcher localTimeMatcher = localTimePattern.matcher(localTimeString);
			if (localTimeMatcher.find()) {
				if (localTimeMatcher.group(1) != null) {
					hour = Integer.parseInt(localTimeMatcher.group(1));
				}
				if (localTimeMatcher.group(3) != null) {
					minute = Integer.parseInt(localTimeMatcher.group(3));
				}
				if (localTimeMatcher.group(5) != null) {
					second = Integer.parseInt(localTimeMatcher.group(5));
				}
				if (localTimeMatcher.group(7) != null) {
					milliSecond = (int) (Float.parseFloat("0." + localTimeMatcher.group(7)) * 1000.0F);
				}
			}
		}
		calendar.set(11, hour);
		calendar.set(12, minute);
		calendar.set(13, second);
		calendar.set(14, milliSecond);

		if ((timezoneString != null) && (!timezoneIgnored)) {
			TimeZone timeZone = TimeZone.getTimeZone("GMT" + timezoneString);
			calendar.setTimeZone(timeZone);
			return calendar.getTime();
		}

		return calendar.getTime();
	}
}