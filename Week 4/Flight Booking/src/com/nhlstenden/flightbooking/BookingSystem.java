package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.flight.Flight;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem
{
    private List<Flight> flights;

    public BookingSystem()
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

    public Flight getFirstAvailableFlight(Airport departureAirport, Airport arrivalAirport)
    {
        if (departureAirport == null || arrivalAirport == null)
        {
            throw new IllegalArgumentException("Departure and Arrival airports cannot be null.");
        }

        if (this.getFlights().isEmpty())
        {
            throw new IllegalArgumentException("There are no flights in this system.");
        }

        for (Flight existingFlight : this.getFlights())
        {
            if (existingFlight.isAvailable()
                    && existingFlight.getDepartureAirport().getCode() == departureAirport.getCode()
                    && existingFlight.getArrivalAirport().getCode() == arrivalAirport.getCode())
            {
                return existingFlight;
            }
        }

        throw new IllegalArgumentException("There are no available flights for the requested trip.");
    }
}