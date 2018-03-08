/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Behrooz
 */
public class TypeManger {
    protected HashMap<String,Integer> map_RelativeDays;
    public TypeManger() throws FileNotFoundException, IOException
    {
        map_RelativeDays = ReadMapFile(new File("").getAbsolutePath()+"\\RulesConvert\\RelativeDays.txt");
    }
    
    public String CallFunction(String refDate, String functionName, List<String> parameters)
    {
        return "";
    }
    
    private HashMap<String, Integer> ReadMapFile(String filePath) throws IOException {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8")))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                String key = line.split("#")[0];
                int val = Integer.valueOf(line.split("#")[1]);
                result.put(key, val);
            }
        }
        return result;
    }
    
    protected String GetRefDate(String refDate, String relativeDay) {
        int val = map_RelativeDays.get(relativeDay);
        
        if(val==0)
            return refDate;

        int day = Integer.valueOf(refDate.split("-")[2]);
        int month = Integer.valueOf(refDate.split("-")[1]);
        int year = Integer.valueOf(refDate.split("-")[0]);
        
        switch (val) {
            case -1:
                if(day-1>0)
                    day=day-1;
                else if(month-1>=7)
                {
                    month -=1;
                    day=30;
                }
                else if(month-1>0)
                {
                    month -= 1;
                    day=31;
                }
                else
                {
                    year -= 1;
                    month= 12;
                    if(year%4==3)
                        day=30;
                    else
                        day=29;
                }
                break;
            case 1:
                if(day+1<=30 && month!=12)
                    day++;
                else if(month!=12)
                {
                    if(month<=6 && day+1<=31)
                            day++;
                    else if(month+1<=12)
                    {
                        month++;
                        day=1;
                    }
                }
                else if(month==12)
                {
                    if(day+1<=29)
                        day++;
                    else if(day==29 && year%4==3)
                        day++;
                    else
                    {
                        day=month=1;
                        year++;
                    }
                }
                break;
            case 2:
                if(day+2<=30 && month!=12)
                    day+=2;
                else if(month!=12)
                {
                    if(month<=6 && day+2<=31)
                        day+= 2;
                    else 
                    {
                        if(month<=6)
                            day = (day+2)%31;
                        else
                            day = (day+2)%30;
                        month++;
                    }
                }
                else if(month==12)
                {
                    if((day+2<=29)||(year%4==3 && day+2<=30))
                        day+= 2;
                    else
                    {
                        year++;
                        month=1;
                        if(year%4==3)    
                            day = (day+2)%30;
                        else
                            day = (day+2)%29;
                    }
                }
                break;
            case -2:
                if(day-2>0)
                    day -= 2;
                else if(month-1>=7)
                {
                    month -= 1;
                    day=30;
                }
                else if(month-1>0)
                {
                    month -= 1;
                    day=31;
                }
                else
                {
                    year -= 1;
                    month= 12;
                    if(year%4==3)
                        day=30;
                    else
                        day=29;
                }
                break;
            default:
                return refDate;
        }
        return refDate;
    }
}
