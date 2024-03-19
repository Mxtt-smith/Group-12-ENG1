package com.heslington_hustle.game;

import java.time.LocalTime;
import java.util.*;

/**
 * The Stats class represents the statistics of the game, including activities performed, study sessions,
 * and daily progress. It provides methods to log activity, retrieve statistics, manage daily progress,
 * and reset statistics.
 */
public class Stats {

    /** Counter for study sessions. */
    int study;

    /** Counter for recreation activities. */
    int recreation;

    /** Counter for eating activities. */
    int eat;

    /** Number of study sessions completed on the previous day. */
    int studiedYesterday;

    /** List containing statistics for each day. */
    List<Dictionary<String, Integer>> days;

    /**
     * Constructs a Stats object and initializes its variables.
     */
    public Stats(){
        study = 0;
        recreation = 0;
        eat = 0;
        studiedYesterday = 1;
        days = new ArrayList<>();
    }

    /**
     * Increments a counter for a given activity type.
     *
     * @param type the type of activity to log
     * @throws Exception if the specified activity type is invalid
     */
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

    /**
     * Retrieves the statistics of the current day.
     *
     * @return a dictionary containing the current statistics
     */
    public Dictionary<String, Integer> getStats(){
        Dictionary<String, Integer> statistics = new Hashtable<>();
        statistics.put("study", study);
        statistics.put("recreation",recreation);
        statistics.put("eat",eat);
        statistics.put("studiedYesterday", studiedYesterday);
        return statistics;
    }

    /**
     * Adds the statistics for the current day to the list of daily statistics.
     *
     * @param day the statistics for the current day
     */
    public void addDay(Dictionary<String, Integer> day) {
        days.add(day);
    }

    /**
     * Resets the statistics for the start of a new day.
     */
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

    /**
     * Retrieves the statistics for a given day.
     *
     * @param index the index of the day
     * @return {} the statistics for the specified day
     */
    public Dictionary<String, Integer> getDay (int index) {
        return days.get(index);
    }

    /**
     * Retrieves the total statistics for the entire game.
     *
     * @return an array containing the total study, recreation, and eating activities
     */
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

    /**
     * Resets the Stats instance to its initial state.
     */
    public void reset() {
        studiedYesterday = 1;
        recreation = 0;
        eat = 0;
        study = 0;
        days.clear();
        HeslingtonHustle.Day = 1;
        HeslingtonHustle.Energy = 100;
        HeslingtonHustle.hoursLeft = 16;
        HeslingtonHustle.Time = LocalTime.of(7, 30);
    }
}
