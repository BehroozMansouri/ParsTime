/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b.mansouri
 */
public class RefrenceTimeTools {
    
    public static String GetRefDay (String refDate, int dayCount) throws ParseException
    {
        String temp = CalendarConvetor.JalaliToGregorian(refDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(temp));
        c.add(Calendar.DATE, dayCount);
        temp = sdf.format(c.getTime());
        return CalendarConvetor.GregorianToJalali(temp);  // dt is now the new date
    }
    public static String GetRefMonth (String refDate, int monthCount) throws ParseException
    {
        int yearToCalculate = monthCount/12;
        refDate = GetRefYear(refDate, yearToCalculate);
        
        int month = Integer.valueOf(refDate.split("-")[1]);
        int year = Integer.valueOf(refDate.split("-")[0]);
        int day = Integer.valueOf(refDate.split("-")[2]);
        
        int remainingmonth = Math.abs(monthCount)%12;
        
        if(monthCount<0)
        {
            if(month-remainingmonth>0)
            {                
                month=month-remainingmonth;
            }
            else
            {
                month= 12-(remainingmonth-month);
                year = year - 1;
            }
        }
        else
        {
            if(month+remainingmonth<=12)
            {                
                month=month+remainingmonth;
            }
            else
            {
                month= (remainingmonth+month)%12;
                year = year + 1;
            }
        }
        if(day==31 && month>=7)
        {
            if(month==12 && year%4!=3)
                day=29;
            else
                day=30;
        }
        if(day==30 && month==12 && year%4!=3)
            day=29;
        return (year)+"-"+month+"-"+day;
    }
    public static String GetRefYear (String refDate, int yearCount)
    {
        int year = Integer.valueOf(refDate.split("-")[0])+yearCount;
        int month = Integer.valueOf(refDate.split("-")[1]);
        int day = Integer.valueOf(refDate.split("-")[2]);
        if(month==12 && day==30 && year%4!=3)
            day=29;
        return (year)+"-"+month+"-"+day;
    }
    
    public static int GetDay(String inputdate)
    {
        String temp = CalendarConvetor.JalaliToGregorian(inputdate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c= Calendar.getInstance();
        try {
            c.setTime(sdf.parse(temp));
        } catch (ParseException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        //int t = Calendar.SATURDAY   
        return c.get(Calendar.DAY_OF_WEEK);
    }
    
    public static String GetRefWeekDay (String refDate, int day, int weeks) throws ParseException
    {
        String temp = CalendarConvetor.JalaliToGregorian(refDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c= Calendar.getInstance();
        c.setTime(sdf.parse(temp));
                switch (day) {
            case 7:
                c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                break;
            case 1:
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                break;
            case 2:
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case 3:
                c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;
            case 4:
                c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;
            case 5:
                c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;
            case 6:
                c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;
            default:
                break;
        }

        c.add(Calendar.DAY_OF_MONTH, 7*weeks);
        temp = sdf.format(c.getTime());
        return CalendarConvetor.GregorianToJalali(temp);  // dt is now the new date
    }
    
}
