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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author zahedi
 */
public class PatternManager {
    
    static HashMap<String,Integer> rules;
    
    public PatternManager() throws IOException
    {
        rules = SetRules("Patterns");
    }
    
    public int GetPatternId (String pattern)
    {
        return rules.get(pattern);
    }
    
    public boolean IsStackMeaningFull(String stack)
    {
        for(String rule : rules.keySet())
            if(rule.contains(stack.trim()))
                return true;
        return false;
    }
    public String CheckRule(String stack)
    {
        stack=stack.trim();
        while(!stack.isEmpty())
        {
            for(String rule : rules.keySet())
                if(stack.equals(rule))
                    return stack;
            if(stack.lastIndexOf(" ")==-1)
                return "";
            stack = stack.substring(0, stack.lastIndexOf(" "));
            stack = stack.trim();
        }
        return "";
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
    private boolean compareString(String one, String two)
    {
        if(one.length()!=two.length())
            return false;
        for(int i=0; i<one.length();i++)
            if(one.charAt(i)!=two.charAt(i))           
                return false;            
                
        return true;
    }
    private HashMap<String,Integer> SetRules(String ruleDirectory) throws FileNotFoundException, IOException 
    {
        HashMap<String,Integer> result = new HashMap<>();
        String RulesDirectoryPath = new File("").getAbsolutePath()+"\\"+ruleDirectory;
        for(File file : new File(RulesDirectoryPath).listFiles())
        {   
            System.out.println(file.getName());
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()),"UTF8")))
            {
                String line = br.readLine();
                while ((line=br.readLine())!= null)
                {
                    result.put(line.split("#")[0],Integer.valueOf(line.split("#")[1]));
                }
            }
        }
        return result;
    }
    
}
