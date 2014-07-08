package edu.pdx.cs410J.tfetters;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by tylerfetters on 7/5/14.
 */
public class TextParser implements AirlineParser{


    private final Path fileName;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private boolean DEBUG = true;

    public TextParser(String fileName) {
        this.fileName = Paths.get(fileName);
    }

    @Override
    public AbstractAirline parse() throws ParserException {

        AbstractAirline airline = null;
        int lineNumber = 0;
        try (Scanner scanner =  new Scanner(fileName, ENCODING.name())){
            if (scanner.hasNextLine()) {
                //get Airline info
                airline = getAirlineInfo(scanner.nextLine());
                lineNumber += 1;

            }
            while (scanner.hasNextLine()){
                AbstractFlight flight = getFlightInfo(scanner.nextLine());
                airline.addFlight(flight);
                lineNumber += 1;
                System.out.println("Number of added flights: " + lineNumber);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new ParserException("The text ﬁle is malformatted");
        }

        return airline;
    }

    private AbstractAirline getAirlineInfo(String line) throws ParserException {
        return new Airline(line.trim());
    }

    private AbstractFlight getFlightInfo(String line) throws ParserException {
        Scanner scanner = new Scanner(line);
        StringBuilder sb = new StringBuilder();
        return new Flight(scanner.nextInt(), scanner.next(), scanner.next()+" "+scanner.next(), scanner.next(), scanner.next()+" "+scanner.next());
    }

}
