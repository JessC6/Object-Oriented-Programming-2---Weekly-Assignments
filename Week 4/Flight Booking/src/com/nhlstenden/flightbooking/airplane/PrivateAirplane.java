package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;

public class PrivateAirplane extends Airplane
{
    private static final double SEATS_AMOUNT_MULTIPLIER = 1.31;
    private static final double SEATS_TAKEN_AMOUNT_MULTIPLIER = 1.87;
    private static final double LUGGAGE_WEIGHT_MULTIPLIER = 0.4;

    private int numberOfSeats;
    private int seatsTaken;

    public PrivateAirplane(String code, double currentFuelLevel, int numberOfSeats, int seatsTaken)
    {
        super(code, currentFuelLevel);
        this.setNumberOfSeats(numberOfSeats);
        this.setSeatsTaken(seatsTaken);
    }

    public int getNumberOfSeats()
    {
        return this.numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)
    {
        if (numberOfSeats <= 0)
        {
            throw new IllegalArgumentException("The airplane must at least have 1 seat.");
        }

        this.numberOfSeats = numberOfSeats;
    }

    public int getSeatsTaken()
    {
        return this.seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken)
    {
        if (seatsTaken < 0)
        {
            throw new IllegalArgumentException("SeatsTaken cannot be negative.");
        }

        if (seatsTaken > this.getNumberOfSeats())
        {
            throw new IllegalArgumentException("SeatsTaken cannot excide the total amount of seats in the airplane.");
        }

        this.seatsTaken = seatsTaken;
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

        return this.getNumberOfSeats() * SEATS_AMOUNT_MULTIPLIER *
                flight.getDistanceInKm() + (this.getSeatsTaken() * SEATS_TAKEN_AMOUNT_MULTIPLIER) +
                (getWeightOfTotalLuggageInKg(flight) * LUGGAGE_WEIGHT_MULTIPLIER);
    }

    @Override
    public int getTotalNumberOfEmptySeats()
    {
        return this.getNumberOfSeats() - this.getSeatsTaken();
    }

    @Override
    public void reserveSeat()
    {
        if (this.getTotalNumberOfEmptySeats() <= 0)
        {
            throw new IllegalArgumentException("A seat cannot be reserved in this airplane because all seats are taken.");
        }

        this.setSeatsTaken(this.getSeatsTaken() + 1);
    }
}