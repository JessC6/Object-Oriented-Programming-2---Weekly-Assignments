package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.luggage.Luggage;

public abstract class Airplane
{
    private String code;
    private double currentFuelLevel;

    public Airplane(String code, double currentFuelLevel)
    {
        this.setCode(code);
        this.setCurrentFuelLevel(currentFuelLevel);
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        if (code == null || code.isEmpty())
        {
            throw new IllegalArgumentException("Code cannot be null or empty.");
        }

        this.code = code;
    }

    public double getCurrentFuelLevel()
    {
        return this.currentFuelLevel;
    }

    public void setCurrentFuelLevel(double currentFuelLevel)
    {
        if (currentFuelLevel < 0)
        {
            throw new IllegalArgumentException("CurrentFuelLevel cannot be negative.");
        }

        this.currentFuelLevel = currentFuelLevel;
    }

    // I wanted to make this method private but since such cannot be reached by subclasses I made it protected instead
    protected double getWeightOfTotalLuggageInKg(Flight flight)
    {
        if (flight == null)
        {
            throw new IllegalArgumentException("Flight cannot be null");
        }

        if (flight.getLuggage().isEmpty())
        {
            return 0;
        }

        double totalLuggageWeight = 0;

        for (Luggage luggage : flight.getLuggage())
        {
            totalLuggageWeight += luggage.getWeightInKg();
        }

        return totalLuggageWeight;
    }

    public abstract double getFuelConsumption(Flight flight);

    public abstract int getTotalNumberOfEmptySeats();

    public abstract void reserveSeat();
}