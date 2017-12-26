/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Behrooz
 */
public class DateManager extends TypeManger{
    
    public DateManager()throws IOException
    {
        
    }
    @Override
    public String CallFunction(String refDate, String functionName, List<String> parameters)
    {
        if(null!=functionName)
            switch (functionName) {
            case "DateGenerator":
                return DateGenerator(parameters.get(0),parameters.get(1),parameters.get(2));
            case "MonthDay":
                return MonthDay(parameters.get(0),parameters.get(1),refDate);
            case "MonthYear":
                return MonthYear(parameters.get(0),parameters.get(1));
            case "Year":
                return Year(parameters.get(0),"J");
            case "YearArabic":
                return Year(parameters.get(0),"A");
            case "YearGeorgian":
                return Year(parameters.get(0),"G");
            case "SeasonYear":
                return SeasonYear(parameters.get(0),parameters.get(1));
            case "NormRelativeTimeMonth":
                return NormRelativeTimeMonth(parameters.get(0),parameters.get(1),refDate);
            case "NormRelativeTimeMonthYear":
                return NormRelativeTimeMonthYear(parameters.get(0),parameters.get(1),parameters.get(2));
            case "TargetDayMonth":
                return TargetDayMonth(parameters.get(0),parameters.get(1),refDate);
            case "TargetDayMonthYear":
                return TargetDayMonthYear(parameters.get(0),parameters.get(1),parameters.get(2));
            case "HalfYear":        
                return HalfYear(parameters.get(0),parameters.get(1));    
            case "Dedace":
                if(parameters.size()==1)
                    return Dedace(parameters.get(0),"");    
                else
                    return Dedace(parameters.get(0),parameters.get(1));
            case "Century":
                if(parameters.size()==1)
                    return Century(parameters.get(0),"");    
                else
                    return Century(parameters.get(0),parameters.get(1));
                
            case "RelDecade":
                if(parameters.size()==2)
                    return RelDecade(parameters.get(0),parameters.get(1),"");    
                else
                    return RelDecade(parameters.get(0),parameters.get(1),parameters.get(2));
            case "RelCentury":
                if(parameters.size()==2)
                    return RelCentury(parameters.get(0),parameters.get(1),""); 
                else
                    return RelCentury(parameters.get(0),parameters.get(1),parameters.get(2));
            case "RelSeasonYear":
                    return RelSeasonYear(parameters.get(0),parameters.get(1),parameters.get(2));
            case "RelSeason":
                    return RelSeason(parameters.get(0),parameters.get(1),refDate);
            default:    
                break;
        }
        return "";
    }
    
    public String DateGenerator(String Day, String Month, String Year)
    {
        String calendarType = Utilities.GetCalanderType(Month);
        Month = String.valueOf(Utilities.GetMonthValue(Month));
        if("A".equals(calendarType) && Utilities.YearIsJalali(Year))
            Year = Utilities.GetArabicYearFromJalali(Year,Integer.valueOf(Month));
        return Utilities.GetNormalValue(Year, Month, Day, calendarType);
    }

    private String MonthDay(String Day, String Month, String refDate) {
        String calendarType = Utilities.GetCalanderType(Month);
        Month = String.valueOf(Utilities.GetMonthValue(Month));
        return Utilities.GetNormalValueMonthDay(Month, Day,refDate, calendarType);
    }

    private String MonthYear(String Month, String Year) {
        String calendarType = Utilities.GetCalanderType(Month);
        if("A".equals(calendarType) && Utilities.YearIsJalali(Year))
            Year = Utilities.GetArabicYearFromJalali(Year,Utilities.GetMonthValue(Month));
        Month = String.valueOf(Utilities.GetMonthValue(Month));
        return Utilities.GetNormalValueYearMonth(Year,Month,calendarType);
    }

    private String Year(String year, String clanderType) {
        return Utilities.GetNormalValueYear(year, clanderType);
    }

    private String SeasonYear(String season, String year) {
        return Utilities.GetNormalValueSeasonYear(season,year);
    }

