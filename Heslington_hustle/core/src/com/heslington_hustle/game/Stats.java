package com.heslington_hustle.game;

import java.time.LocalTime;
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
                System.out.println("Study incremented");
                study++;
                break;
            case EAT:
                eat++;
                break;
            default:
                throw new Exception("The statistic you are trying to log doesn't exist");
        }
    }

    public Dictionary<String, Integer> getStats(){
        Dictionary<String, Integer> statistics = new Hashtable<>();
        statistics.put("study", study);
        statistics.put("recreation",recreation);
        statistics.put("eat",eat);
        statistics.put("studiedYesterday", studiedYesterday);
        return statistics;
    }

    public void addDay(Dictionary<String, Integer> day) {
        days.add(day);
    }

    public void newDay() {
        studiedYesterday = study;
        recreation = 0;
        eat = 0;
        study = 0;
        HeslingtonHustle.Day++;
        HeslingtonHustle.Energy = 100;
        HeslingtonHustle.hoursLeft = 16;
        HeslingtonHustle.Time = LocalTime.of(7, 30);
    }

    public Dictionary<String, Integer> getDay (int index) {
        return days.get(index);
    }

    public int[] getTally(){
        int totalStudy = 0;
        int totalRecreation = 0;
        int totalEat = 0;
        for (int i = 0; i < days.size(); i++) {
            Dictionary<String, Integer> day = getDay(i);
            totalStudy += day.get("study");
            totalRecreation += day.get("recreation");
            totalEat += day.get("eat");
        }
        return new int[] {totalStudy, totalRecreation, totalEat};
    }
}
