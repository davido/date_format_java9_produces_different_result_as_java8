package org.ostrovsky.date;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateFormatTest {
  private TimeZone systemTimeZone;

  @Before
  public void setUp() {
    systemTimeZone = TimeZone.getDefault();
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @After
  public void tearDown() {
    TimeZone.setDefault(systemTimeZone);
  }

  @Test
  public void dateFormatResultInJava9DiffersFromJava8() {
    DateFormat enUsFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US);
    assertEquals("Jan 1, 1970 12:00:00 AM", enUsFormat.format(new Date(0)));
  }
}
