package thermostat;

import static org.junit.Assert.*;

public class ThermostatTest {

    public Thermostat preSet(boolean overRide, int thresholdDiff, int minLag, int overTemp)
    {
        Thermostat therm = new Thermostat();
        therm.setCurrentTemp(55);
        therm.setThresholdDiff(thresholdDiff);
        therm.setTimeSinceLastRun(30);
        therm.setMinLag(minLag);
        therm.setOverride(overRide);
        therm.setOverTemp(overTemp);
        return therm;
    }

    public void print(boolean pass, String coverage)
    {
        if(pass)
        {
            System.out.println(coverage + " test passed.");
        }
        else {
            System.out.println(coverage + " test failed.");
        }
    }

    @org.junit.Test
    void P2Test1PC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(true, heatOn);
        boolean expected = true;
        String name = "Predicate 2 PC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //predicate testing testing predicate 2 using row 1
    //b = false
    @org.junit.Test
    void P2Test2PC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 2 PC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //predicate coverage testing predicate 1 using row 16
    // a, b, c, and d all true
    @org.junit.Test
    void P1Test1PC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        int thresholdD = 5;
        int minLag = 15;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(true, heatOn);
        boolean expected = true;
        String name = "Predicate 1 PC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //predicate coverage testing predicate 1 using row 1
    //a, b, c, and d all false
    @org.junit.Test
    void P1Test2PC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        int thresholdD = 25;
        int minLag = 40;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 1 PC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }


    @org.junit.Test
    void P2Test1CC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(true, heatOn);
        boolean expected = true;
        String name = "Predicate 2 CC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //clause coverage testing testing predicate 2 using row 1
    @org.junit.Test
    void P2Test2CC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 2 CC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //clause coverage testing predicate 1 using row 4
    //clause a and b = false, clause c and d = true
    @org.junit.Test
    void P1Test1CC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        int thresholdD = 19;
        int minLag = 15;
        int overTemp = 75;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, overTemp);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = true;
        String name = "Predicate 1 CC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //clause coverage testing predicate 1 using row 13
    // clause c and d = false, clause a and b = true
    @org.junit.Test
    void P1Test2CC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        int thresholdD = 5;
        int minLag = 40;
        int overTemp = 10;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, overTemp);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 1 CC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //correlated active clause coverage testing predicate 2 using row 2
    @org.junit.Test
    void P2Test1CACC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(true, heatOn);
        boolean expected = true;
        String name = "Predicate 2 CACC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //correlated active clause coverage testing predicate 2 using row 1
    @org.junit.Test
    void P2Test2CACC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        Thermostat testThermostat = preSet(override, 5, 15, 75);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 2 CACC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //correlated active clause coverage testing predicate 1 using row 15
    //clause a, b , and c = true, d = false
    @org.junit.Test
    void P1Test1CACC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = true;
        int thresholdD = 5;
        int minLag = 40;
        int overTemp = 75;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, overTemp);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(false, heatOn);
        boolean expected = false;
        String name = "Predicate 1 CACC TEST 1: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }

    //correlated active clause coverage testing predicate 1 using row 10
    // clause c and b = false, clause a and d = true
    @org.junit.Test
    void P1Test2CACC() {
        ProgrammedSettings pSet = new ProgrammedSettings();
        boolean override = false;
        int thresholdD = 5;
        int minLag = 15;
        int overTemp = 10;

        Thermostat testThermostat = preSet(override, thresholdD, minLag, overTemp);

        boolean heatOn = testThermostat.turnHeaterOn(pSet);

        assertEquals(true, heatOn);
        boolean expected = true;
        String name = "Predicate 2 CACC TEST 2: ";
        if(expected == heatOn)
        {
            print(true, name);
        }
        else
        {
            print(false, name);
        }
    }
}