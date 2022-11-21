package com.driver.Converters;

public class TimeConverter {
    public static String intToString (int time){
        int temp = time;
        int hours = temp/60;
        int min = temp%60;
        String ans = hours+":"+min;

        return ans;
    }

    public static int StringToInt (String time){
        String[] str = time.split(":");
        int hours = Integer.parseInt(str[0]);
        int min = Integer.parseInt(str[1]);

        return 60*hours + min;
    }
}
