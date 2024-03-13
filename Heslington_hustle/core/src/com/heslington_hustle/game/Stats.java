package com.heslington_hustle.game;

import java.util.Objects;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Stats {
    public static int study;
    public static int recreation;
    public static int eat;
    public Stats(){
        study = 0;
        recreation = 0;
        eat = 0;
    }
    public static void log(String activity) throws Exception{
        if(Objects.equals(activity, "study")){
            study += 1;
        } else if (Objects.equals(activity, "recreation")) {
            recreation += 1;
        } else if (Objects.equals(activity, "eat")) {
            eat += 1;
        }else {
            throw new Exception("The statistic you are trying to log doesn't exist");
        }
    }
    public static Dictionary<String, Integer> getStats(){
        Dictionary<String, Integer> statistics = new Hashtable<>();
        statistics.put("study", eat);
        statistics.put("recreation",recreation);
        statistics.put("eat",eat);
        statistics.put("score", getScore());
        return statistics;
    }
    public static int getScore(){
        //Method to calculate the score
        return 0;
    }
}
