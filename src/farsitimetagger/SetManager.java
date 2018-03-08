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
public class SetManager extends TypeManger{
    public SetManager() throws IOException
    {
        
    }
    @Override
    public String CallFunction(String refDate, String functionName, List<String> parameters)
    {
        if(null!=functionName)
            switch (functionName) {
            case "PeriodicWeekDays":
                return PeriodicWeekDays(parameters.get(0));
            case "EveryDayPart":
                return EveryDayPart(parameters.get(0));
            case "EveryDay":
                return "P1D";
            case "EveryYear":
                return "P1Y";
            case "RTEveryYear":
                return RTEveryYear(parameters.get(0));
            case "RTEveryMonthNoName":
                return RTEveryMonthNoName(parameters.get(0));    
            case "RTEveryWeek":
                return RTEveryWeek(parameters.get(0));    
            case "EveryMonthYear":
                return EveryMonthYear(parameters.get(0));
            case "EverySeason":
                return EverySeason(parameters.get(0));
            case "EveryMonth":
                return "P1M";
            case "EveryWeek":
                return "P1W";
            case "RTEverySeason":
                return RTEverySeason(parameters.get(0),parameters.get(1));
            case "RTEveryMonth":
                return RTEveryMonth(parameters.get(0),parameters.get(1));
            case "PerDay":        
                return PerDay(parameters.get(0));    
            case "PerWeek":        
                return PerWeek(parameters.get(0));
            case "PerMonth":        
                return PerMonth(parameters.get(0));
            case "PerYear":        
                return PerYear(parameters.get(0));
            case "PerHour":
                return PerHour(parameters.get(0));
            case "DayDayPart":
                return DayDayPart(parameters.get(0),parameters.get(1));
            default:    
                break;
        }
        return "";
    }

    private String PeriodicWeekDays(String day) {
        return "XXXX-WXX-"+Utilities.GetDayValue(day);
    }

    private String EveryMonthYear(String month) {
        String monthNum = String.valueOf(Utilities.GetMonthValue(month));
        if(monthNum.length()==1)
            monthNum="0"+monthNum;
        String calendarType = Utilities.GetCalanderType(month);
        if(calendarType.equals("G"))
            return "XXXX-"+monthNum+"(Georgian)";   
        else if(calendarType.equals("A"))
            return "XXXX-"+monthNum+"(Arabic)";
        else 
            return "XXXX-"+monthNum;
    }

    private String RTEveryYear(String relTime) {
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+"P1Y";
    }
    private String RTEveryMonthNoName(String relTime) {
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+"P1M";
    }
    private String RTEveryWeek(String relTime) {
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+"P1W";
    }
    private String EverySeason(String season) {
        return Utilities.GetNormalValueSeasonYear(season);
    }

    private String RTEverySeason(String relTime, String season) {
        String temp = EverySeason(season);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+temp;
    }

    private String RTEveryMonth(String relTime, String month) {
        String temp = EveryMonthYear(month);
        String mode = Utilities.GetMode(relTime);
        return "mode=\""+mode+"\" "+temp;
    }

    private String PerDay(String num) {
        return "P"+Integer.valueOf(num)+"D";
    }

    private String PerWeek(String num) {
        return "P"+Integer.valueOf(num)+"W";
    }

    private String PerMonth(String num) {
        return "P"+Integer.valueOf(num)+"M";
    }

    private String PerYear(String num) {
        return "P"+Integer.valueOf(num)+"Y";
    }
    private String PerHour(String num) {
        return "PT"+Integer.valueOf(num)+"H";
    }
    private String DayDayPart(String Day,String dayPart) {
        return PeriodicWeekDays(Day)+EveryDayPart(dayPart);
    }
    private String EveryDayPart(String dayPart) {
        return Utilities.GetPartOfDayForSet(dayPart);
    }
}
