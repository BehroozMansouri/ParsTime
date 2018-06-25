/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import edu.stanford.nlp.util.Pair;
import static farsitimetagger.xmlReaderForTextProcessing.GTEMainMethod;
import ir.ac.itrc.qqa.nlp.PersianTools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author zahedi
 */
public class TestConsole {

    public static <K, V extends Comparable<? super V>> Map<K, V>
            sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void file() throws FileNotFoundException, IOException {
        HashMap<String, Integer> querycount = new HashMap<String, Integer>();
        PrintWriter pw = new PrintWriter("E:\\Query Log Analysis Papers\\Queries with temporal expressions\\Queries dataset\\Alltemporals.txt");
        for (File file : new File("E:\\Query Log Analysis Papers\\Queries with temporal expressions\\Queries dataset\\result").listFiles()) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))) {
                String line = "";
                while ((line = in.readLine()) != null) {
                    line = line.replaceAll("\"", "");
                    line = line.replaceAll("\'", "");
                    //line = line.replaceAll("/","");
                    line = line.trim();
                    if (!line.isEmpty()) {
                        querycount.put(line.split(",")[0], Integer.valueOf(line.split(",")[line.split(",").length - 1]));
                    }
                }
            }
        }
        sortByValue(querycount);
        String[] strarr = new String[querycount.keySet().size()];
        int i = 0;
        for (String str : querycount.keySet()) {
            strarr[i++] = str;
        }
        Random r = new Random();
        int c = 1;
        int seed = 0;
        int key = 1;
        int temp = 3400;
        while (c <= 10000) {
            if (seed == 10) {
                temp = 200;
            }

            int index = seed * 3000 + r.nextInt(temp) + 1;
            pw.println(strarr[index]);
            if (key == 1000) {
                seed++;
                key = 1;
            }
            key++;
            c++;
        }

        pw.close();
    }

    public static void SplitFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(new File("E:\\Java Libraries\\FarsiTimeTagger\\1.csv")))) {
            String line = br.readLine();
            int counter = 1;
            int fileCounter = 1;
            PrintWriter pw = new PrintWriter("E:\\Java Libraries\\FarsiTimeTagger\\SplitFile\\" + fileCounter + ".txt");
            pw.println();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                pw.println(line);
                counter++;
                if (counter == 500) {
                    counter = 1;
                    fileCounter++;
                    pw.close();
                    pw = new PrintWriter("E:\\Java Libraries\\FarsiTimeTagger\\SplitFile\\" + fileCounter + ".txt");
                    pw.println();
                }
            }
        }

    }
    public static double count = 0.0;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {

//        HashMap<String,String> result = GTEMainMethod("C:\\Mansouri\\Ham3");
//        PrintWriter pwa = new PrintWriter("C:\\Mansouri\\dateMap.txt");
//        for(File file:new File("C:\\Mansouri\\Main Data").listFiles())
//        {
//            String name = file.getName();
//            String date = result.get(name);
//            pwa.println(name+","+date);
//        }
//        pwa.close();
//        if(1==1)return;
//        for(File file : new File("C:\\Mansouri\\Data1").listFiles())
//        {
//            
//            String textFile = ReadTextFile(file).trim();
//            String[]parts = textFile.split("\\.");
//            PrintWriter pw = new PrintWriter("C:\\Mansouri\\Data2\\"+file.getName());
//            for(String part:parts)
//                pw.println(part+".");
//            pw.close();
//        }
//         
//         if(1==1)return;
        FarsiTimeTagger fr = new FarsiTimeTagger();
//        for(String key : temp.keySet())
//            System.out.println(key+","+temp.get(key));

        for (File file : new File("E:\\TestData").listFiles()) {
            //List<String> temporalExpr = new ArrayList<String>();
            String textFile = ReadTextFile(file);
            //HashMap<String, Double> temp = fr.FindPatterns(textFile, "", temporalExpr);
            List<String> temp = fr.FindTimeTags(textFile, "1397-1-20");
            try (PrintWriter pw = new PrintWriter("E:\\TestData2\\" + file.getName())) {
                for (String key : temp) {
                    pw.println(key);
                }
            }
        }

        if (1 == 1) {
            return;
        }

    }

    private static String ReadTextFile(File file) throws FileNotFoundException, IOException {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
        }
        return result;
    }
}
