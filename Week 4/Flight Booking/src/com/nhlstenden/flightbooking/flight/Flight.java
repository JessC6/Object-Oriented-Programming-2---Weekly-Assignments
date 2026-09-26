package com.nhlstenden.flightbooking.flight;

import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.luggage.Luggage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight
{
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDateTime;
    private FlightStatus status;
    private Airplane airplane;
    private List<Luggage> luggage;

    public Flight(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, FlightStatus status, Airplane airplane)
    {
        this.setDepartureAirport(departureAirport);
        this.setArrivalAirport(arrivalAirport);
        this.setDepartureDateTime(departureDateTime);
        this.setStatus(status);
        this.setAirplane(airplane);
        this.setLuggage(new ArrayList<>());
    }

    public Airport getDepartureAirport()
    {
        return this.departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport)
    {
        if (departureAirport == null)
        {
            throw new IllegalArgumentException("DepartureAirport cannot be null.");
        }

        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport()
    {
        return this.arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport)
    {
        if (arrivalAirport == null)
        {
            throw new IllegalArgumentException("ArrivalAirport cannot be null.");
        }

        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime()
    {
        return this.departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime)
    {
        if (departureDateTime == null)
        {
            throw new IllegalArgumentException("DepartureDateTime cannot be null.");
        }

        this.departureDateTime = departureDateTime;
    }

    public FlightStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(FlightStatus status)
    {
        if (status == null)
        {
            throw new IllegalArgumentException("Status cannot be null.");
        }

        this.status = status;
    }

    public Airplane getAirplane()
    {
        return this.airplane;
    }

    public void setAirplane(Airplane airplane)
    {
        if (airplane == null)
        {
            throw new IllegalArgumentException("Airplane cannot be null.");
        }

        this.airplane = airplane;
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

        this.getLuggage().add(luggage);
    }

    public int getDistanceInKm()
    {
        switch (this.getDepartureAirport().getCode())
        {
            case JFK:
                if (this.getArrivalAirport().getCode().equals(AirportCode.AMS))
                {
                    return 5848;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.MEX))
                {
                    return 3366;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.LAX))
                {
                    return 3975;
                }
                break;
            case AMS:
                if (this.getArrivalAirport().getCode().equals(AirportCode.MEX))
                {
                    return 9206;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.LAX))
                {
                    return 8956;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.JFK))
                {
                    return 5848;
                }
                break;
            case MEX:
                if (this.getArrivalAirport().getCode().equals(AirportCode.LAX))
                {
                    return 2500;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.JFK))
                {
                    return 3366;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.AMS))
                {
                    return 9206;
                }
                break;
            case LAX:
                if (this.getArrivalAirport().getCode().equals(AirportCode.JFK))
                {
                    return 3975;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.MEX))
                {
                    return 2500;
                }
                else if (this.getArrivalAirport().getCode().equals(AirportCode.AMS))
                {
                    return 8956;
                }
                break;
        }

        return 0;
    }

    public String getFlightInformation()
    {
        return String.format("F: %S -> %S. Departure %3$td-%3$tm-%3$tY %3$tH:%3$tM.", this.getDepartureAirport().getCode(), this.getArrivalAirport().getCode(), this.getDepartureDateTime());
    }

    public String getAirplaneInformation()
    {
        return String.format("P: %S. %.0f liter fuel. %d empty seats.", this.getAirplane().getCode(), this.getAirplane().getCurrentFuelLevel(), this.getAirplane().getTotalNumberOfEmptySeats());
    }

    public String getOverallInformation()
    {
        return this.getFlightInformation() + "\n" + this.getAirplaneInformation();
    }

    public void depart()
    {
        if (this.getAirplane().getFuelConsumption(this) > this.getAirplane().getCurrentFuelLevel())
        {
            throw new IllegalArgumentException("This flight cannot depart because it doesn't have enough fuel.");
        }

        this.setStatus(FlightStatus.DEPARTED);
    }

    public boolean isAvailable()
    {
        // Enums should generally be compared using == (note to self because I was using .equal before)
        return (this.getStatus() == FlightStatus.AWAITING_DEPARTURE || this.getStatus() == FlightStatus.BOARDING) &&
                this.getAirplane().getTotalNumberOfEmptySeats() > 0;
    }

    public void reserveSeat()
    {
        if (!this.isAvailable())
        {
            throw new IllegalArgumentException("Is no longer possible to reserve a seat in this airplane.");
        }

        this.getAirplane().reserveSeat();
    }
}