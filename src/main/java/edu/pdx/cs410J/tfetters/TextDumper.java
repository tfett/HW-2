package edu.pdx.cs410J.tfetters;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * Created by tylerfetters on 7/5/14.
 */
public class TextDumper implements AirlineDumper {

    private String fileName;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private Writer fw;
    private boolean DEBUG = true;

    public TextDumper() {
        fileName = null;
        fw = null;
    }

    public TextDumper(String fileName) {
        this.fileName = fileName;

    }


    @Override
    public void dump(AbstractAirline airline) throws IOException {
        try {

            this.fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), ENCODING.name()));
            fw.write(airline.getName());
            fw.flush();
            Collection<AbstractFlight> flights = ((Airline) airline).getFlights();
            if (DEBUG) System.out.println("Number of flights before write: " + flights.size());
            int i = 0;
            for (AbstractFlight flight: flights) {
                i++;
                if (DEBUG) System.out.println("Writing Flight " + i);
                StringBuilder sb = new StringBuilder();
                sb.append(System.lineSeparator());
                sb.append(flight.getNumber());
                sb.append(" ");
                sb.append(flight.getSource());
                sb.append(" ");
                sb.append(flight.getDepartureString());
                sb.append(" ");
                sb.append(flight.getDestination());
                sb.append(" ");
                sb.append(flight.getArrivalString());
                if (DEBUG) System.out.println(sb.toString());
                fw.write(sb.toString());
                fw.flush();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            fw.close();
        }


    }

}
