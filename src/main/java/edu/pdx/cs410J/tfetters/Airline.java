package edu.pdx.cs410J.tfetters;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by tylerfetters on 6/28/14.
 * class creates an airline object either with the
 * default constructor or the secondary constructors.
 * each airline has a name and a list of flights
 *
 * Additionally the class provides methods to get
 * access to both fields and a method to add a flight
 * to the airline collection.
 *
 */
public class Airline extends AbstractAirline {
    private String name;
    private Collection<AbstractFlight> flights;

    /**
     * Default constructor to initialize empty list and
     * set name to null
     */
    public Airline() {
        this.name = null;
        this.flights = new ArrayList<AbstractFlight>();
    }

    /**
     * secondary constructor taking the
     * @param name as an argument. Initialize name to
     * given parameter and initializes empty list.
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<AbstractFlight>();
    }
    /**
     * secondary constructor taking the
     * @param name and @param flights as arguments.
     * Initialize name to given parameter and initializes
     * list to given arguments
     */
    public Airline(String name, Collection<AbstractFlight> flights) {
        this.name = name;
        this.flights = flights;
    }

    /**
     * getName function returns the name of the airline
     * @return String
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * addFlight takes the @param flight to be added to the airline
     * and adds the given parameter to the flight list.
     *
     */
    @Override
    public void addFlight(AbstractFlight flight) {
        this.flights.add(flight);
    }

    /**
     * getFlights method returns the list of flights
     * @return list of Flights
     */
    @Override
    public Collection getFlights() {
        return this.flights;
    }
}
