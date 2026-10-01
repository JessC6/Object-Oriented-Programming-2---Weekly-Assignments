package com.nhlstenden.thermostatresort.treatment;

public class ExtensiveTreatment extends Treatment
{
    private static final int PRICE_IN_EURO_CENTS_PER_MINUTE = 350;
    private static final int PRICE_IN_EURO_CENTS_HEATING_STONES = 500;
    private static final int PRICE_IN_EURO_CENTS_BREAK = 100;

    private boolean isBreakIncluded;
    private boolean areHotStonesRequired;

    public ExtensiveTreatment(int durationInMinutes, boolean isBreakIncluded, boolean areHotStonesRequired)
    {
        super(durationInMinutes);
        this.isBreakIncluded = isBreakIncluded;
        this.areHotStonesRequired = areHotStonesRequired;
    }

    public boolean getIsBreakIncluded()
    {
        return this.isBreakIncluded;
    }

    public boolean getAreHotStonesRequired()
    {
        return this.areHotStonesRequired;
    }

    @Override
    public int getTotalTreatmentPriceInEuroCents()
    {
        int total = this.getDurationInMinutes() * PRICE_IN_EURO_CENTS_PER_MINUTE;

        if (this.getIsBreakIncluded())
        {
            total += PRICE_IN_EURO_CENTS_BREAK;
        }

        if (this.getAreHotStonesRequired())
        {
            total += PRICE_IN_EURO_CENTS_HEATING_STONES;
        }

        return total;
    }
}