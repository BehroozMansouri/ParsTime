/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Administrator
 */
public class xmlReaderForTextProcessing {
    static String GetFileExtensionName(File file)
    {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".")!=-1 && fileName.lastIndexOf(".")!=0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        return "";
    }
    
    
    
    
    public static HashMap<String,String> GTEMainMethod(String filePath)throws FileNotFoundException, IOException
    {
        HashMap<String,String> result = new HashMap<String,String>();
        
        
        for(File folder : new File(filePath).listFiles())
        {
            for(File file : folder.listFiles())
            {
                try 
                {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(file);	
                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("Doc");
                    for (int temp = 0; temp < nList.getLength(); temp++) 
                    {                
                        Node nNode = nList.item(temp);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                        {
                            Element eElement = (Element) nNode;
                            String Id = eElement.getElementsByTagName("DocID").item(0).getTextContent();
                            String date = eElement.getElementsByTagName("DATE").item(1).getTextContent();
                            result.put(Id,date);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    

    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        int d = Integer.parseInt(str);  
        if(d>=1000)
            return true;
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      
      return false;  
    }

    private static boolean IsYearIndicator(String term) {
        try{
            int temp = Integer.parseInt(term);
            if(temp>=50 && temp<=99)
                return true;
            if(temp>=1800 && temp<=2100)
                return true;
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }
}
