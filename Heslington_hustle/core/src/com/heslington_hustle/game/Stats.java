package com.heslington_hustle.game;

import java.util.*;

// This is to keep a count of how many activities they have completed
public class Stats {
    int study;
    int recreation;
    int eat;
    int studiedYesterday;
    List<Dictionary<String, Integer>> days;

    public Stats(){
        study = 0;
        recreation = 0;
        eat = 0;
        studiedYesterday = 0;
        days = new ArrayList<Dictionary<String, Integer>>();
    }

    // increment an activity's counter
    public void log(String activity) throws Exception{
        if(Objects.equals(activity, "study")){
            study += 1;
        } else if (Objects.equals(activity, "recreation")) {
            recreation += 1;
        } else if (Objects.equals(activity, "eat")) {
            eat += 1;
        } else {
            throw new Exception("The statistic you are trying to log doesn't exist");
        }
    }

    //
    public Dictionary<String, Integer> getStats(){
        Dictionary<String, Integer> statistics = new Hashtable<>();
        statistics.put("study", eat);
        statistics.put("recreation",recreation);
        statistics.put("eat",eat);
        statistics.put("score", getScore());
        statistics.put("studiedYesterday", studiedYesterday);
        return statistics;
    }

    public void addDay(Dictionary<String, Integer> day) {
        days.add(day);
    }

    public void newDay() {
        recreation = 0;
        eat = 0;
        studiedYesterday = study;
        study = 0;
    }

    public Dictionary<String, Integer> getDay (int index) {
        return days.get(index);
    }

    public int getScore(){
        //Method to calculate the score
        return 0;
    }
}
