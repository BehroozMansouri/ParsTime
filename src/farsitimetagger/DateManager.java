/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
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
                    case "Decade":
                        if(parameters.size()==1)
                            return Dedace(parameters.get(0),"");
                        else
                            return Dedace(parameters.get(0),parameters.get(1));
                    case "DecadeCentury":
                        if(parameters.size()==2)
                            return DecadeCentury(parameters.get(0),parameters.get(1),"");
                        else
                            return DecadeCentury(parameters.get(0),parameters.get(1),parameters.get(2));
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
                    case "RelYear":
                        return RelYear(parameters.get(0),parameters.get(1));
                    case "RelYearArabic":
                        return RelYearArabic(parameters.get(0),parameters.get(1));
                    case "RelYearGeorgian":
                        return RelYearGeorgian(parameters.get(0),parameters.get(1));
                    case "Yesterday":
                        return Yesterday(refDate);
                    case "Tomorrow":
                        return Tomorrow(refDate);
                    case "SomeDaysAgo":
                        return NumDayAgo(Utilities.ValueForSome,refDate);
                    case "SomeDayLater":
                        return NumDayLater(Utilities.ValueForSome,refDate);
                    case "NumDayAgo":
                        return NumDayAgo(parameters.get(0),refDate);
                    case "NumDayLater":
                        return NumDayLater(parameters.get(0),refDate);
                    case "LastWeek":
                        return LastWeek(refDate);
                    case "NextWeek":
                        return NextWeek(refDate);
                    case "SomeWeeksAgo":
                        return SomeWeeksAgo(refDate);
                    case "SomeWeeksLater":
                        return SomeWeeksLater(refDate);
                    case "NumWeeksAgo":
                        return NumWeeksAgo(parameters.get(0),refDate);
                    case "NumWeeksLater":
                        return NumWeeksLater(parameters.get(0),refDate);
                    case "ThisWeek":
                        return ThisWeek(refDate);
                    case "ThisMonth":
                        return ThisMonth(refDate);
                    case "ThisGeorgianMonth":
                        return ThisGeorgianMonth(refDate);
                    case "LastMonth":
                        return LastMonth(refDate);
                    case "NextMonth":
                        return NextMonth(refDate);
                    case "SomeMonthsAgo":
                        return SomeMonthsAgo(refDate);
                    case "SomeMonthsLater":
                        return SomeMonthsLater(refDate);  
                    case "NumMonthsAgo":
                        return NumMonthsAgo(parameters.get(0),refDate);
                    case "NumMonthsLater":
                        return NumMonthsLater(parameters.get(0),refDate);                       
                    case "LastYear":
                        return LastYear(refDate);
                    case "NextYear":
                        return NextYear(refDate);
                    case "SomeYearsAgo":
                        return SomeYearsAgo(refDate);
                    case "SomeYearsLater":
                        return SomeYearsLater(refDate);
                    case "NumYearsAgo":
                        return NumYearsAgo(parameters.get(0),refDate);
                    case "NumYearsLater":
                        return NumYearsLater(parameters.get(0),refDate);  
                    case "ThisYear":
                        return ThisYear(refDate);                 
                    case "LastYearGeorgian":
                        return LastYearGeorgian(refDate);   
                    case "NextYearGeorgian":
                        return NextYearGeorgian(refDate);
                    case "ThisDecade":
                        return ThisDecade(refDate);  
                    case "LastDecade":
                        return LastDecade(refDate);  
                    case "NextDecade":
                        return NextDecade(refDate);  
                    case "SomdeDecadesAgo":
                        return SomdeDecadesAgo(refDate);  
                    case "SomeDecadesLater":
                        return SomeDecadesLater(refDate);  
                    case "NumDecadesAgo":
                        return NumDecadesAgo(parameters.get(0),refDate);
                    case "NumDecadesLater":
                        return NumDecadesLater(parameters.get(0),refDate);  
                    case "ThisCentury":
                        return ThisCentury(refDate);  
                    case "LastCentury":
                        return LastCentury(refDate);  
                    case "NextCentury":
                        return NextCentury(refDate);  
                    case "SomdeCenturiesAgo":
                        return SomdeCenturiesAgo(refDate);  
                    case "SomeCenturiesLater":
                        return SomeCenturiesLater(refDate);  
                    case "NumCenturiesAgo":
                        return NumCenturiesAgo(parameters.get(0),refDate);
                    case "NumCenturiesLater":
                        return NumCenturiesLater(parameters.get(0),refDate);  
                    case "ModeLastWeek":
                        return ModeLastWeek(parameters.get(0),refDate);  
                    case "ModeNextWeek":
                        return ModeNextWeek(parameters.get(0),refDate);  
                    case "ModeThisWeek":
                        return ModeThisWeek(parameters.get(0),refDate);  
                    case "ModeNumWeekAgo":
                        return ModeNumWeekAgo(parameters.get(0),parameters.get(1),refDate);  
                    case "ModeNumWeekLater":
                        return ModeNumWeekLater(parameters.get(0),parameters.get(1),refDate);  
                    case "ModeLastMonth":
                        return ModeLastMonth(parameters.get(0),refDate);  
                    case "ModeNextMonth":
                        return ModeNextMonth(parameters.get(0),refDate);  
                    case "ModeThisMonth":
                        return ModeThisMonth(parameters.get(0),refDate);  
                    case "ModeNumMonthsLater":
                        return ModeNumMonthsLater(parameters.get(0),parameters.get(1),refDate);  
                    case "ModeNumMonthsAgo":
                        return ModeNumMonthsAgo(parameters.get(0),parameters.get(1),refDate);
                    case "ModeThisYear":
                        return ModeThisYear(parameters.get(0),refDate);  
                    case "ModeLastYear":
                        return ModeLastYear(parameters.get(0),refDate);  
                    case "ModeNextYear":
                        return ModeNextYear(parameters.get(0),refDate);  
                    case "ModeNumYearsLater":
                        return ModeNumYearsLater(parameters.get(0),parameters.get(1),refDate);  
                    case "ModeNumYearsAgo":
                        return ModeNumYearsAgo(parameters.get(0),parameters.get(1),refDate);
                    case "ModeThisDecade":
                        return ModeThisDecade(parameters.get(0),refDate);  
                    case "ModeLastDecade":
                        return ModeLastDecade(parameters.get(0),refDate);  
                    case "ModeNextDecade":
                        return ModeNextDecade(parameters.get(0),refDate);  
                    case "ModeLastCentury":
                        return ModeLastCentury(parameters.get(0),refDate);  
                    case "ModeNextCentury":
                        return ModeNextCentury(parameters.get(0),refDate);  
                    case "ModeThisCentury":
                        return ModeThisCentury(parameters.get(0),refDate);     
                    case "RelativeYear":
                        return RelativeYear(parameters.get(0),refDate);                        
                    case "RelativeYearMonth":
                        return RelativeYearMonth(parameters.get(0),parameters.get(1),refDate);                                     
                    case "ModeRelativeYear":
                        return ModeRelativeYear(parameters.get(0),parameters.get(1),refDate);                           
                    case "RelativeDay":
                        return RelativeDay(parameters.get(0),refDate);
                    case "RelativeDayWithDayMonth":
                        return RelativeDayWithDayMonth(parameters.get(0),parameters.get(1),parameters.get(2),refDate);                                                   
                    case "NumMonth":
                        return NumMonth(parameters.get(0),parameters.get(1),refDate); 
                    case "RelativeDayWithDayMonthYear":
                        return RelativeDayWithDayMonthYear(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3),refDate);                             
                        
                    default:
                        break;
                }
            return "";
        } catch (ParseException ex) {
            Logger.getLogger(DateManager.class.getName()).log(Level.SEVERE, null, ex);
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
        {
            if(calender.equals("Georgian"))
                return  decade.substring(0, 3)+"X("+calender+")";
            else if(calender.equals("Arabic"))
                return  decade.substring(0, 3)+"X("+calender+")";
            else
                return  decade.substring(0, 3)+"X";
        }
        else if(calender.equals("Georgian"))
            return  "19"+decade.substring(0, 1)+"X"+"("+calender+")";
        else if(calender.equals("Arabic"))
            return  "14"+decade.substring(0, 1)+"X"+"("+calender+")";
        else
            return  "13"+decade.substring(0, 1)+"X";
    }
    private String DecadeCentury(String decade, String Century, String calendarType) {
        String calender = "Jalali";
        if(!calendarType.isEmpty())
            calender = Utilities.GetClanderType(calendarType);
        if(decade.length()==4)
            return  decade.substring(0, 3)+"X";
        else if(calender.equals("Georgian"))
            return  Century.substring(0,2)+decade.substring(0, 1)+"X"+"("+calender+")";
        else if(calender.equals("Arabic"))
            return  Century.substring(0,2)+decade.substring(0, 1)+"X"+"("+calender+")";
        else
            return  Century.substring(0,2)+decade.substring(0, 1)+"X";
    }

    private String Century(String century, String calendarType) {
        String calender = "Jalali";
        if(!calendarType.isEmpty())
            calender = Utilities.GetClanderType(calendarType);
        if(century.length()==2)
        {
            if(calender.equals("Jalali"))
                return  century+"XX";
            else
                return  century+"XX"+"("+calender+")";
        }
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

    private String RelYear(String relTime, String Year) {
        String result = Year(Year,"J");
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelYearArabic(String relTime, String Year) {
        String result = Year(Year,"A");
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelYearGeorgian(String relTime, String Year) {
        String result = Year(Year,"G");
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+result;
    }
    
    private String Yesterday(String refDate) throws ParseException {
        return RefrenceTimeTools.GetRefDay(refDate, -1);
    }

    private String Tomorrow(String refDate) throws ParseException {
        return RefrenceTimeTools.GetRefDay(refDate, 1);
    }

    private String NumDayAgo(String numberDay, String refDate) throws ParseException {
        int val = -Integer.valueOf(numberDay);
        return RefrenceTimeTools.GetRefDay(refDate, val);
    }

    private String NumDayLater(String numberDay,String refDate) throws ParseException {
        int val = Integer.valueOf(numberDay);
        return RefrenceTimeTools.GetRefDay(refDate, val);
    }

    private String LastWeek(String refDate) throws ParseException {
        return ThisWeek(NumDayAgo("7",refDate));
    }

    private String NextWeek(String refDate) throws ParseException {
        return ThisWeek(NumDayLater("7",refDate));
    }

    private String SomeWeeksAgo(String refDate) throws ParseException {
        return NumWeeksAgo(Utilities.ValueForSome,refDate);
    }

    private String SomeWeeksLater(String refDate) throws ParseException {
        return NumWeeksLater(Utilities.ValueForSome,refDate);
    }

    private String NumWeeksAgo(String number, String refDate) throws ParseException {
        String val = String.valueOf(Integer.valueOf(number)*7);
        return ThisWeek(NumDayAgo(val,refDate));
    }

    private String NumWeeksLater(String number, String refDate) throws ParseException {
        String val = String.valueOf(Integer.valueOf(number)*7);
        return ThisWeek(NumDayLater(val,refDate));
    }

    private String ThisWeek(String refDate) {
        return RefrenceTimeTools.GetRefWeekOfYear(refDate);
    }

    private String ThisMonth(String refDate) {
        return refDate.split("-")[0]+"-"+refDate.split("-")[1];
    }

    private String ThisGeorgianMonth(String refDate) {
        String date = CalendarConvetor.JalaliToGregorian(refDate);
        return date.split("-")[0]+"-"+date.split("-")[1]+"(Georgian)";
    }

    private String LastMonth(String refDate) throws ParseException {
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, -1));
    }

    private String NextMonth(String refDate) throws ParseException {
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, 1));
    }

    private String SomeMonthsAgo(String refDate) throws ParseException {
        int val = -(Integer.valueOf(Utilities.ValueForSome));
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, val));
    }

    private String SomeMonthsLater(String refDate) throws ParseException {
        int val = (Integer.valueOf(Utilities.ValueForSome));
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, val));
    }

    private String NumMonthsAgo(String number, String refDate) throws ParseException {
        int val = -(Integer.valueOf(number));
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, val));
    }

    private String NumMonthsLater(String number, String refDate) throws ParseException {
        int val = (Integer.valueOf(number));
        return ThisMonth(RefrenceTimeTools.GetRefMonth(refDate, val));
    }

    private String LastYear(String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])-1; 
        return String.valueOf(year);
    }

    private String NextYear(String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])+1; 
        return String.valueOf(year);
    }

    private String SomeYearsAgo(String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])-Integer.valueOf(Utilities.ValueForSome); 
        return String.valueOf(year);
    }

    private String SomeYearsLater(String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])+Integer.valueOf(Utilities.ValueForSome); 
        return String.valueOf(year);
    }

    private String NumYearsAgo(String number, String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])-Integer.valueOf(number); 
        return String.valueOf(year);
    }

    private String NumYearsLater(String number, String refDate) {
        int year = Integer.valueOf(refDate.split("-")[0])+Integer.valueOf(number); 
        return String.valueOf(year);
    }

    private String ThisYear(String refDate) {
        return refDate.split("-")[0]; 
    }

    private String LastYearGeorgian(String refDate) {
        return LastYear(CalendarConvetor.JalaliToGregorian(refDate));
    }

    private String NextYearGeorgian(String refDate) {
        return NextYear(CalendarConvetor.JalaliToGregorian(refDate));
    }

    private String ThisDecade(String refDate) {
        String year = refDate.split("-")[0];
        return  year.substring(0, 3)+"X";
    }

    private String LastDecade(String refDate) {
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])-10);
        return  year.substring(0, 3)+"X";
    }

    private String NextDecade(String refDate) {
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+10);
        return  year.substring(0, 3)+"X";
    }

    private String SomdeDecadesAgo(String refDate) {
        int val = Integer.valueOf(Utilities.ValueForSome+"0");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])-val);
        return  year.substring(0, 3)+"X";
    }

    private String SomeDecadesLater(String refDate) {
        int val = Integer.valueOf(Utilities.ValueForSome+"0");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+val);
        return  year.substring(0, 3)+"X";
    }

    private String NumDecadesAgo(String number, String refDate) {
        int val = Integer.valueOf(number+"0");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])-val);
        return  year.substring(0, 3)+"X";
    }

    private String NumDecadesLater(String number, String refDate) {
        int val = Integer.valueOf(number+"0");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+val);
        return  year.substring(0, 3)+"X";
    }

    private String ThisCentury(String refDate) {
        String year = refDate.split("-")[0];
        return  year.substring(0, 2)+"XX";
    }

    private String LastCentury(String refDate) {
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])-100);
        return  year.substring(0, 2)+"XX";
    }

    private String NextCentury(String refDate) {
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+100);
        return  year.substring(0, 2)+"XX";
    }

    private String SomdeCenturiesAgo(String refDate) {
        int val = Integer.valueOf(Utilities.ValueForSome+"00");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])-val);
        return  year.substring(0, 2)+"XX";
    }

    private String SomeCenturiesLater(String refDate) {
        int val = Integer.valueOf(Utilities.ValueForSome+"00");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+val);
        return  year.substring(0, 2)+"XX";
    }

    private String NumCenturiesAgo(String number, String refDate) {
        int val = Integer.valueOf(number+"00");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+val);
        return  year.substring(0, 2)+"XX";
    }

    private String NumCenturiesLater(String number, String refDate) {
        int val = Integer.valueOf(number+"00");
        String year = String.valueOf(Integer.valueOf(refDate.split("-")[0])+val);
        return  year.substring(0, 2)+"XX";
    }

    private String ModeLastWeek(String DefinedMode, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result = LastWeek(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNextWeek(String DefinedMode, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result = NextWeek(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeThisWeek(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result = ThisWeek(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumWeekAgo(String DefinedMode, String number, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String val = String.valueOf(Integer.valueOf(number)*7);
        String result =  NumWeeksAgo(number, refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumWeekLater(String DefinedMode, String number, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NumWeeksLater(number,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeLastMonth(String DefinedMode, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  LastMonth(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNextMonth(String DefinedMode, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NextMonth(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeThisMonth(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  ThisMonth(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumMonthsLater(String DefinedMode, String number, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NumMonthsLater(number,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumMonthsAgo(String DefinedMode, String number, String refDate) throws ParseException {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NumMonthsAgo(number,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeThisYear(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  ThisYear(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeLastYear(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  LastYear(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNextYear(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NextYear(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumYearsLater(String DefinedMode, String number, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NumYearsLater(number,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNumYearsAgo(String DefinedMode, String number, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NumYearsAgo(number,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeThisDecade(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  ThisDecade(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeLastDecade(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  LastDecade(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNextDecade(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NextDecade(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeLastCentury(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  LastCentury(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeNextCentury(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  NextCentury(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String ModeThisCentury(String DefinedMode, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  ThisCentury(refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelativeYear(String relativeYear, String refDate) {
        if(refDate==null || refDate.isEmpty())
            return "";
        int yearnumber = RuleUtilities.GetRelativeYear(relativeYear);
        int currentYear = Integer.valueOf(refDate.split("-")[0]);
        return String.valueOf(currentYear+yearnumber);
    }

    private String RelativeYearMonth(String Month, String relativeYear, String refDate) {
        String year = RelativeYear(relativeYear,refDate);
        String month = String.valueOf(Utilities.GetMonthValue(Month));
        if(month.length()==1)
            month="0"+month;
        return year+"-"+month;
    }

    private String ModeRelativeYear(String DefinedMode, String relativeYear, String refDate) {
        String mode = Utilities.GetMode(DefinedMode);
        String result =  RelativeYear(relativeYear,refDate);
        return "mode=\""+mode+"\" "+result;
    }

    private String RelativeDay(String relativeDay, String refDate) throws ParseException {
        if(refDate==null || refDate.isEmpty())
            return "";
        int val = RuleUtilities.GetRelativeDay(relativeDay);
        return RefrenceTimeTools.GetRefDay(refDate, val);
    }

    private String RelativeDayWithDayMonth(String relativeDay, String Number, String Month, String refDate) throws ParseException {
        if(refDate==null || refDate.isEmpty())
            return NumMonth(Number, Month, refDate);
        else 
            return RelativeDay(relativeDay, refDate);
    }

    private String NumMonth(String Number, String Month, String refDate) {
        String calendarType = Utilities.GetCalanderType(Month);
        return Utilities.GetNormalValueMonthDay(Month,Number,refDate,calendarType);
    }

    private String RelativeDayWithDayMonthYear(String relativeDay, String Number, String Month, String Year, String refDate) throws ParseException {
        String calendarType = Utilities.GetCalanderType(Month); 
        if(refDate==null || refDate.isEmpty())
            return DateGenerator(Number, Month, Year);
        else
        {
            if(calendarType.equals("G")||calendarType.equals("A"))
                return DateGenerator(Number, Month, Year);
            return RelativeDay(relativeDay, refDate);
        }
    }

        
}
