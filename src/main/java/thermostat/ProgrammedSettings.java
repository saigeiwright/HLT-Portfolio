package thermostat;

//Used with the Thermostat.java example, Chapter 8

import thermostat.DayType;
import thermostat.Period;

import java.util.*;

public class ProgrammedSettings
{
    private Map<Period, Map<DayType, Integer>> settings =
            new HashMap<Period, Map<DayType, Integer>>();

    // Property
    public ProgrammedSettings ()
    {
        for (Period p : Period.values())
        {
            HashMap<DayType, Integer> m = new HashMap<DayType, Integer>();
            settings.put (p, m);
            for (DayType d : DayType.values())
            {
                m.put (d, 65); // Default value, 18.33C
            }
        }
    }
    public void setSetting (Period period, DayType day, int temp)
    {
        settings.get (period).put (day, temp);
    }
    public int getSetting (Period period, DayType day)
    {
        return settings.get (period).get (day);
    }
    public String toString()
    {
        return settings.toString();
    }
} // End ProgrammedSettings class
