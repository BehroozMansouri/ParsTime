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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Behrooz
 */
public class RuleManager {
    
    TypeManger tm;
    TypeManger dum;
    TypeManger dm;
    TypeManger sm;
    String ruleDirectory = "Rules";
    protected HashMap<Integer,String> ruleMap;
    
    public RuleManager() throws IOException
    {
        ruleMap = new HashMap<Integer,String>();
        RuleUtilities.Initialize();
        for(File file : new File(new File("").getAbsolutePath()+"\\"+ruleDirectory).listFiles())
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")))
        {
            System.out.println(file.getName());
            String line=br.readLine();
            while ((line=br.readLine())!= null)
                ruleMap.put(Integer.valueOf(line.split("#")[0]), line.split("#")[1]);
        }
        tm = new TimeManager();
        dum = new DurationManager();
        sm = new SetManager();
        dm = new DateManager(); 
    }
       
    /*
        Takes PatternId to find rule,
        original normalized Text to get value
        type to find function
        refDate for relative dates
    */
    public String ConvertPatternToDate(int patternId, String orginalText, String Type, String refDate)
    {
        String rule = ruleMap.get(patternId);
        List<String> funcElements = GetFunctionAndParameters(rule, orginalText);
        String funcName= funcElements.remove(0);  
        switch (Type) {
            case "DATE":
                return dm.CallFunction(refDate, funcName, funcElements);
            case "TIME":
                return tm.CallFunction(refDate, funcName, funcElements);
            case "DURATION":
                return dum.CallFunction(refDate, funcName, funcElements);
//            case "DURATION":
//                return dum.CallFunction(refDate, funcName, funcElements);
            default:
                return sm.CallFunction(refDate, funcName, funcElements);
        }
    }
    
    private List<String> GetFunctionAndParameters(String rule,String originalText)
    {
        List<String> result = new ArrayList<String>();
        //System.out.println(originalText);
        //System.out.println(rule);
        String[] ruleElements = rule.split(",");
        result.add(ruleElements[0]);//Function Name
        String[] textParts = originalText.split(" ");
        for(int i=1; i<ruleElements.length;i++)
        {
            String value = ruleElements[i].substring(1);
            int index = Integer.valueOf(value);
            result.add(textParts[index]);
        }
        return result;
    }

}
