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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Behrooz
 */
public class RuleUtilities {
    static HashMap<String,Integer> relativeDay;
    static HashMap<String,Integer> relativeYear;
    static String directoryPath = new File("").getAbsolutePath()+"\\RulesConvert\\";;
    public static void Initialize () throws IOException
    {
       relativeDay = ReadListFile(directoryPath+"relativeDay.txt");
       relativeYear = ReadListFile(directoryPath+"relativeYear.txt");
    }
    
    public static int GetRelativeYear(String relativeYearStr)
    {
        return relativeYear.get(relativeYearStr);
    }
    
    public static int GetRelativeDay(String relativeDayStr)
    {
        return relativeDay.get(relativeDayStr);
    }
    
    private static HashMap<String,Integer> ReadListFile(String filePath) throws IOException {
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8")))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                String[] parts=line.split("#");
                result.put(parts[0],Integer.valueOf(parts[1]));
            }
        }
        return result;
    }
}
