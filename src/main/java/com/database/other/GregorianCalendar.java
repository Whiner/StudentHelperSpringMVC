package com.database.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GregorianCalendar extends java.util.GregorianCalendar {

    public GregorianCalendar(){
        super();
    }

    public GregorianCalendar(long millis){
        super.setTime(new Date(millis));
    }

    public GregorianCalendar(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy г");
        return dateFormat.format(getTime());
    }

    public void fromString(String string, SimpleDateFormat dateFormat) throws ParseException {
        Date date = dateFormat.parse(string);
        super.setTime(date);
    }

}
