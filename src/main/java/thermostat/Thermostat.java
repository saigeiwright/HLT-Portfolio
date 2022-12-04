package thermostat;

//Introduction to Software Testing
//Authors: Paul Ammann & Jeff Offutt
//Chapter 8, page ??
//See ThermostatTest.java for JUnit tests

//Programmable Thermostat
public class Thermostat{

    private static int curTemp;          // current temperature reading
    private static int thresholdDiff;    // temp difference until we turn heater on
    private static int timeSinceLastRun; // time since heater stopped
    private static int minLag;           // how long I need to wait
    private static boolean override;     // has user overridden the program
    private static int overTemp;         // overriding temperature
    private int runTime;          // output of turnHeaterOn - how long to run
    private boolean heaterOn;     // output of turnHeaterOn - whether to run
    private Period period;        // morning, day, evening, or night
    private DayType day;          // week day or weekend day

    // Decide whether to turn the heater on, and for how long.
    public boolean turnHeaterOn (ProgrammedSettings pSet)
    {
        int dTemp = pSet.getSetting (period, day);

        if (((curTemp < dTemp - thresholdDiff) ||
                (override && curTemp < overTemp - thresholdDiff)) &&
                (timeSinceLastRun > minLag))
        {  // Turn on the heater
            // How long? Assume 1 minute per degree (Fahrenheit)
            int timeNeeded = curTemp - dTemp;
            if (override)
                timeNeeded = curTemp - overTemp;
            setRunTime (timeNeeded);
            setHeaterOn (true);
            return (true);
        }
        else
        {
            setHeaterOn (false);
            return (false);
        }
    } // End turnHeaterOn

    public static void setCurrentTemp(int temperature)  { curTemp = temperature; }
    public static void setThresholdDiff(int delta)      { thresholdDiff = delta; }
    public static void setTimeSinceLastRun(int minutes) { timeSinceLastRun = minutes; }
    public static void setMinLag(int minutes)           { minLag = minutes; }
    public static void setOverride(boolean value)       { override = value; }
    public static void setOverTemp(int temperature)     { overTemp = temperature; }

    // for the ProgrammedSettings
    public void setDay (DayType curDay)           { day = curDay; }
    public void setPeriod (Period curPeriod)      { period = curPeriod; }

    // outputs from turnHeaterOn - need corresponding getters to activate heater
    void setRunTime  (int minutes)         { runTime = minutes; }
    void setHeaterOn (boolean value)       { heaterOn = value; }
} // End Thermostat class
