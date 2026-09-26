package com.nhlstenden.flightbooking.luggage;

public class Luggage
{
    private double weightInKg;
    private LuggageType type;

    public Luggage(double weightInKg, LuggageType type)
    {
        this.setWeightInKg(weightInKg);
        this.setType(type);
    }

    public double getWeightInKg()
    {
        return this.weightInKg;
    }

    public void setWeightInKg(double weightInKg)
    {
        if (weightInKg <= 0)
        {
            throw new IllegalArgumentException("Weight cannot equal or inferior to 0.");
        }

        this.weightInKg = weightInKg;
    }

    public LuggageType getType()
    {
        return this.type;
    }

    public void setType(LuggageType type)
    {
        if (type == null)
        {
            throw new IllegalArgumentException("Type cannot be null.");
        }

        this.type = type;
    }
}