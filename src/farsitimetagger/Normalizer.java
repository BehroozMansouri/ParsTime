/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import ir.ac.itrc.qqa.nlp.PersianTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Behrooz
 */
public class Normalizer {
    
    HashMap<String,String> replaceMentMap;
    Map<String,String> numbers;
    
    PersianTools tools;
    Map<Integer, String> normalizedValue;

    public Normalizer() throws FileNotFoundException, IOException
    {
        String RulesDirectoryPath = new File("").getAbsolutePath()+"\\Normalizer\\";
        replaceMentMap = new HashMap<String,String>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(RulesDirectoryPath+"replacements.txt"))))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                String[] temp = line.split("#");
                replaceMentMap.put(temp[0], temp[1]);
            }
        }
        HashMap<String,String> templst = new HashMap<String,String>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(RulesDirectoryPath+"numbers.txt"))))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
            {
                String[] temp = line.split("#");
                templst.put(temp[0], temp[1]);
            }
        }
        numbers = new TreeMap<String, String>(
        new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() > s2.length()) {
                    return -1;
                } else if (s1.length() < s2.length()) {
                    return 1;
                } else {
                    return s1.compareTo(s2);
                }
            }
        });
        numbers.putAll(templst);
    }
    
    public Map<Integer, String> GetNormalizedValues()
    {
        return normalizedValue;
    }
    
    public String Nomalize(String text) throws FileNotFoundException
    {
        TreeMap<Integer, String> tempNormalizedValue = new TreeMap<Integer, String>();
        String tempText = text;
        for(int i=0; i<replaceMentMap.size();)
        {
            String key = (String) replaceMentMap.keySet().toArray()[i];
            int index = text.indexOf(key);
            if(index!=-1)
            {
                tempNormalizedValue.put(index,key+"#"+replaceMentMap.get(key));
                String tempstr ="";
                for(int h=0;h<key.length();h++)
                    tempstr+="X";
                text = text.replaceFirst(key, tempstr);
                continue;
            }
            i++;
        }
        
        
        for(int i =0 ;i<numbers.size() ;)
        {
            String key = (String) numbers.keySet().toArray()[i];
            int index = text.indexOf(key);
            if(index!=-1)
            {
                tempNormalizedValue.put(index,key+"#"+numbers.get(key));
                String tempstr ="";
                for(int h=0;h<key.length();h++)
                    tempstr+="X";
                text = text.replaceFirst(key, tempstr);
                continue;
            }
            i++;
        }
        
        normalizedValue = new TreeMap<Integer, String>();
        for(int key : tempNormalizedValue.keySet())
        {
            String temp = tempNormalizedValue.get(key);            
            String v1 = (temp.split("#")[0]);
            int indexof = tempText.indexOf(v1);
            String v2="";
            if(temp.split("#").length<2 ||temp.split("#")[1]==null)
                v2=" ";
            else
                v2 = (temp.split("#")[1]);
            tempText = tempText.replaceFirst(v1,v2);
            normalizedValue.put(indexof,v1+"#"+v2);
        }
        
        
        
        return tempText;
    }
    
}
