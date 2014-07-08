package edu.pdx.cs410J.tfetters;

import edu.pdx.cs410J.AbstractAirline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

    private static boolean DEBUG = false;

    /**
     * The main methods receives the @param args from the command line
     * Upon receiving the README option the program will print the
     * class info to the standard output. When the proper amount of
     * arguments have been given in the proper format and the
     * print options is give. the class will create a new airline
     * and flight then print the flight information out to the user.
     *
     * When arguments don't match the proper format then error
     * messages will be given to the standard output.
     *
     * USAGE: [-print|-README] name flightNumber src departTime dest arriveTime
     *
     * name = The name of the airline
     * flightNumber = The flight number
     * src = Three-letter code of departure airport
     * departTime = Departure date and time (24-hour time)
     * dest = Three-letter code of arrival airport
     * arriveTime = Arrival date and time (24-hour time)
     * options are (options may appear in any order):
     * -print = Prints a description of the new flight
     * -README = Prints a README for this project and exits
     * Date and time should be in the format: mm/dd/yyyy hh:mm
     *
     */

    public static void main(String[] args) {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        int flightNum = 0;
        int optionCnt = 0;
        boolean toPrint  = false;
        boolean toReadMe  = false;



        for (String arg : args) {
            if(DEBUG)System.out.println(arg);
            if (arg.startsWith("-")) {
                if (arg.equals("-README")){

                    toReadMe = true;
                    optionCnt += 1;
                }
                else if (arg.equals("-print")) {
                    toPrint = true;
                    optionCnt += 1;
                }
                else {
                    System.err.println("Invalid Option Provided!");
                    System.exit(1);
                }

            }
        }

        if (toReadMe) {
            readME();
            System.exit(0);
        }

        if (DEBUG) System.out.println(args.length);

        if (args.length < 9) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }
        else if (args.length > 9) {
            System.err.println("Too many command line arguments");
            System.exit(1);
        }

        String name = args[0+optionCnt];

        try {
            flightNum = Integer.parseInt(args[1+optionCnt]);
        }
        catch (NumberFormatException e) {
            System.err.println("Flight number must be an integer");
            System.exit(1);
        }

        String src = args[2+optionCnt];
        String departTime = args[3+optionCnt] + " " + args[4+optionCnt];
        String dest = args[5+optionCnt];
        String arriveTime = args[6+optionCnt] + " " + args[7+optionCnt];

        if (!checkName(name)) {
            System.err.println("Flight name must be a string");
            System.exit(1);
        }

        switch (checkAirport(src)){
            case 1:
                System.err.println("Airport Source must be 3 characters");
                System.exit(1);
                break;
            case 2:
                System.err.println("Airport Source must only contain letters");
                System.exit(1);
                break;
            default:
                break;
        }

        switch (checkAirport(dest)){
            case 1:
                System.err.println("Airport Source must be 3 characters");
                System.exit(1);
                break;
            case 2:
                System.err.println("Airport Source must only contain letters");
                System.exit(1);
                break;
            default:
                break;
        }

        if (!checkDate(departTime)) {
            System.err.println("Departure Date not in proper format(mm/dd/yyyy hh:mm)");
            System.exit(1);
        }

        if (!checkDate(arriveTime)) {
            System.err.println("Arrival Date not in proper format(mm/dd/yyyy hh:mm)");
            System.exit(1);
        }


        Flight flight = new Flight (flightNum, src, departTime, dest, arriveTime);
        Airline airline = new Airline(name);
        airline.addFlight(flight);

        if (toPrint) {
            System.out.println(flight.toString());
        }



        System.exit(0);
    }

    /**
     * readME prints the class info to the standard output.
     */
    private static void readME() {
        String README = "* The main methods receives the args from the command line \n" +
            "* Upon receiving the README option the program will print the \n" +
            "* class info to the standard output. When the proper amount of\n" +
            "* arguments have been given in the proper format and the\n" +
            "* print options is give. the class will create a new airline\n" +
            "* and flight then print the flight information out to the user. \n" +
            "*\n" +
            "* When arguments don't match the proper format then error\n" +
            "* messages will be given to the standard output.\n" +
            "*\n" +
            "* USAGE: [-print|-README] name flightNumber src departTime dest arriveTime\n" +
            "*\n" +
            "* name = The name of the airline\n" +
            "* flightNumber = The flight number\n" +
            "* src = Three-letter code of departure airport\n" +
            "* departTime = Departure date and time (24-hour time)\n" +
            "* dest = Three-letter code of arrival airport\n" +
            "* arriveTime = Arrival date and time (24-hour time)\n" +
            "* options are (options may appear in any order):\n" +
            "* -print = Prints a description of the new flight\n" +
            "* -README = Prints a README for this project and exits\n" +
            "* Date and time should be in the format: mm/dd/yyyy hh:mm ";
        System.out.println(README);
        System.exit(0);
    }

    /**
     * The checkDate function takes a @param date and returns
     * @return boolean based on whether the date/time format matches
     * the required format.
     */

    public static boolean checkDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy H:mm");
        dateFormat.setLenient(false);
        try {
            if(DEBUG) System.out.println(date);

            if(!date.matches("^(1[0-2]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])/[0-9]{4} ([01]?[0-9]|2[0-3]):[0-5][0-9]$")){
                return false;
            }
            Date date1 = dateFormat.parse(date);

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    /**
     * the checkAirport function takes a string
     * @param airport and ensures that the parameter has the proper
     * format of 3 digits.
     * @return int based on check, if length not correct return 1
     * if not digits return 2, else return 0 for correct format
     */
    private static int checkAirport(String airport) {
        if (airport.length() != 3) {
            return 1;
        }
        if (!airport.matches("[a-zA-Z]+")) {
            return 2;
        }
        return 0;
    }

    /**
     * checkName takes a the @param name and verifies the
     * given name is a string
     * @return boolean based on test.
     */
    private static boolean checkName(String name) {
        return String.class == name.getClass();
    }

}