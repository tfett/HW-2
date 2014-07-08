package edu.pdx.cs410J.tfetters;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import edu.pdx.cs410J.InvokeMainTestCase;
import static junit.framework.Assert.assertEquals;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project1.class, args );
    }


    /**
    * Tests that invoking the main method with no arguments issues an error
    */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertEquals(new Integer(1), result.getExitCode());
        assertTrue(result.getErr().contains( "Missing command line arguments" ));
    }

    @Test
    public void testTooFewCommandArguments() {
        MainMethodResult result = invokeMain("A Single Argument");
        assertTrue(result.getErr().contains("Missing command line arguments"));
    }

    @Test
    public void testTooManyCommandArguments () {
       MainMethodResult result = invokeMain("A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A");
       assertTrue(result.getErr().contains("Too many command line arguments"));
    }

    @Test
    public void flightNumberNotDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "ABCD", "PDX",
                        "6/28/2014", "6:05", "SJC", "6/28/2014", "7:15");
        assertTrue(result.getErr().contains("Flight number must be an integer"));
    }

    @Test
    public void flightNumberDigitsAndNonDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "123A", "PDX",
                        "6/28/2014", "6:05", "SJC", "6/28/2014", "7:15");
        assertTrue(result.getErr().contains("Flight number must be an integer"));
    }

    @Test
    public void AirportContainsDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "123", "123",
                        "6/28/2014", "6:05", "SJC", "6/28/2014", "7:15");
        assertTrue(result.getErr().contains("Airport Source must only contain letters"));
    }

    @Test
    public void SrcFlightIsMoreThan3Digits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDXA",
                        "6/28/2014", "6:05", "SJC", "6/28/2014", "7:15");
        assertTrue(result.getErr().contains("Airport Source must be 3 characters"));
    }

    @Test
    public void dayInDateisTwoDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                        "12/18/2014", "6:05", "SJC", "6/28/2014", "7:15");
        assertEquals(new Integer(0), result.getExitCode());
    }

    @Test
    public void dayInDateIsOneDigit() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                "6/8/2014", "6:05", "SJC", "6/2/2014", "7:15");
        assertEquals(new Integer(0), result.getExitCode());
    }

    @Test
    public void monthInDateisTwoDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                "06/08/2014", "6:05", "SJC", "11/28/2014", "7:15");
        assertEquals(new Integer(0), result.getExitCode());

    }

    @Test
    public void monthInDateIsOneDigit() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                        "6/08/2014", "6:05", "SJC", "1/28/2014", "7:15");
        assertEquals(new Integer(0), result.getExitCode());
    }

    @Test
    public void yearInDateIsTwoDigits() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                                "6/08/20", "6:05", "SJC", "1/28/20", "7:15");
        assertTrue(result.getErr().contains("Departure Date not in proper format(mm/dd/yyyy hh:mm)"));
    }
    @Test
    public void timeIsInvalid() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                                "6/08/20", "26:05", "SJC", "1/28/20", "7:15");
        assertTrue(result.getErr().contains("Departure Date not in proper format(mm/dd/yyyy hh:mm)"));
    }

    @Test
    public void timeIsInvalid2() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                                                "6/08/20", "22:05", "SJC", "1/28/20", "7:75");
        assertTrue(result.getErr().contains("Departure Date not in proper format(mm/dd/yyyy hh:mm)"));
    }

    @Test
    public void testCleanPass() {
        MainMethodResult result = invokeMain("-print","United Airlines", "1234", "PDX",
                "6/28/2014", "6:05", "SJC", "6/28/2014", "00:15");
        assertEquals(new Integer(0), result.getExitCode());
    }




}