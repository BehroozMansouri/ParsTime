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
        for(File file : new File(new File("").getAbsolutePath()+"\\"+ruleDirectory).listFiles())
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")))
        {
            String line=br.readLine();
            while ((line=br.readLine())!= null)
                ruleMap.put(Integer.valueOf(line.split("#")[0]), line.split("#")[1]);
        }
        tm = new TimeManager();
        dum = new DurationManager();
        sm = new SetManager();
        dm = new DateManager(); 
    }
       
    public String ConvertPatternToDate(int patternId, String orginalText, String Type, String refDate)
    {
        String rule = ruleMap.get(patternId);
        List<String> funcElements = null;
        String funcName= GetFunctionAndParameters(rule, orginalText,funcElements);;  
        switch (Type) {
            case "DATE":
                return dm.CallFunction(refDate, funcName, funcElements);
            case "TIME":
                return tm.CallFunction(refDate, funcName, funcElements);
            case "DURATION":
                return dum.CallFunction(refDate, funcName, funcElements);
            default:
                return sm.CallFunction(refDate, funcName, funcElements);
        }
    }
    
    private String GetFunctionAndParameters(String rule,String originalText,List<String> funcElements)
    {
        String[] ruleElements = rule.split(",");
        String funtionName = ruleElements[0];
        String[] textParts = originalText.split(" ");
        funcElements = new ArrayList<String>();
        for(int i=1; i<ruleElements.length;i++)
        {
            String value = ruleElements[i].substring(1);
            int index = Integer.valueOf(value);
            funcElements.add(textParts[index]);
        }
        return funtionName;
    }

}
