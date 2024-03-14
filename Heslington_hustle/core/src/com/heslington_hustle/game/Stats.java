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
        studiedYesterday = 1;
        days = new ArrayList<>();
    }

    // increment an activity's counter
    public void log(Activity.ActivityType type) throws Exception{
        switch (type) {
            case RECREATION:
                recreation++;
                break;
            case STUDY:
                study++;
                break;
            case EAT:
                eat++;
                break;
            default:
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
        HeslingtonHustle.Day++;
    }

    public Dictionary<String, Integer> getDay (int index) {
        return days.get(index);
    }

    public int getScore(){
        //Method to calculate the score
        return 0;
    }
}
