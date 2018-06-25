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
            {
                try {
                    return HourDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "HourDayDayPart":
            {
                try {
                return HourDayDayPart(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "TimeDay":
            {
                try {
                return TimeDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "TimeDayDayPart":
            {
                try {
                return TimeDayDayPart(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "MinToHour":
            {
                return MinToHour(parameters.get(0),parameters.get(1),refDate);
            }
            case "MinToHourDayPart":
            {
                return MinToHourDayPart(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
            }
            case "MinToHourRelDay":
            {
                try {
                    return MinToHourRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                        Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "MinToHourDayPartRelDay":
            {
                try {
                    return MinToHourDayPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "MinToHourDayPartDay":
            {
                try {
                    return MinToHourDayPartDay(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "MinToHourDay":
            {
                try {
                    return MinToHourDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                        Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "Quarter":
            {
                try {
                    return Quarter(parameters.get(0),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterRelDay":
            {
                try {
                    return QuarterRelDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterDay":
            {
                try {
                    return QuarterDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterDayPart":
            {
                return QuarterDayPart(parameters.get(0),parameters.get(1),refDate);
            }    
            case "QuarterDayPartRelDay":
            {
                try {
                    return QuarterDayPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterDayPartDay":
            {
                try {
                    return QuarterDayPartDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "Half":
            {
                try {
                    return Half(parameters.get(0),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "HalfRelDay":
            {
                try {
                    return HalfRelDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "HalfDay":
            {
                try {
                    return HalfDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "HalfDayPart":
            {
                return HalfDayPart(parameters.get(0),parameters.get(1),refDate);
            }    
            case "HalfDayPartRelDay":
            {
                try {
                    return HalfDayPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "HalfDayPartDay":
            {
                try {
                    return HalfDayPartDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterTo":
            {
                try {
                    return QuarterTo(parameters.get(0),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterToRelDay":
            {
                try {
                    return QuarterToRelDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterToDay":
            {
                try {
                    return QuarterToDay(parameters.get(0),parameters.get(1),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterToDayPart":
            {
                return QuarterToDayPart(parameters.get(0),parameters.get(1),refDate);
            }    
            case "QuarterToDayPartRelDay":
            {
                try {
                    return QuarterToDayPartRelDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            case "QuarterToDayPartDay":
            {
                try {
                    return QuarterToDayPartDay(parameters.get(0),parameters.get(1),parameters.get(2),refDate);
                } catch (ParseException ex) {
                    Logger.getLogger(TimeManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
                
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
    
    private String HourDay(String hour, String day, String refDate) throws ParseException {
        int dayvalue = Integer.valueOf(Utilities.GetDayValue(day));
        int refDay = RefrenceTimeTools.GetDay(refDate);
        int distance = Utilities.getDistanceDayByRefDay(refDay,dayvalue);
        String val = RefrenceTimeTools.GetRefDay(refDate, distance);
        return val+normalizeTime(hour);
    }
    
    private String HourDayDayPart(String hour, String dayPart, String day, String refDate) throws ParseException {
        int dayvalue = Integer.valueOf(Utilities.GetDayValue(day));
        int refDay = RefrenceTimeTools.GetDay(refDate);
        int distance = Utilities.getDistanceDayByRefDay(refDay,dayvalue);
        String date = RefrenceTimeTools.GetRefDay(refDate, distance);
        hour = HourByDayPart(hour,dayPart);       
        return date+normalizeTime(hour);
    }
    
    private String TimeDay(String time, String day, String refDate) throws ParseException {
        int dayvalue = Integer.valueOf(Utilities.GetDayValue(day));
        int refDay = RefrenceTimeTools.GetDay(refDate);
        int distance = Utilities.getDistanceDayByRefDay(refDay,dayvalue);
        String date = RefrenceTimeTools.GetRefDay(refDate, distance);
        return JustTime(time,date);
    }
    
    private String TimeDayDayPart(String time, String dayPart, String day, String refDate) throws ParseException {
        String hour = time.split(":")[0];
        hour = HourByDayPart(hour,dayPart);
        return TimeDay(hour+time.substring(time.indexOf(":")),day,refDate);
    }
    
    private String MinToHour(String min, String hour, String refDate)
    {
        return JustTime(MakeTimForRemain(min,hour),refDate);
    }
    
    private String MinToHourDayPart(String min, String hour, String dayPart, String refDate)
    {
        return TimeDayPart(MakeTimForRemain(min,hour),dayPart,refDate);
    }
    
    private String MinToHourRelDay(String min, String hour, String relDay, String refDate) throws ParseException
    {
        return TimeRelDay(MakeTimForRemain(min,hour),relDay,refDate);
    }
    
    private String MinToHourDayPartRelDay(String min, String hour, String dayPart, String relDay, String refDate) throws ParseException
    {
        return TimeDayPartRelDay(MakeTimForRemain(min,hour),dayPart,relDay,refDate);
    }
    
    private String MinToHourDay(String min, String hour, String day, String refDate) throws ParseException
    {
        return TimeDay(MakeTimForRemain(min,hour),day,refDate);
    }
    
    private String MinToHourDayPartDay(String min, String hour, String dayPart, String day, String refDate) throws ParseException
    {
        return TimeDayDayPart(MakeTimForRemain(min,hour),dayPart,day,refDate);
    }
    
    private String Quarter(String hour, String refDate) throws ParseException
    {
        return JustTime(MakeQuarterTime(hour),refDate);
    }
    
    private String QuarterDayPart(String hour, String dayPart, String refDate)
    {
        return TimeDayPart(MakeQuarterTime(hour),dayPart,refDate);
    }
    
    private String QuarterRelDay(String hour, String relDay, String refDate) throws ParseException
    {
        return TimeRelDay(MakeQuarterTime(hour),relDay,refDate);
    }
    
    private String QuarterDayPartRelDay(String hour, String dayPart, String relDay, String refDate) throws ParseException
    {
        return TimeDayPartRelDay(MakeQuarterTime(hour),dayPart,relDay,refDate);
    }
    
    private String QuarterDay(String hour, String day, String refDate) throws ParseException
    {
        return TimeDay(MakeQuarterTime(hour),day,refDate);
    }
    
    private String QuarterDayPartDay(String hour, String dayPart, String day, String refDate) throws ParseException
    {
        return TimeDayDayPart(MakeQuarterTime(hour),dayPart,day,refDate);
    }

    private String Half(String hour, String refDate) throws ParseException
    {
        return JustTime(MakeHalfTime(hour),refDate);
    }
    
    private String HalfDayPart(String hour, String dayPart, String refDate)
    {
        return TimeDayPart(MakeHalfTime(hour),dayPart,refDate);
    }
    
    private String HalfRelDay(String hour, String relDay, String refDate) throws ParseException
    {
        return TimeRelDay(MakeHalfTime(hour),relDay,refDate);
    }
    
    private String HalfDayPartRelDay(String hour, String dayPart, String relDay, String refDate) throws ParseException
    {
        return TimeDayPartRelDay(MakeHalfTime(hour),dayPart,relDay,refDate);
    }
    
    private String HalfDay(String hour, String day, String refDate) throws ParseException
    {
        return TimeDay(MakeHalfTime(hour),day,refDate);
    }
    
    private String HalfDayPartDay(String hour, String dayPart, String day, String refDate) throws ParseException
    {
        return TimeDayDayPart(MakeHalfTime(hour),dayPart,day,refDate);
    }
    
    private String QuarterTo(String hour, String refDate) throws ParseException
    {
        return JustTime(MakeTimeForQuarterTo(hour),refDate);
    }
    
    private String QuarterToDayPart(String hour, String dayPart, String refDate)
    {
        return TimeDayPart(MakeTimeForQuarterTo(hour),dayPart,refDate);
    }
    
    private String QuarterToRelDay(String hour, String relDay, String refDate) throws ParseException
    {
        return TimeRelDay(MakeTimeForQuarterTo(hour),relDay,refDate);
    }
    
    private String QuarterToDayPartRelDay(String hour, String dayPart, String relDay, String refDate) throws ParseException
    {
        return TimeDayPartRelDay(MakeTimeForQuarterTo(hour),dayPart,relDay,refDate);
    }
    
    private String QuarterToDay(String hour, String day, String refDate) throws ParseException
    {
        return TimeDay(MakeTimeForQuarterTo(hour),day,refDate);
    }
    
    private String QuarterToDayPartDay(String hour, String dayPart, String day, String refDate) throws ParseException
    {
        return TimeDayDayPart(MakeTimeForQuarterTo(hour),dayPart,day,refDate);
    }
    
    private String MakeTimForRemain(String min, String hour)
    {
        int minutes = 0;
        minutes = 60 - Integer.valueOf(min);
        int ho = Integer.valueOf(hour);
        
        if(ho==0)
            ho=23;
        else
            ho -=1;
        return ho+":"+minutes;
    }
    private String MakeQuarterTime(String hour)
    {
        int minutes = 15;
        int ho = Integer.valueOf(hour);
        return ho+":"+minutes;
    }
    private String MakeHalfTime(String hour)
    {
        int minutes = 30;
        int ho = Integer.valueOf(hour);
        return ho+":"+minutes;
    }
    private String MakeTimeForQuarterTo(String hour)
    {
        int minutes = 45;
        int ho = Integer.valueOf(hour);
        
        if(ho==0)
            ho=23;
        else
            ho -=1;
        return ho+":"+minutes;
    }
}
