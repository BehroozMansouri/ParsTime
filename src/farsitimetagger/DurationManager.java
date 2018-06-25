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
public class DurationManager extends TypeManger{
    
    public DurationManager() throws IOException
    {
    }
    @Override
    public String CallFunction(String refDate, String functionName, List<String> parameters)
    {
        if(null!=functionName)
            switch (functionName) {
            case "ForNumDays":
                return ForNumDays(parameters.get(0));
            case "ForNumNights":
                return ForNumDays(parameters.get(0));
            case "ForNumWeeks":
                return ForNumWeeks(parameters.get(0));
            case "ForNumMonths":
                return ForNumMonths(parameters.get(0));
            case "ForNumYears":
                return ForNumYears(parameters.get(0));
            case "ForNumDecades":
                return ForNumDecades(parameters.get(0));
            case "ForNumCenturies":
                return ForNumCenturies(parameters.get(0));  
            case "ForNumHours":
                return ForNumHours(parameters.get(0));
            case "ForNumMins":
                return ForNumMins(parameters.get(0));
            case "ForNumSeconds":
                return ForNumSeconds(parameters.get(0));
            case "ForNumMiliSeconds":
                return ForNumMiliSeconds(parameters.get(0));
            case "ForHalfHour":
                return ForNumMins("30");
            case "ForNumHourMins":
                return ForNumHourMins(parameters.get(0),parameters.get(1));
            case "ForNumHourMinSecs":        
                return ForNumHourMinSecs(parameters.get(0),parameters.get(1),parameters.get(2));    
            case "ForNumHourHalf":        
                return ForNumHourHalf(parameters.get(0));
            case "ForNumHourQuarter":        
                return ForNumHourQuarter(parameters.get(0));
            default:    
                break;
        }
        return "";
    }

    private String ForNumDays(String daynumber) {
        int val = Integer.valueOf(daynumber);
        return "P"+val+"D";
    }

    private String ForNumWeeks(String weeknumber) {
        int val = Integer.valueOf(weeknumber);
        return "P"+val+"W";
    }

    private String ForNumMonths(String monthnumber) {
        int val = Integer.valueOf(monthnumber);
        return "P"+val+"M";
    }

    private String ForNumYears(String yearnumber) {
        int val = Integer.valueOf(yearnumber);
        return "P"+val+"Y";
    }

    private String ForNumDecades(String decadenumber) {
        int val = Integer.valueOf(decadenumber)*10;
        return "P"+val+"Y";
    }

    private String ForNumCenturies(String centurynumber) {
        int val = Integer.valueOf(centurynumber)*100;
        return "P"+val+"Y";
    }

    private String ForNumHours(String hournumber) {
        int val = Integer.valueOf(hournumber);
        return "PT"+val+"H";
    }

    private String ForNumMins(String minnumber) {
        int val = Integer.valueOf(minnumber);
        return "PT"+val+"M";
    }

    private String ForNumSeconds(String secondnumber) {
        int val = Integer.valueOf(secondnumber);
        return "PT"+val+"S";
    }

    private String ForNumMiliSeconds(String millisecondnumber) {
        double val = Integer.valueOf(millisecondnumber)/1000;
        return "PT"+val+"S";
    }

    private String ForNumHourMins(String hour, String min) {
        int val = (Integer.valueOf(hour)*60)+Integer.valueOf(min);
        return "PT"+val+"M";
    }

    private String ForNumHourMinSecs(String hour, String min, String sec) {
        int val = (Integer.valueOf(hour)*3600)+(Integer.valueOf(min)*60)+Integer.valueOf(sec);
        return "PT"+val+"S";
    }

    private String ForNumHourHalf(String hour) {
        int val = (Integer.valueOf(hour)*60)+30;
        return "PT"+val+"M";
    }

    private String ForNumHourQuarter(String hour) {
        int val = (Integer.valueOf(hour)*60)+15;
        return "PT"+val+"M";
    }
}
