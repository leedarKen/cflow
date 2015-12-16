package com.csoft.resource.cflow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 *         to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public abstract class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    private static final String TIME_PATTERN = "HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAILT_DATE_PATTERN = "MM/dd/yyyy HH:mm:ss" ;

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return DEFAILT_DATE_PATTERN;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {

        if (aDate == null) {
            log.warn("aDate is null!");
            return null;
        }

        SimpleDateFormat  df = new SimpleDateFormat(aMask);
        return df.format(aDate);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }
    
    public static Date addDate(Date oldDate,final int days) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.DATE, days);
    	return calendar.getTime();
    }
    
    public static Boolean isWeekends(Date date){
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;

    }
    
    
    public static String formatDate(String pattern, Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAILT_DATE_PATTERN);
        return sdf.format(date);
    }

    /**
     * Calculates the date by specified timezone offset
     * @author jennings.wang
     * @param ori        Original date
     * @param offset     Timezone offset
     * @return
     */
    public static Date calTimeByTZOffset(Date ori, Integer offset){
    	if (ori==null){
    		return null;
    	}
    	//if offset is null or 0, return original value
    	if (offset==null || offset==0){
    		return ori;
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(ori);
    	calendar.add(Calendar.HOUR, offset);
    	return calendar.getTime();
    }

    public static Integer getYearMonth(Date date, final int monthOffSet) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthOffSet);
        
        Integer year = calendar.get(Calendar.YEAR) * 100;
        Integer month = calendar.get(Calendar.MONTH);
        return year + month;
    }
    
    public static long getdfday(String sdate, String ddate) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	long days = 0;
    	try	{
    	    Date d1 = df.parse(ddate);
    	    Date d2 = df.parse(sdate);
    	    long diff = d1.getTime() - d2.getTime();
    	    days = diff / (1000 * 60 * 60 * 24);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return days;
    }
    
    public static long getdfday(Date sdate, Date ddate) {
    	long days = 0;
    	try	{
    	    long diff = ddate.getTime() - sdate.getTime();
    	    days = diff / (1000 * 60 * 60 * 24);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return days;
    }
    
    public static String getWeek(Date date) {
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }
    /**
     * get timezoneOffset by selected timezone; 
     * if selectedTimezone is null or empty, the timezoneOffset is the currentUser's timezoneOffset
     * @param selectedTZ(Style:GMT+8:00)
     * @param userTzOffset
     * @return timezoneOffset
     */
    public static int getTimeOffsetBySelectedTZ(String selectedTZ, Integer userTzOffset) {
        
        //if selected timezone is null or '',timeoffset = tzoffset(rm_user table)
        if (selectedTZ == null || selectedTZ.trim().length() == 0) {
            return userTzOffset;
        }
        
        //get users selected time zone
        String selectedTZStr = selectedTZ.split(":")[0];
        TimeZone tz = TimeZone.getTimeZone(selectedTZStr);
        int useros = tz.getRawOffset();
        
        TimeZone default_tz = TimeZone.getDefault();//maybe server's tz      
        int serveros = default_tz.getRawOffset();
        
        //calculate
        int timeoffset ;
        if (serveros == useros) {
            timeoffset = 0;
        }
        else {
            int tmp = useros - serveros;
            timeoffset = tmp / 3600000;//hour
        }
        return timeoffset;
    }
    /**
     * change the original Date(type:String) to StandardTime(Server Time)
     * @param OriDateStr original Date(type:String;style:MM/dd/yyyy HH:mm:ss/MM/dd/yyyy)
     * @param tzOffset selectedTimzone/loginUserTimezone
     * @return Standard Time(type:Date)
     * @throws ParseException
     */
    public static Date toStandardTimeByselectedTZ(String OriDateStr, int tzOffset)
        throws ParseException {
        if (OriDateStr == null) {
            return null;
        }
        SimpleDateFormat sdf = null;
        if (OriDateStr.contains(" ")) {
            sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        }
        else {
            sdf = new SimpleDateFormat("MM/dd/yyyy");
        }
        Date OriDate = sdf.parse(OriDateStr);
        int opp_offset = -tzOffset;
        return calTimeByTZOffset(OriDate, opp_offset);
    }
    /**
     * change original Date(type:String) to UserTime(client Time/login Time)
     * @param oriDateStr(Type:String; Style:MM/dd/yyyy HH:mm:ss)
     * @param tzoffset selectedTimzone/loginUserTimezone
     * @return User Time (type:Date)
     * @throws ParseException
     */
    public static String toUserTimeBySelectedTZ(String oriDateStr, int tzoffset)
        throws ParseException {
        if (oriDateStr == null) {
            return null;
        }
        SimpleDateFormat sdf = null;
        String userTimeStr = null;
        if (oriDateStr.contains(" ")) {
            sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date oriDate = sdf.parse(oriDateStr);
            userTimeStr = formatDate("MM/dd/yyyy HH:mm:ss", calTimeByTZOffset(oriDate, tzoffset));
        }
        else {
            sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date oriDate = sdf.parse(oriDateStr);
            userTimeStr = formatDate("MM/dd/yyyy", calTimeByTZOffset(oriDate, tzoffset));
        }
        return userTimeStr;
    }
    
    public static void main(String[] args) {
        //System.out.println("standard time="+getYearMonth(new Date(), 2));
        try {
            //System.out.println("--->"+getdfday());
            String date = "7/13/2015";
            System.out.println("--->" + getWeek(convertStringToDate(date)));
            
            Date today = new Date();
            for (int i = 0; i < getdfday("2015-07-13", "2015-07-15") - 1; i++) {
                if (i == 0) {
                    today = convertStringToDate(date);
                }
                today = addDate(today, 1);
                System.out.println(formatDate("MM/dd/yyyy", today));
            }
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Date parse(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAILT_DATE_PATTERN);
            return sdf.parse(date) ;
        } catch (ParseException e) {
            e.printStackTrace();
            return null ;
        }
    }
}
