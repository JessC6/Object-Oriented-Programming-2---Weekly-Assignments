package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;

public class CommercialAirplane extends Airplane
{
    private static final double ECO_SEATS_MULTIPLIER = 1.75;
    private static final double BUS_SEATS_MULTIPLIER = 1.98;
    private static final double ECO_SEATS_TAKEN_MULTIPLIER = 2.02;
    private static final double BUS_SEATS_TAKEN_MULTIPLIER = 2.87;
    private static final double LUGGAGE_WEIGHT_MULTIPLIER = 0.3;

    private int economySeats;
    private int businessSeats;
    private int economySeatsTaken;
    private int businessSeatsTaken;

    public CommercialAirplane(String code, double currentFuelLevel, int economySeats, int businessSeats, int economySeatsTaken, int businessSeatsTaken)
    {
        super(code, currentFuelLevel);
        this.setEconomySeats(economySeats);
        this.setBusinessSeats(businessSeats);
        this.setEconomySeatsTaken(economySeatsTaken);
        this.setBusinessSeatsTaken(businessSeatsTaken);
    }

    public int getEconomySeats()
    {
        return this.economySeats;
    }

    public void setEconomySeats(int economySeats)
    {
        if (economySeats <= 0)
        {
            throw new IllegalArgumentException("This airplane must have at least 1 economy seat.");
        }

        this.economySeats = economySeats;
    }

    public int getBusinessSeats()
    {
        return this.businessSeats;
    }

    public void setBusinessSeats(int businessSeats)
    {
        if (businessSeats < 0)
        {
            throw new IllegalArgumentException("BusinessSeats cannot be negative.");
        }

        this.businessSeats = businessSeats;
    }

    public int getEconomySeatsTaken()
    {
        return this.economySeatsTaken;
    }

    public void setEconomySeatsTaken(int economySeatsTaken)
    {
        if (economySeatsTaken < 0)
        {
            throw new IllegalArgumentException("EconomySeatsTaken cannot be negative.");
        }

        if (economySeatsTaken > this.getEconomySeats())
        {
            throw new IllegalArgumentException("EconomySeatsTaken cannot excide the total amount of economy seats in the airplane.");
        }

        this.economySeatsTaken = economySeatsTaken;
    }

    public int getBusinessSeatsTaken()
    {
        return this.businessSeatsTaken;
    }

    public void setBusinessSeatsTaken(int businessSeatsTaken)
    {
        if (businessSeatsTaken < 0)
        {
            throw new IllegalArgumentException("BusinessSeatsTaken cannot be negative.");
        }

        if (businessSeatsTaken > this.getBusinessSeats())
        {
            throw new IllegalArgumentException("BusinessSeatsTaken cannot excide the total amount of business seats in the airplane.");
        }

        this.businessSeatsTaken = businessSeatsTaken;
    }

    @Override
    public double getFuelConsumption(Flight flight)
    {
        if (flight == null)
        {
            throw new IllegalArgumentException("Flight cannot be null.");
        }

        if (flight.getDistanceInKm() == 0)
        {
            throw new IllegalArgumentException("Something went wrong with the assignments of Airports for departure and/or arrival.");
        }

        return ((this.getEconomySeats() * ECO_SEATS_MULTIPLIER) + (this.getBusinessSeats() * BUS_SEATS_MULTIPLIER)) *
                flight.getDistanceInKm() + (this.getEconomySeatsTaken() * ECO_SEATS_TAKEN_MULTIPLIER) +
                (this.getBusinessSeatsTaken() * BUS_SEATS_TAKEN_MULTIPLIER) + (getWeightOfTotalLuggageInKg(flight) * LUGGAGE_WEIGHT_MULTIPLIER);
    }

    @Override
    public int getTotalNumberOfEmptySeats()
    {
        return this.getEconomySeats() + this.getBusinessSeats() - this.getEconomySeatsTaken() - this.getBusinessSeatsTaken();
    }

    @Override
    public void reserveSeat()
    {
        if (this.getTotalNumberOfEmptySeats() <= 0)
        {
            throw new IllegalArgumentException("A seat cannot be reserved in this airplane because all seats are taken.");
        }

        if (this.getEconomySeats() > this.getEconomySeatsTaken())
        {
            this.setEconomySeatsTaken(this.getEconomySeatsTaken() + 1);
        }
        else
        {
            this.setBusinessSeatsTaken(this.getBusinessSeatsTaken()+ 1);
        }
    }
}