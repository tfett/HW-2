package edu.pdx.cs410J.tfetters;

import edu.pdx.cs410J.AbstractFlight;

/**
 * Created by tylerfetters on 6/28/14.
 * class flight creates a flight object given the correct
 * parameters for the constructor. Additionally provides
 * getter methods to give access to the flight information.
 *
 */
public class Flight extends AbstractFlight {
    private int number;
    private String source;
    private String departure;
    private String destination;
    private String arrival;

    /**
     * Constructor takes the following parameters
     *
     * @param number
     * @param source
     * @param departure
     * @param destination
     * @param arrival
     *
     * And sets it's respective fields to the given parameters
     *
     */
    public Flight(int number, String source, String departure, String destination, String arrival) {
        this.number = number;
        this.source = source;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
    }

    /**
     * get Number returns the flight number
     * @return int
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * getSource returns the source airport
     * @return String
     */
    @Override
    public String getSource() {
        return this.source;
    }

    /**
     * getDepartureString returns the departure time
     * @return String
     */
    @Override
    public String getDepartureString() {
        return this.departure;
    }

    /**
     * getDestination returns the destination airport
     * @return String
     */
    @Override
    public String getDestination() {
        return this.destination;
    }


    /**
     * getArrivalString returns the arrival time
     * @return String
     */
    @Override
    public String getArrivalString() {
        return this.arrival;
    }
}