    private String NormRelativeTimeMonth(String relativeTime, String month, String refDate) {
        String calendarType = Utilities.GetCalanderType(month);
        month = String.valueOf(Utilities.GetMonthValue(month));
        return Utilities.GetNormalValueRelativeTimeMonth(month,calendarType,relativeTime,refDate);
    }

    private String NormRelativeTimeMonthYear(String relativeTime, String Month, String Year) {
        String calendarType = Utilities.GetCalanderType(Month);
        if("A".equals(calendarType) && Utilities.YearIsJalali(Year))
            Year = Utilities.GetArabicYearFromJalali(Year,Utilities.GetMonthValue(Month));
        Month = String.valueOf(Utilities.GetMonthValue(Month));
        return Utilities.GetNormalValueYearMonthWithRelativeTime(Year,Month,calendarType,relativeTime);
    }

    private String TargetDayMonth(String pointerToDay, String Month, String refDate) {
        String pointerValue = Utilities.CheckPointer(pointerToDay);
        if(pointerValue==null || pointerValue.isEmpty())
            return "";
        
        String calendarType = Utilities.GetCalanderType(Month);
        if(pointerValue.equals("S"))
            return MonthDay("01",Month,refDate);
        String m = String.valueOf(Utilities.GetMonthValue(Month));
        
        String Day = Utilities.GetLastDayofMonth(refDate,m,calendarType);
        return MonthDay(Day,Month,refDate);
    }

    private String TargetDayMonthYear(String pointerToDay, String Month, String Year) {
        String pointerValue = Utilities.CheckPointer(pointerToDay);
        if(pointerValue==null || pointerValue.isEmpty())
            return "";
        String calendarType = Utilities.GetCalanderType(Month);
        if(pointerValue.equals("S"))
            return DateGenerator("01",Month,Year);
        String m = String.valueOf(Utilities.GetMonthValue(Month));
        String Day = Utilities.GetLastDayofMonthWithYear(Year,m,calendarType);
        
        return DateGenerator(Day,Month,Year);
    }

    private String HalfYear(String halfDefiner, String year) {
        String y = String.valueOf(year);
        switch (y.length()) {
            case 1:
                y="000"+y;
                break;
            case 2:
                y="00"+y;
                break;
            case 3:
                y="0"+y;
                break;
            default:
                break;
        }
        if(Integer.valueOf(halfDefiner)==1)
        {
             return "mode=\"START\" "+y;
        }
        else if(Integer.valueOf(halfDefiner)==2)
        {
             return "mode=\"END\" "+y;
        }
        return "";
    }

    private String Dedace(String decade, String calendarType) {
        String calender = "Jalali";
        if(!calendarType.isEmpty())
            calender = Utilities.GetClanderType(calendarType);
        if(decade.length()==4)
            return  decade.substring(0, 3)+"X"+"("+calender+")";
        else if(calendarType.equals("Georgian"))
            return  "19"+decade.substring(0, 1)+"X"+"("+calender+")";
        else
            return  "13"+decade.substring(0, 1)+"X"+"("+calender+")";
    }

    private String Century(String century, String calendarType) {
        String calender = "Jalali";
        if(!calendarType.isEmpty())
            calender = Utilities.GetClanderType(calendarType);
        if(century.length()==2)
            return  century+"XX"+"("+calender+")";
        else 
            return  "";
    }

    private String RelDecade(String relTime, String decade, String calendarType) {
        String result = Dedace(decade, calendarType);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelCentury(String relTime, String century, String calendarType) {
        String result = Century(century, calendarType);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelSeasonYear(String relTime, String season, String year) {
        String result = SeasonYear(season,year);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelSeason(String relTime, String season, String refDate) {
        if(refDate!=null && !refDate.isEmpty())
            return RelSeasonYear(relTime,season,refDate.split("-")[0]);
        String result = Utilities.GetNormalValueSeasonYear(season);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    

}
