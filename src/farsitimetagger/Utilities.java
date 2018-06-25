/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author zahedi
 */
public class Utilities {
    public static String ValueForSome = "3";
    static HashMap<String,Integer> map_MonthValue;
    static HashMap<String,String> map_SeasonValue;
    static HashMap<String,String> map_MonthCalendar;
    static HashMap<String,String> map_ModMap;
    static HashMap<String,String> map_PointerRelativeTimeMap;
    static HashMap<String,String> map_CalendarType;
    static HashMap<String,String> map_WeekDays;
    static HashMap<String,String> map_RelativeDays;
    static HashMap<String,String> map_DayPartForSet;
    static HashMap<String,String> map_DayPartForHour;
    public static void Initialize() throws IOException
    {
        String RulesDirectoryPath = new File("").getAbsolutePath()+"\\Normalizer\\";
        map_SeasonValue = ReadMapFile(RulesDirectoryPath+"seasons.txt");
        map_ModMap = ReadMapFile(RulesDirectoryPath+"Mod.txt");
        map_PointerRelativeTimeMap = ReadMapFile(RulesDirectoryPath+"relativeTime.txt");
        map_CalendarType =  ReadMapFile(RulesDirectoryPath+"CalendarType.txt");
        map_WeekDays =  ReadMapFile(RulesDirectoryPath+"WeekDays.txt");
        map_RelativeDays =  ReadMapFile(RulesDirectoryPath+"relativeDays.txt");
        map_DayPartForSet = ReadMapFile(RulesDirectoryPath+"dayPartForSet.txt");
        map_DayPartForHour = ReadMapFile(RulesDirectoryPath+"dayPartForHour.txt");
        map_MonthValue = new  HashMap<String, Integer>();
        map_MonthCalendar = new HashMap<String, String>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(RulesDirectoryPath+"months.txt")), "UTF8")))
        {    
            String line=in.readLine();
            while ((line=in.readLine())!= null)
            {
                String[]parts = line.split("#");
                map_MonthValue.put(parts[0], Integer.valueOf(parts[1]));
                map_MonthCalendar.put(parts[0], parts[2]);
            }
        }
    }
    
    public static String StacktoString(Stack<String> stack)
    {
        String temp = "";
        while(!stack.isEmpty())
            temp = stack.pop()+" "+temp;
        return temp.trim();
    }

    static boolean IsNumber(String term) {
        try
        {
            Integer.valueOf(term);
            return true;
        }
        catch (Exception er)
        {
            return false;
        }
    }

    private static HashMap<String, String> ReadMapFile(String filePath) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "UTF8")))
            {    
                String line=in.readLine();
                while ((line=in.readLine())!= null)
                {
                    String[]parts = line.split("#");
                    result.put(parts[0], parts[1]);
                }
            }
        return result;
    }
    
    public static String GetNormalValue(String Date)
    {
        if(Date.isEmpty())
            return Date;
        String[] dateParts = Date.split("-");
        if(dateParts.length<3)
             throw new IllegalArgumentException("The input refDate is not in correct format; (YYYY-MM-DD)");
        
        String d = dateParts[2];
        if(d.length()==1)
            d="0"+d;
        String m = String.valueOf(dateParts[1]);
        if(m.length()==1)
            m="0"+m;
        String y = String.valueOf(dateParts[0]);
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
        return y+"-"+m+"-"+d;   
    }
    
    public static String GetNormalValue(String year, String month, String day)
    {
        return GetNormalValue(year,month,day,"J");
    }

    public static String GetNormalValue(String year, String month, String day, String CalendarType)
    {
        String d = day;
        if(d.length()==1)
            d="0"+d;
        String m = String.valueOf(month);
        if(m.length()==1)
            m="0"+m;
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
        if(CalendarType.equals("G"))
            return y+"-"+m+"-"+d+"(Georgian)";   
        else if(CalendarType.equals("A"))
            return y+"-"+m+"-"+d+"(Arabic)";   
        else 
            return y+"-"+m+"-"+d;   
    }

    public static String GetNormalValueMonthDay(String month, String day, String refDate, String CalendarType){
        String d = day;
        if(d.length()==1)
            d="0"+d;
        String m = String.valueOf(month);
        if(m.length()==1)
            m="0"+m;
        String y = "XXXX";
        if(!(refDate==null || refDate.isEmpty()))
        {
            switch (CalendarType) {
                case "G":
                    y = CalendarConvetor.JalaliToGregorian(refDate).split("-")[0];
                    return y+"-"+m+"-"+d+"(Georgian)";
                case "A":
                    y = CalendarConvetor.JalaliToArabic(refDate).split("-")[0];
                    return y+"-"+m+"-"+d+"(Arabic)";
                default:
                    y = refDate.split("-")[0];
                    return y+"-"+m+"-"+d;   
            }
        }
        else
        {
            switch (CalendarType) {
                case "G":
                    return y+"-"+m+"-"+d+"(Georgian)";
                case "A":
                    return y+"-"+m+"-"+d+"(Arabic)";
                default:   
                    return y+"-"+m+"-"+d;
            }
        }
    }
    
    public static String GetNormalValueYearMonth(String year, String month, String CalendarType){
        String m = String.valueOf(month);
        if(m.length()==1)
            m="0"+m;
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
        if(CalendarType.equals("G"))
            return y+"-"+m+"(Georgian)";   
        else if(CalendarType.equals("A"))
            return y+"-"+m+"(Arabic)";   
        else 
            return y+"-"+m;   
    }
    
    public static String GetNormalValueYear(String year, String CalendarType){
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
        if(CalendarType.equals("G"))
            return y+"(Georgian)";   
        else if(CalendarType.equals("A"))
            return y+"(Arabic)";   
        else 
            return y;   
    }
    
    public static String GetNormalValueSeasonYear(String season,String year){
        season = map_SeasonValue.get(season);
        String y = String.valueOf(year);
        switch (y.length()) {
            case 1:
                y="139"+y;
                break;
            case 2:
                y="13"+y;
                break;
            case 3:
                y="1"+y;
                break;
            default:
                break;
        }
        int value = Integer.valueOf(y);
        if(value>1800)
            return y+"-"+season+"(Georgian)";    
        else 
            return y+"-"+season;   
    }
    public static String GetNormalValueSeasonYear(String season){
        season = map_SeasonValue.get(season);
        String y = "XXXX";
        return y+"-"+season;    
    }
            
    public static String GetMode(String mode){
        return map_ModMap.get(mode);
    }
    public static String GetCalanderType(String month){
        return map_MonthCalendar.get(month);
    }
    
    public static int GetMonthValue(String month){
        return map_MonthValue.get(month);
    }
    public static String GetClanderType(String type){
        return map_CalendarType.get(type);
    }
    public static String GetDayValue(String day){
        return map_WeekDays.get(day);
    }
    public static String GetRelativeDayValue(String relDay){
        return map_RelativeDays.get(relDay);
    }
    private static String GetSeasonValue(String season){
        return map_SeasonValue.get(season);
    }
    public static String GetPartOfDayForSet(String partOfDay)
    {
        return map_DayPartForSet.get(partOfDay);
    }
    public static String GetPartOfDayForTime(String partOfDay)
    {
        return map_DayPartForHour.get(partOfDay);
    }
    public static int getDistanceDayByRefDay(int refDay,int dayvalue)
    {
        switch (refDay) {
            case 7:
                if(dayvalue>=1 && dayvalue<=3)
                    return dayvalue;
                else
                    return dayvalue-refDay;        
            case 1:
                if(dayvalue>=1 && dayvalue<=4)
                    return dayvalue-refDay;
                else
                    return dayvalue-8; 
            case 2:
                if(dayvalue>=1 && dayvalue<=5)
                    return dayvalue-refDay;
                else 
                    return dayvalue-9; 
            case 3:
                if(dayvalue!=7)
                    return dayvalue-refDay;
                else 
                    return -3;
            case 4:
                if(dayvalue!=7)
                    return dayvalue-refDay;
                else 
                    return 3; 
            case 5:
                if(dayvalue!=1)
                    return dayvalue-refDay;
                else 
                    return 3; 
            default:
                if(dayvalue>=3 && dayvalue<=7)
                    return dayvalue-refDay;
                else
                    return dayvalue+1;
        }
    }
    static boolean YearIsJalali(String Year) {
        int year = Integer.valueOf(Year);
        if((year>=40 && year<=99)||(year>=1340 && year<=1410))
            return true;
        return false;
    }

    static String GetArabicYearFromJalali(String Year, int month) {
        
        for(int i=1;i<=12;i++)
        {
            if(Integer.valueOf(CalendarConvetor.JalaliToArabic(Year+"-"+i+"-5").split("-")[1]) == month)
                return CalendarConvetor.JalaliToArabic(Year+"-"+i+"-5").split("-")[0];
            if(Integer.valueOf(CalendarConvetor.JalaliToArabic(Year+"-"+i+"-5").split("-")[1]) == month)
                return CalendarConvetor.JalaliToArabic(Year+"-"+i+"-15").split("-")[0];
            if(Integer.valueOf(CalendarConvetor.JalaliToArabic(Year+"-"+i+"-5").split("-")[1]) == month)
                return CalendarConvetor.JalaliToArabic(Year+"-"+i+"-25").split("-")[0];
        }
        return "XXXX";
    }

    static String GetNormalValueRelativeTimeMonth(String month, String CalendarType, String relativeTime, String refDate) {
        String m = String.valueOf(month);
        if(m.length()==1)
            m="0"+m;
        String y = "XXXX";
        String temp = "";
        if(!(refDate==null || refDate.isEmpty()))
        {
            switch (CalendarType) {
                case "G":
                    y = CalendarConvetor.JalaliToGregorian(refDate).split("-")[0];
                    temp = y+"-"+m+"-"+"(Georgian)";
                case "A":
                    y = CalendarConvetor.JalaliToArabic(refDate).split("-")[0];
                    temp = y+"-"+m+"-"+"(Arabic)";
                default:
                    y = refDate.split("-")[0];
                    temp = y+"-"+m;   
            }
        }
        else
        {
            switch (CalendarType) {
                case "G":
                    temp = y+"-"+m+"-"+"(Georgian)";
                case "A":
                    temp = y+"-"+m+"-"+"(Arabic)";
                default:   
                    temp = y+"-"+m;
            }
        }
        String value = map_ModMap.get(relativeTime);
        return "mode=\""+value+"\" "+temp;
    }

    static String GetNormalValueYearMonthWithRelativeTime(String Year, String Month, String calendarType, String relativeTime) {
        String temp = GetNormalValueYearMonth(Year, Month, calendarType);
        String value = map_ModMap.get(relativeTime);
        return "mode=\""+value+"\" "+temp;
    }

    static String CheckPointer(String pointerToDay) {
        return map_PointerRelativeTimeMap.get(pointerToDay);
    }

    static String GetLastDayofMonth(String refDate, String MonthNumber, String CalendarType) {
        int month = Integer.valueOf(MonthNumber);
        switch (CalendarType) {
                case "G":
                    if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
                        return "31";
                    else if(month!=2)
                        return "30";
                    else
                    {
                        if(refDate==null || refDate.isEmpty())
                            return "28";
                        else 
                        {
                            if(isGreYearLeap(CalendarConvetor.JalaliToGregorian(refDate).split("-")[0]))
                                return "29";
                            else
                                return "28";
                        }
                            
                    }
                case "A":
                    return "30";
                default:   
                    if(month<7)
                        return "31";
                    else if(month<12)
                        return "30";
                    else if(refDate==null || refDate.isEmpty())
                        return "29";
                    else
                    {
                        int year = Integer.valueOf(refDate.split("-")[0]);
                        if(year%4==3)
                            return "30";
                        return "29";
                    }
            }
    }
    
    static boolean isGreYearLeap(String Year)
    {
        int year = Integer.valueOf(Year);
        if (((year % 4 == 0) && (year % 100 !=0))||(year%400==0))
            return true;
        return false;
    }
    
    static String GetLastDayofMonthWithYear(String Year, String MonthNumber, String CalendarType) {
        int month = Integer.valueOf(MonthNumber);
        switch (CalendarType) {
                case "G":
                    if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
                        return "31";
                    else if(month!=2)
                        return "30";
                    else
                    {
                        if(isGreYearLeap(Year))
                            return "29";
                        else
                            return "28";  
                    }
                case "A":
                    return "30";
                default:   
                    if(month<7)
                        return "31";
                    else if(month<12)
                        return "30";
                    else
                    {
                        int year = Integer.valueOf(Year);
                        if(year%4==3)
                            return "30";
                        return "29";
                    }
            }
    }

}
