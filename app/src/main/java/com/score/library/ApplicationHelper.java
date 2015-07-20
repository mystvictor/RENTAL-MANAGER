package com.score.library;

import android.widget.Toast;

import com.score.models.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by myves.stvictor on 2015-07-06.
 */
public class ApplicationHelper {
    /**
     * This function allows to check if a STRING IS NULL EMPTY OR WHITE SPACE.
     * It takes a String as parameter and return TRUE OR FALSE.
     * @param value
     * @return boolean
     */
    public static boolean isStringNullOrWhiteSpace(String value){
        boolean isNullOrWhiteSpace = false;

        if(value == null || value.isEmpty() || value == " "){
            isNullOrWhiteSpace = true;
        }

        return isNullOrWhiteSpace;
    }

    /**
     * This function allows to verify if a valid EMAIL ADDRESS was provided.
     * It takes a String as parameter and return TRUE OR FALSE.
     * @param value
     * @return boolean
     */
    public static boolean isEmailValid(String value){
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    /**
     * This function allows to verify if a valid date was provided.
     * It takes a String as parameter and returns TRUE OR FALSE.
     * @param value
     * @return boolean
     */
    public static boolean isDateValid(String value){
        if(isStringNullOrWhiteSpace(value)){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(value);
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
