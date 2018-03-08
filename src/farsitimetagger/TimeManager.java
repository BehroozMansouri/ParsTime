/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Behrooz
 */
public class TimeManager extends TypeManger{
    
    public TimeManager() throws IOException
    {
    }
    @Override
    public String CallFunction(String refDate, String functionName, List<String> parameters)
    {
        if(null!=functionName)
            switch (functionName) {
            case "CompleteTime":
                return CompleteTime(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
            case "CompleteTimePart":
                return CompleteTimePart(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3),refDate);
            case "Hour":
                return Hour(parameters.get(0),refDate);
            case "JustTime":
                return JustTime(parameters.get(0),refDate);
            case "HourPartRelDay":
            {
                try {
                    return HourPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
            case "HourDayPart":
                return HourDayPart(parameters.get(0),parameters.get(1),refDate);  
            case "HourRelDay":
            {
                try {
                    return HourRelDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "TimeRelDay":
            {
                try {
                    return TimeRelDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }       
            case "TimeDayPart":
                return TimeDayPart(parameters.get(0),parameters.get(1),refDate);  
            case "TimeDayPartRelDay":
                return TimeDayPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);  
            case "HourDay":
                return HourDay(parameters.get(0),parameters.get(1),refDate);  
            
            default:    
                break;
        }
        return "";
    }

    private String CompleteTime(String hour, String min, String sec, String refDate) {
        return refDate+normalizeTime(hour,min,sec);
    }
    private String normalizeTime(String hour,String min,String sec, String milSec)
    {
        if(hour.length()==1)
            hour="0"+hour;
        if(min.length()==1)
            min="0"+min;
        if(sec.length()==1)
            sec="0"+sec;
        return "T"+hour+":"+min+":"+sec+":"+milSec;
    }
    private String normalizeTime(String hour,String min,String sec)
    {
        if(hour.length()==1)
            hour="0"+hour;
        if(min.length()==1)
            min="0"+min;
        if(sec.length()==1)
            sec="0"+sec;
        return "T"+hour+":"+min+":"+sec;
    }
    private String normalizeTime(String hour,String min)
    {
        if(hour.length()==1)
            hour="0"+hour;
        if(min.length()==1)
            min="0"+min;
        return "T"+hour+":"+min;
    }
    private String normalizeTime(String hour)
    {
        if(hour.length()==1)
            hour="0"+hour;
        return "T"+hour+":00";
    }
    private String CompleteTimePart(String hour, String min, String sec, String dayPart, String refDate) {
        hour = HourByDayPart(hour,dayPart);
        return CompleteTime(hour,min,sec,refDate);
    }
    private String HourByDayPart(String hour, String dayPart) {
        String dp = Utilities.GetPartOfDayForTime(dayPart);
        int h = Integer.valueOf(hour);
        if(dp.equals("N"))
        {
            if(h>=1 && h<=5)
                return String.valueOf(h+12);
        }
        else if(dp.equals("EV")||dp.equals("AF")||dp.equals("NI"))
        {
            if(h>=5 && h<=11)
                return String.valueOf(h+12);
            else if(h==12)
                return "00";
        }
        if(h<10)
            return "0"+h;
        return String.valueOf(h);
    }
    private String Hour(String hour, String refDate) {
        return refDate+normalizeTime(hour);
    }

    private String JustTime(String time, String refDate) {
        String[]parts = time.split(":");
        switch (parts.length) {
            case 2:
                return refDate+normalizeTime(parts[0], parts[1]);
            case 3:
                return refDate+normalizeTime(parts[0], parts[1],parts[2]);
            default:
                return refDate+normalizeTime(parts[0], parts[1],parts[2],parts[3]);
        }
    }

    private String HourPartRelDay(String hour, String dayPart, String relDay, String refDate) throws ParseException {
        String val = Utilities.GetRelativeDayValue(relDay);
        refDate = RefrenceTimeTools.GetRefDay(refDate, Integer.valueOf(val));
        hour = HourByDayPart(hour,dayPart);
        return refDate+normalizeTime(hour);
    }

    private String HourDayPart(String hour, String dayPart, String refDate) {
        hour = HourByDayPart(hour,dayPart);
        return refDate+normalizeTime(hour);
    }

    private String HourRelDay(String hour, String relDay, String refDate) throws ParseException {
        String val = Utilities.GetRelativeDayValue(relDay);
        refDate = RefrenceTimeTools.GetRefDay(refDate, Integer.valueOf(val));
        return refDate+normalizeTime(hour);
    }

    private String TimeRelDay(String time, String relDay, String refDate) throws ParseException {
        String val = Utilities.GetRelativeDayValue(relDay);
        refDate = RefrenceTimeTools.GetRefDay(refDate, Integer.valueOf(val));
        return JustTime(time,refDate);
    }

    private String TimeDayPart(String time, String dayPart, String refDate) {
        String hour = time.split(":")[0];
        hour = HourByDayPart(hour,dayPart);
        return JustTime(hour+time.substring(time.indexOf(":")),refDate);
    }

    private String TimeDayPartRelDay(String time, String dayPart, String relDay, String refDate) {
        String val = Utilities.GetRelativeDayValue(relDay);
        try {
            refDate = RefrenceTimeTools.GetRefDay(refDate, Integer.valueOf(val));
        } catch (ParseException ex) {
            Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TimeDayPart(time,dayPart,refDate);
    }

    private String HourDay(String hour, String day, String refDate) {
        int dayvalue = Integer.valueOf(Utilities.GetDayValue(day));
        int refDay = RefrenceTimeTools.GetDay(refDate);
        
        return "";
    }
                            
}
