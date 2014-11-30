package org.pigne;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;


import org.junit.BeforeClass;
import org.junit.Test;
import org.pigne.DateUtils.DateUtilsParseException;

public class DateUtilsTests {

	String format1 = "dd/MM/yyyy HH:mm:ss";
	String format2 = "yyyy-MM-dd HH:mm";
	
	String from1 = "30/11/2014 11:00:00";
	String from2 = "2014-11-30 11:00";
	
	String to1 = "01/12/2014 12:30:00";
	String to2 = "2014-12-01 12:30";
	
	static Date beforeNow;
	static Date afterNow;
	static Date now;
	
	@BeforeClass
	public static void init(){
		Calendar cal = Calendar.getInstance();
		now = cal.getTime();
		cal.add(Calendar.HOUR, -1);
		beforeNow = cal.getTime();
		cal.add(Calendar.HOUR, 2);
		cal.add(Calendar.MINUTE, 1);
		cal.add(Calendar.SECOND, 2);
		afterNow = cal.getTime();
	}
	
	@Test
	public void testPositiveDifferenceBetweenDateObjects(){
		DateUtils.DHMS diff = DateUtils.difference(beforeNow, now);
		assertEquals(0, diff.getDays());
		assertEquals(1, diff.getHours());
		assertEquals(0, diff.getMinutes());
		assertEquals(0, diff.getSeconds());	
	}
	
	@Test
	public void testNegativeDifferenceBetweenDateObjects(){
		DateUtils.DHMS diff = DateUtils.difference(afterNow, now);
		assertEquals(0, diff.getDays());
		assertEquals(-1, diff.getHours());
		assertEquals(-1, diff.getMinutes());
		assertEquals(-2, diff.getSeconds());	
	}

	@Test
	public void testToStringDHMS(){
		DateUtils.DHMS diff = DateUtils.difference(beforeNow, now);
		assertEquals("1 hour", diff.toString());
		
		diff = DateUtils.difference(now, afterNow);
		assertEquals("1 hour, 1 minute, and 2 seconds", diff.toString());
		
	}
	
	@Test
	public void testDifferenceBetweenDateStringsWithFormat(){
		String diff = null;
		try {
			diff = DateUtils.difference(format1, from1, to1);
		} catch(DateUtilsParseException e){
			fail();
		}
		assertEquals("1 day, 1 hour, and 30 minutes", diff);
		
		try {
			diff = DateUtils.difference(format2, from2, to2);
		} catch(DateUtilsParseException e){
			fail();
		}
		assertEquals("1 day, 1 hour, and 30 minutes", diff);	
	}
}
