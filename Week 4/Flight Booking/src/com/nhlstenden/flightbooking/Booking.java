package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.CommercialAirplane;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;
import com.nhlstenden.flightbooking.person.Person;

import java.util.ArrayList;
import java.util.List;

public class Booking
{
    private Person person;
    private Flight flight;
    private List<Luggage> luggage;

    public Booking(Person person, Flight flight)
    {
        this.setPerson(person);
        this.setFlight(flight);
        this.setLuggage(new ArrayList<>());
    }

    public Person getPerson()
    {
        return this.person;
    }

    public void setPerson(Person person)
    {
        if (person == null)
        {
            throw new IllegalArgumentException("Person cannot be null.");
        }

        this.person = person;
    }

    public Flight getFlight()
    {
        return this.flight;
    }

    public void setFlight(Flight flight)
    {
        if (flight == null)
        {
            throw new IllegalArgumentException("Flight cannot be null.");
        }

        if (!flight.isAvailable())
        {
            throw new IllegalArgumentException("This flight is no longer available.");
        }

        this.flight = flight;
    }

    public List<Luggage> getLuggage()
    {
        return this.luggage;
    }

    public void setLuggage(List<Luggage> luggage)
    {
        if (luggage == null)
        {
            throw new IllegalArgumentException("Luggage cannot be null.");
        }

        this.luggage = luggage;
    }

    public void addLuggage(Luggage luggage)
    {
        if (luggage == null)
        {
            throw new IllegalArgumentException("Luggage cannot be null.");
        }

        if (this.getLuggage().contains(luggage))
        {
            throw new IllegalArgumentException("This luggage already exists in the system.");
        }

        // A passenger can have at most one carry-on luggage in commercial airplanes.
        if (this.getFlight().getAirplane() instanceof CommercialAirplane && luggage.getType() == LuggageType.CARRY_ON)
        {
            for (Luggage existingLuggage : this.getLuggage())
            {
                if (existingLuggage.getType() == LuggageType.CARRY_ON)
                {
                    throw new IllegalArgumentException("Each passenger can only have one carry-on luggage in commercial airplanes.");
                }
            }
        }

        // Private airplanes cannot have hold luggage.
        if (!(this.getFlight().getAirplane() instanceof CommercialAirplane) && luggage.getType() == LuggageType.HOLD)
        {
            throw new IllegalArgumentException("Private airplanes cannot have hold luggage.");
        }

        this.getLuggage().add(luggage);

        // Also add luggage to the flight so that the flight's
        // total luggage is included in fuel calculations.
        this.getFlight().addLuggage(luggage);
    }

    public void reserveSeat()
    {
        this.getFlight().reserveSeat();
    }
}