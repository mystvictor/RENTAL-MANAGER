package com.myves.score.rentalmanager.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by myves.stvictor on 2015-07-15.
 */
public class ApplicationContract {
    public final static SimpleDateFormat SDF_DATE_AND_TIME = new SimpleDateFormat("dd MMM yyyy HH:mm");
    public final static SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd-MM-yyyy");
    public final static SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm");

    public static String convertDateToString(Date value){
        return SDF_DATE.format(value);
    }

    public static Date convertStringToDate(String value){
        Date date = null;

        try{
            date = SDF_DATE.parse(value);
        }catch (ParseException e){
            e.printStackTrace();
        }

        return date;
    }
}
