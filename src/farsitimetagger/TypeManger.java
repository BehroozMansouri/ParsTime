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

    public TypeManger() throws FileNotFoundException, IOException
    {
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
    
}
