package com.nhlstenden.thermostatresort.treatment;

public class SimpleTreatment extends Treatment
{
    private final static int PRICE_IN_EURO_CENTS_PER_MINUTE = 200;

    public SimpleTreatment(int durationInMinutes)
    {
        super(durationInMinutes);
    }

    @Override
    public int getTotalTreatmentPriceInEuroCents()
    {
        return this.getDurationInMinutes() * PRICE_IN_EURO_CENTS_PER_MINUTE;
    }
}