package com.nhlstenden.thermostatresort.treatment;

public abstract class Treatment
{
    private int durationInMinutes;

    public Treatment(int durationInMinutes)
    {
        this.setDurationInMinutes(durationInMinutes);
    }

    public int getDurationInMinutes()
    {
        return this.durationInMinutes;
    }

    private void setDurationInMinutes(int durationInMinutes)
    {
        if (durationInMinutes <= 0)
        {
            throw new IllegalArgumentException("DurationInMinutes cannot be inferior or equal to 0.");
        }

        this.durationInMinutes = durationInMinutes;
    }

    public abstract int getTotalTreatmentPriceInEuroCents();
}