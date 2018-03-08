/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import edu.stanford.nlp.util.Pair;
import ir.ac.itrc.qqa.nlp.PersianTools;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * @author zahedi
 */
public class FarsiTimeTagger {
    PatternManager pm;
    Normalizer nr;
    RuleManager rm;
    TemporalAnnotator ta;
    
    public FarsiTimeTagger() throws IOException
    {
        Utilities.Initialize();
        ///////////////////////////////////////////
        System.out.println("Normalizer Loading...");
        nr = new Normalizer();
        ///////////////////////////////////////////
        System.out.println("Temporal Annotator Loading...");
        ta = new TemporalAnnotator();
        ///////////////////////////////////////////        
        System.out.println("Reading Pattern Files...");
        pm = new PatternManager();
        ///////////////////////////////////////////       
        //This instance manages the conversion of pattern to dates
        System.out.println("Reading Rules Files...");
        rm = new RuleManager();
    }
    
    public HashMap<String,Double> FindPatterns (String text, String refDate,List<String> temporalExp) throws IOException
    {
        //This is the collection that results are stored in it.
        HashMap<String,Double> result = new HashMap<String,Double>();
        
        if(refDate!=null)
            refDate = Utilities.GetNormalValue(refDate);
        //This stack manages the rules and terms
        String stack = "";
        String[] sentences = text.split("\n|\\.");
        
      
        for(String sentence : sentences)
        {
            String tt = sentence.trim();
                    
            //String nsentence = nr.Nomalize(sentence);   
            String nsentence = nr.Nomalize(tt);   
            Map<Integer, String> normalized = nr.GetNormalizedValues();
            
            // This means terms are extracted in sentences by spliting sentences by one or more space
            String[] terms = ta.Annotate(nsentence);
            
            for(int pointer=0; pointer<terms.length; pointer++)
            {
                boolean carryOn = true;
                for(int i=pointer; i<terms.length && carryOn;i++)
                {
                    if(i==pointer)
                        stack+=terms[i];
                    else
                        stack+=" "+terms[i];

                    //When there is no temporal expression in stack it goes to next round 
                    //When stack can not determine if it is a meaningful temporal expression or not it takes another term
                    if (pm.IsStackMeaningFull(stack.trim()) && i+1<terms.length)
                        continue;
                    
                    carryOn = false;
                    String recognizedPattern = pm.CheckRule(stack);
                    if(recognizedPattern.isEmpty())
                    {
                        stack="";
                        break;
                    }
                    
                    String originalNormal = nsentence.split(" ")[pointer];
                    for(int j=pointer+1;j<=pointer+recognizedPattern.split(" ").length-1;j++)
                        originalNormal+=" "+nsentence.split(" ")[j];
                    String orginalText = GetOrginalText(nsentence,originalNormal,normalized);
                    
                    temporalExp.add(orginalText);
                    if(result.containsKey(recognizedPattern))
                        result.put(recognizedPattern, result.get(recognizedPattern)+1);
                    else
                        result.put(recognizedPattern, 1.0);
                    
                    //pp[1]="";
                    pointer+=(recognizedPattern.split(" ").length-1);
                    stack="";
                }
            }
        }
        return result;
    }
    
    public List<String> FindTimeTags (String text, String refDate) throws IOException
    {
        //This is the collection that results are stored in it.
        List<String> result = new ArrayList<String>();
        int ID = 0;
        
        if(refDate!=null)
            refDate = Utilities.GetNormalValue(refDate);
        //This stack manages the rules and terms
        String stack = "";
        String[] sentences = text.split("\n|\\.");
        
      
        for(String sentence : sentences)
        {
            String tt = sentence.trim();
                    
            //String nsentence = nr.Nomalize(sentence);   
            String nsentence = nr.Nomalize(tt);   
            Map<Integer, String> normalized = nr.GetNormalizedValues();
            
            // This means terms are extracted in sentences by spliting sentences by one or more space
            String[] terms = ta.Annotate(nsentence);
            
            for(int pointer=0; pointer<terms.length; pointer++)
            {
                boolean carryOn = true;
                for(int i=pointer; i<terms.length && carryOn;i++)
                {
                    if(i==pointer)
                        stack+=terms[i];
                    else
                        stack+=" "+terms[i];

                    //When there is no temporal expression in stack it goes to next round 
                    //When stack can not determine if it is a meaningful temporal expression or not it takes another term
                    if (pm.IsStackMeaningFull(stack.trim()) && i+1<terms.length)
                        continue;
                    
                    carryOn = false;
                    String recognizedPattern = pm.CheckRule(stack);
                    if(recognizedPattern.isEmpty())
                    {
                        stack="";
                        break;
                    }
                    
                    String originalNormal = nsentence.split(" ")[pointer];
                    for(int j=pointer+1;j<=pointer+recognizedPattern.split(" ").length-1;j++)
                        originalNormal+=" "+nsentence.split(" ")[j];
                    String orginalText = GetOrginalText(nsentence,originalNormal,normalized);
                    
                    String type = TemporalExpressionType(pm.GetPatternId(recognizedPattern));
                    
                    
                    String value = rm.ConvertPatternToDate(pm.GetPatternId(recognizedPattern), originalNormal,type,refDate);
                                        
                    result.add(GetTimeX3Value(value,type,orginalText,ID));
                    ID++;
                    
                    pointer+=(recognizedPattern.split(" ").length-1);
                    stack="";
                }
            }
        }
        return result;
    }
    
        
    public List<String> FindTimeTags (String text) throws IOException
    {
        return FindTimeTags(text, "");
    }
    
    private String GetOrginalText(String nsentence, String originalNormal, Map<Integer, String> normalized) {
        if(normalized.size()==0) return originalNormal;

        Map<Integer, String> newMap = new TreeMap(Collections.reverseOrder());
        newMap.putAll(normalized);
        
        int startindex = nsentence.indexOf(originalNormal);
        int eindex = startindex+originalNormal.length();
        String tempsent = nsentence.substring(0,eindex);
        for(int key : newMap.keySet())
        {
            String temp = newMap.get(key);            
            int value = key;
            if(value>=startindex && value<eindex)
            {
                String v1 = (temp.split("#")[0]);
                String v2 = (temp.split("#")[1]);
                String s1 = tempsent.substring(0, value);
                String s2 = tempsent.substring(value,value+v2.length()).replace(v2, v1);
                String s3 = tempsent.substring(value+v2.length());
                tempsent = s1+s2+s3;
            }
        }
        return tempsent.substring(startindex);
    }

    private String GetTimeX3Value(String value, String type, String orginalText, int ID) {
        return "<TIMEX3 tid=\"t"+ID+"\" type=\""+type+"\" value=\""+value+"\">"+orginalText+"</TIMEX3>";
    }
    
    /**
     * Based on predefined Ids in files we are able to recognize the type of each temporal expression
     * @param patternId
     * @return
     */
    public String TemporalExpressionType(int patternId)
    {
        if(patternId<300)
            return "DATE";
        else if(patternId>300 && patternId<400)
            return "SET";
        else if(patternId>600 && patternId<700)
            return "DURATION";
        else if(patternId>400 && patternId<500)
            return "TIME";
        return "";
    }
}
