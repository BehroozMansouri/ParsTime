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
public class Convertor {
    List<String> months,days,seasons,past,next,relativeTime,start,relativeDays,dayPart;
    public Convertor() throws IOException
    {
       String ConversionDirectoryPath = new File("").getAbsolutePath()+"\\Conversion\\";
       months = ReadListFile(ConversionDirectoryPath+"months.txt");
       days = ReadListFile(ConversionDirectoryPath+"days.txt");
       seasons = ReadListFile(ConversionDirectoryPath+"seasons.txt");
       past = ReadListFile(ConversionDirectoryPath+"past.txt");
       next = ReadListFile(ConversionDirectoryPath+"next.txt");
       relativeTime = ReadListFile(ConversionDirectoryPath+"relativeTime.txt");
       start = ReadListFile(ConversionDirectoryPath+"start.txt");
       relativeDays = ReadListFile(ConversionDirectoryPath+"relativeDays.txt");
       dayPart = ReadListFile(ConversionDirectoryPath+"dayPart.txt");
    }
    private List<String> ReadListFile(String filePath) throws FileNotFoundException, IOException {
        ArrayList<String> result = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8")))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                char[]va = line.toCharArray();
                result.add(line);
            }
        }
        return result;
    }
    
    String ContainFromList (String text,List<String> lst)
    {
        for(String temp : lst)
            if(text.contains(temp))
                return temp;
        return "";
    }
 
    String ReplaceMent(String input,String title, List<String>lst,List<Pair<String,String>> changes)
    {
        if(lst==null)
        {
            String[] terms = input.split(" ");
            for(String term : terms)
            for(int i =1 ; i<2050 ;i++)
                if(term.contains(String.valueOf(i)))
                {
                    input = input.replaceFirst(String.valueOf(i), title);
                    changes.add(new Pair<String,String>(String.valueOf(i),title));
                }
            return input;
        }
        while(true)
        {
            String item = ContainFromList(input,lst);
            if(item.isEmpty())
                break;
            input = input.replaceFirst(item, title);
            changes.add(new Pair<String,String>(item,title));
        }
        return input;
    }
    
    public String DecodeToRuleFormat(String input,List<Pair<String,String>> replaced)
    {
        for(Pair<String,String> temp : replaced)
            input = input.replaceFirst(temp.first, temp.second);
        return input;
    }
    
    public String EncodeToRuleFormat(String input,List<Pair<String,String>> replaced)
    {
        replaced= new ArrayList<Pair<String,String>>();
        input = ReplaceMent(input,"Month",months,replaced);
        input = ReplaceMent(input,"Day",days,replaced);
        input = ReplaceMent(input,"Season",seasons,replaced);
        input = ReplaceMent(input,"Past",past,replaced);
        input = ReplaceMent(input,"Next",next,replaced);
        input = ReplaceMent(input,"RT",relativeTime,replaced);
        input = ReplaceMent(input,"RD",relativeDays,replaced);
        input = ReplaceMent(input,"DP",dayPart,replaced);
        input = ReplaceMent(input,"Start",start,replaced);
        input = ReplaceMent(input,"Num",null,replaced);
        return input;
    }
}
