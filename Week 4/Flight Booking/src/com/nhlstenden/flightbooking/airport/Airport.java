package com.nhlstenden.flightbooking.airport;

public class Airport
{
    private AirportCode code;

    public Airport(AirportCode code)
    {
        this.setCode(code);
    }

    public AirportCode getCode()
    {
        return this.code;
    }

    public void setCode(AirportCode code)
    {
        if (code == null)
        {
            throw new IllegalArgumentException("Code cannot be null.");
        }

        this.code = code;
    }
}