/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farsitimetagger;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author b.mansouri
 */
public class CalendarConvetor {
    
    public static String GregorianToJalali(String GregorianDate){
        
        int gy = Integer.valueOf(GregorianDate.split("-")[0]);
        int gm = Integer.valueOf(GregorianDate.split("-")[1]);
        int gd = Integer.valueOf(GregorianDate.split("-")[2]);
        int[] g_d_m = {0,31,59,90,120,151,181,212,243,273,304,334};
        int jy;
        if(gy>1600){
            jy=979;
            gy-=1600;
        }
        else{
            jy=0;
            gy-=621;
        }
        int gy2 = (gm > 2)?(gy + 1):gy;
        int days = (365 * gy) + ((int)((gy2 + 3) / 4)) - ((int)((gy2 + 99) / 100)) + ((int)((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int)(days / 12053));
        days %= 12053;
        jy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            jy+=(int)((days-1)/365);
            days=(days-1)%365;
        }
        int jm = (days < 186)?1 + (int)(days / 31):7 + (int)((days - 186) / 30);
        int jd = 1 + ((days < 186)?(days % 31):((days - 186) % 30));
        return jy+"-"+jm+"-"+jd;
    }

    public static String JalaliToGregorian(String JalaliDate){
        int jy = Integer.valueOf(JalaliDate.split("-")[0]);
        int jm = Integer.valueOf(JalaliDate.split("-")[1]);
        int jd = Integer.valueOf(JalaliDate.split("-")[2]);
        
        int gy;
        if(jy>979){
            gy=1600;
            jy-=979;
        }
        else{
            gy=621;
        }
        int days = (365 * jy) + (((int)(jy / 33)) * 8) + ((int)(((jy % 33) + 3) / 4)) + 78 + jd + ((jm < 7)?(jm - 1) * 31:((jm - 7) * 30) + 186);
        gy += 400 * ((int)(days / 146097));
        days %= 146097;
        if(days > 36524){
            gy += 100 * ((int)(--days / 36524));
            days %= 36524;
            if (days >= 365)
                days++;
        }
        gy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            gy += (int)((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int gd = days + 1;
        int[] sal_a = {0,31,((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))?29:28,31,30,31,30,31,31,30,31,30,31};
        int gm;
        for(gm = 0;gm < 13;gm++){
            int v = sal_a[gm];
            if(gd <= v)
                break;
            gd -= v;
        }
        return gy+"-"+gm+"-"+gd;
    }
        
    public static String GregorianToArabic(String GeorgianDate){
        GeorgianDate = GeorgianDate.replaceAll("-", "/");
        Date date=new Date(GeorgianDate); ///هنا التاريخ الصليبي
        Calendar cl=Calendar.getInstance();
        cl.setTime(date);
        HijrahDate islamyDate=HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR),cl.get(Calendar.MONTH)+1, cl.get(Calendar.DATE)));
        return islamyDate.toString().split(" ")[2];
    }
    
    public static String ArabicToGregorian(String ArabicDate){
        String[] parts = ArabicDate.split("-");
        HijrahDate islamyDate = HijrahChronology.INSTANCE.date(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
        LocalDate ld = LocalDate.from(islamyDate);
        return ld.toString();
    }
    
    public static String ArabicToJalali(String ArabicDate){
        String georgianDate = ArabicToGregorian(ArabicDate);
        return GregorianToJalali(georgianDate);
    }
    
    public static String JalaliToArabic(String JalaliDate){
        String georgianDate = JalaliToGregorian(JalaliDate);
        return GregorianToArabic(georgianDate);
    }
    
}
