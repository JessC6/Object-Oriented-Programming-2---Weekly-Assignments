package com.nhlstenden.flightbooking.flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Flight24Uploader
{
    private List<Flight> flights;

    public Flight24Uploader()
    {
        this.setFlights(new ArrayList<>());
    }

    public List<Flight> getFlights()
    {
        return this.flights;
    }

    public void setFlights(List<Flight> flights)
    {
        if (flights == null)
        {
            throw new IllegalArgumentException("Flights cannot be null.");
        }

        this.flights = flights;
    }

    private void writeToFile(String content)
    {
        try
        {
            // Writes content to "output.txt", if it doesn't exist it creates the file inside the current folder, if it does it completely overrides it
            Files.writeString(Path.of("output.txt"), content);
            System.out.println("Successfully written to file!");
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            throw new IllegalArgumentException("An error occurred and the file could not be created/written.");
        }
    }

    public void uploadAllFlightsInformation()
    {
        if (this.getFlights().isEmpty())
        {
            throw new IllegalArgumentException("Flights is empty, therefore information regarding it cannot be extracted and/or uploaded.");
        }

        StringBuilder sbContent = new StringBuilder("");

        for (Flight flight : this.getFlights())
        {
            sbContent.append(flight.getFlightInformation());
            sbContent.append("\n");
        }

        String content = sbContent.toString();

        this.writeToFile(content);
    }

    public void uploadAllAirplanesInformation()
    {
        if (this.getFlights().isEmpty())
        {
            throw new IllegalArgumentException("Flights is empty, therefore information regarding it cannot be extracted and/or uploaded.");
        }

        StringBuilder sbContent = new StringBuilder("");

        for (Flight flight : this.getFlights())
        {
            sbContent.append(flight.getAirplaneInformation());
            sbContent.append("\n");
        }

        String content = sbContent.toString();

        this.writeToFile(content);
    }

    public void uploadOverallInformation()
    {
        if (this.getFlights().isEmpty())
        {
            throw new IllegalArgumentException("Flights is empty, therefore information regarding it cannot be extracted and/or uploaded.");
        }

        StringBuilder sbContent = new StringBuilder("");

        for (Flight flight : this.getFlights())
        {
            sbContent.append(flight.getOverallInformation());
            sbContent.append("\n");
            sbContent.append("\n");
        }

        String content = sbContent.toString();

        this.writeToFile(content);
    }
}