/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import edu.stanford.nlp.util.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Behrooz
 */
public class TemporalAnnotator {
    
    String filePath = "\\Annotation";
    List<String> months,days,seasons,past,next,relativeTime,dayPart,relativeDays,relativeYears;
    
    public TemporalAnnotator() throws IOException
    {
        String ConversionDirectoryPath = new File("").getAbsolutePath()+filePath+"\\";
        months = ReadListFile(ConversionDirectoryPath+"months.txt");
        days = ReadListFile(ConversionDirectoryPath+"days.txt");
        seasons = ReadListFile(ConversionDirectoryPath+"seasons.txt");
        past = ReadListFile(ConversionDirectoryPath+"past.txt");
        next = ReadListFile(ConversionDirectoryPath+"next.txt");
        relativeTime = ReadListFile(ConversionDirectoryPath+"relativeTime.txt");
        relativeDays = ReadListFile(ConversionDirectoryPath+"relativeDays.txt");
        dayPart = ReadListFile(ConversionDirectoryPath+"dayPart.txt");
        relativeYears = ReadListFile(ConversionDirectoryPath+"relativeYears.txt");
    }
    
    public String[] Annotate(String inputText)
    {
        String[] terms = inputText.split(" ");
        for(int i=0;i<terms.length;i++)
            terms[i] = GetAnnotatedValue(terms[i]);
        return terms;
    }
    
    private List<String> ReadListFile(String filePath) throws FileNotFoundException, IOException {
        ArrayList<String> result = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8")))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                result.add(line);
            }
        }
        return result;
    }

    private String GetAnnotatedValue(String term) {
        if(isNumber(term))
            return "Num";
        else if (seasons.contains(term))
            return "Season";
        else if (months.contains(term))
            return "Month";
        else if (days.contains(term))
            return "Day";
        else if (past.contains(term))
            return "Past";
        else if (next.contains(term))
            return "Next";
        else if (relativeTime.contains(term))
            return "RT";
        else if (relativeDays.contains(term))
            return "RD";
        else if (dayPart.contains(term))
            return "DP";
        else if (relativeYears.contains(term))
            return "RY";
        else if (IsTime(term))
            return "T";
        else if (IsDate(term))
            return "Date";
        return term;
    }

    private boolean isNumber(String term) {
        try
        {
            term = term.trim();
            int val = Integer.parseInt(term.trim());
            return true;
        }
        catch(Exception er)
        {
            try
            {
                term=term.substring(1);
                int val = Integer.parseInt(term.trim());
                return true;
            }
            catch(Exception er2)
            {
                return false;
            }
        }
    }

    private boolean IsTime(String term) {
        if(!term.contains(":"))
            return false;
        String[]parts = term.split(":");
        for(String part:parts)
            if(!Utilities.IsNumber(part))
                return false;
        if(parts.length==2)
        {
            int hour = Integer.valueOf(parts[0]);
            int min = Integer.valueOf(parts[1]);
            return hour>=0 && hour<=24 && min>=0 && min<=60;
        }
        else if(parts.length==3)
        {
            int hour = Integer.valueOf(parts[0]);
            int min = Integer.valueOf(parts[1]);
            int sec = Integer.valueOf(parts[2]);
            return hour>=0 && hour<=24 && min>=0 && min<=60 && sec>=0 && sec<=60;
        }
        else if(parts.length==4)
        {
            int hour = Integer.valueOf(parts[0]);
            int min = Integer.valueOf(parts[1]);
            int sec = Integer.valueOf(parts[2]);
            int milsec = Integer.valueOf(parts[3]);
            return hour>=0 && hour<=24 && min>=0 && min<=60 && sec>=0 && sec<=60;
        }

        return false;
    }

    private boolean IsDate(String term) {
        String[]parts = term.split("/");
        if(parts.length!=3)
            return false;
        if(!(Utilities.IsNumber(parts[0])&&Utilities.IsNumber(parts[1])&&Utilities.IsNumber(parts[2])))
            return false;
        int month = Integer.valueOf(parts[1]);
        if(month>12 && month<1)
            return false;
        if(Integer.valueOf(parts[0])<=0 && Integer.valueOf(parts[2])<=0)    
            return false;
        return true;
    }
}
