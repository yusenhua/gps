/**
 * 
 */
package com.user.gps.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yusenhua
 *
 */
public class DateUtil {

    public static final String SIMPLE_DATE_FORMAT="yyyy-MM-dd";
    
    public static final String HHMM_DATE_FORMAT="yyyy-MM-dd HH:mm";
    
    public static final String HHMMSS_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    
    public static final String START_TIME=" 00:00:00";
    
    public static final String END_TIME=" 23:59:59";
    
    public static final  SimpleDateFormat format1 = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
    
    public static final  SimpleDateFormat format2 = new SimpleDateFormat(HHMMSS_DATE_FORMAT);
    
    public static String SimpleFormatDateToString(Date date){
        if(date == null)
            date = new Date();       
        return format1.format(date);
    }
    
    public static Date toDate(String date){
       try {
        return format2.parse(date);
    } catch (ParseException e) {      
        e.printStackTrace();
        throw new RuntimeException("日期格式不正确 ");
    }
    }
   
    /**
     * 格式时间
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(Date date, String format) {
        if (date != null) {
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(date);
        } else {
            return null;
        }
    }
    
    public static Date toDate(Long mseconds){
        try {
         return new Date(mseconds);
     } catch (Exception e) {      
         e.printStackTrace();
         throw new RuntimeException("日期格式不正确 ");
     }
     }
	
    
    public static String AllFormatDateToString(Date date){
        if(date == null)
            date = new Date();       
        return format2.format(date);
    }
}
