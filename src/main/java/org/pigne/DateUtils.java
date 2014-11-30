package org.pigne;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * Structure that handles time differences in Days hours minutes and seconds
	 */
	public static class DHMS {
		int days;
		int hours;
		int minutes;
		int seconds;

		public DHMS(int days, int hours, int minutes, int seconds) {
			this.days = days;
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
		}

		private void appendToBuffer(StringBuilder b, int q, String unit) {
			b.append(q);
		}

		@Override
		public String toString() {
			StringBuilder sb;
			ArrayList<String> strings = new ArrayList<String>();

			if (days != 0) {
				sb = new StringBuilder();
				strings.add(sb.append(days).append(" day")
						.append(days > 1 ? "s" : "").toString());
			}
			if (hours != 0) {

				sb = new StringBuilder();
				strings.add(sb.append(hours).append(" hour")
						.append(hours > 1 ? "s" : "").toString());
			}
			if (minutes != 0) {
				sb = new StringBuilder();
				strings.add(sb.append(minutes).append(" minute")
						.append(minutes > 1 ? "s" : "").toString());
			}
			if (seconds != 0) {
				sb = new StringBuilder();
				strings.add(sb.append(seconds).append(" second")
						.append(seconds > 1 ? "s" : "").toString());
			}
			sb = new StringBuilder();
			for (int i = 0; i < strings.size(); i++) {
				if (i != 0 && i + 1 == strings.size()) {
					sb.append("and ");
				}
				sb.append(strings.get(i).toString());
				if (i < strings.size() - 1) {
					sb.append(", ");
				}
			}
			return sb.toString();
		}

		public int getDays() {
			return days;
		}

		public void setDays(int days) {
			this.days = days;
		}

		public int getHours() {
			return hours;
		}

		public void setHours(int hours) {
			this.hours = hours;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}

		public int getSeconds() {
			return seconds;
		}

		public void setSeconds(int seconds) {
			this.seconds = seconds;
		}
	}

	/**
	 * Difference in days Hours Minutes and seconds
	 */
	public static DHMS difference(Date from, Date to) {

		long diff = to.getTime() - from.getTime();

		return new DateUtils.DHMS((int) (diff / (24 * 60 * 60 * 1000)),
				(int) (diff / (60 * 60 * 1000) % 24),
				(int) (diff / (60 * 1000) % 60), (int) (diff / 1000 % 60));
	}

	public static Date dateFromString(String pattern, String theDate)
			throws DateUtils.DateUtilsParseException {
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(theDate);
		} catch (ParseException e) {
			throw new DateUtils.DateUtilsParseException(e.getMessage(),
					e.getErrorOffset());
		}
		return d;
	}

	public static String difference(String pattern, String from, String to)
			throws DateUtils.DateUtilsParseException {
		Date d1 = DateUtils.dateFromString(pattern, from);
		Date d2 = DateUtils.dateFromString(pattern, to);
		return difference(d1, d2).toString();
	}

	public static String differenceToNow(String pattern, String from)
			throws DateUtils.DateUtilsParseException {
		return difference(DateUtils.dateFromString(pattern, from),
				Calendar.getInstance().getTime()).toString();
	}

	public static DHMS differenceToNow(Date from) {
		return difference(from, Calendar.getInstance().getTime());
	}

	public static String differenceFromNow(String pattern, String to)
			throws DateUtils.DateUtilsParseException {
		return difference(Calendar.getInstance().getTime(), DateUtils.dateFromString(pattern, to)).toString();
	}

	public static DHMS differenceFromNow(Date to) {
		return difference(Calendar.getInstance().getTime(), to);
	}

	public static class DateUtilsParseException extends ParseException {
		public DateUtilsParseException(String s, int errorOffset) {
			super(s, errorOffset);
		}
	}
}
