package com.dimimport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) throws ParseException {
        String isoDateTime = "2013-04-25T11:15:08+00:00";
        ZonedDateTime fromIsoDate = ZonedDateTime.parse(isoDateTime);

        TimeZone tz = TimeZone.getTimeZone("Europe/Moscow");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        int off = tz.getOffset(dateFormat.parse("2013-04-25T11:15:08").getTime());
        ZoneOffset offset = ZoneOffset.ofHours(off / 1000 / 60 / 60);

        ZonedDateTime localDateTime = fromIsoDate.withZoneSameInstant(offset);

        System.out.println("Input:  " + fromIsoDate);
        System.out.println("Output: " + localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}

