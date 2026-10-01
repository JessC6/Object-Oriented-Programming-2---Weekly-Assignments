package com.nhlstenden.thermostatresort.booking;

import com.nhlstenden.thermostatresort.guest.Guest;
import com.nhlstenden.thermostatresort.treatment.Treatment;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Booking
{
    private static final int DINNER_PRICE_PER_PERSON_IN_EURO_CENTS = 1595;
    private static final int DINNER_DISCOUNT_PERCENTAGE = 2;

    private Treatment treatment;
    private Guest guest;
    private Map<AgeCategory, Integer> accompanyingGuests;
    private boolean isDinnerIncluded;
    private LocalDate date;

    public Booking(Treatment treatment, Guest guest, boolean isDinnerIncluded, LocalDate date)
    {
        this.setTreatment(treatment);
        this.setGuest(guest);
        this.setAccompanyingGuests(new HashMap<>());
        this.isDinnerIncluded = isDinnerIncluded;
        this.setDate(date);
    }

    public Treatment getTreatment()
    {
        return this.treatment;
    }

    private void setTreatment(Treatment treatment)
    {
        if (treatment == null)
        {
            throw new IllegalArgumentException("Treatment cannot be null.");
        }

        this.treatment = treatment;
    }

    public Guest getGuest()
    {
        return this.guest;
    }

    private void setGuest(Guest guest)
    {
        if (guest == null)
        {
            throw new IllegalArgumentException("Guest cannot be null.");
        }

        this.guest = guest;
    }

    public Map<AgeCategory, Integer> getAccompanyingGuests()
    {
        return this.accompanyingGuests;
    }

    private void setAccompanyingGuests(Map<AgeCategory, Integer> accompanyingGuests)
    {
        if (accompanyingGuests == null)
        {
            throw new IllegalArgumentException("AccompanyingGuests cannot be null.");
        }

        this.accompanyingGuests = accompanyingGuests;
    }

    public void addAccompanyingGuest(AgeCategory ageCategory)
    {
        if (ageCategory == null)
        {
            throw new IllegalArgumentException("AgeCategory cannot be null.");
        }

        this.getAccompanyingGuests().put(ageCategory, this.getAccompanyingGuests().getOrDefault(ageCategory, 0) + 1);
    }

    public int getAmountOfAccompanyingGuestsFromAgeCategory(AgeCategory ageCategory)
    {
        if (ageCategory == null)
        {
            throw new IllegalArgumentException("AgeCategory cannot be null.");
        }

        return this.getAccompanyingGuests().getOrDefault(ageCategory, 0);
    }

    public boolean getIsDinnerIncluded()
    {
        return this.isDinnerIncluded;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    private void setDate(LocalDate date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        this.date = date;
    }

    public int getTotalDinnerPriceInEuroCents()
    {
        if (!this.getIsDinnerIncluded())
        {
            return 0;
        }

        int total = 0;

        // Main guest
        if (this.getGuest().getAge() >= 16 && this.getGuest().getAge() < 18)
        {
            total += DINNER_PRICE_PER_PERSON_IN_EURO_CENTS - (DINNER_PRICE_PER_PERSON_IN_EURO_CENTS * DINNER_DISCOUNT_PERCENTAGE / 100);
        }
        else
        {
            total += DINNER_PRICE_PER_PERSON_IN_EURO_CENTS;
        }

        // Accompanying guests
        for (Map.Entry<AgeCategory, Integer> entry : this.getAccompanyingGuests().entrySet())
        {
            AgeCategory ageCategory = entry.getKey();
            int number = entry.getValue();

            switch (ageCategory)
            {
                case SIXTEEN_TO_EIGHTEEN:
                    total += (DINNER_PRICE_PER_PERSON_IN_EURO_CENTS - (DINNER_PRICE_PER_PERSON_IN_EURO_CENTS * DINNER_DISCOUNT_PERCENTAGE / 100)) * number;
                    break;
                case EIGHTEEN_TO_TWENTY_ONE:
                    total += DINNER_PRICE_PER_PERSON_IN_EURO_CENTS * number;
                    break;
                case TWENTY_ONE_AND_OVER:
                    total += DINNER_PRICE_PER_PERSON_IN_EURO_CENTS * number;
                    break;
            }
        }

        return total;
    }

    public int getTotalBookingPriceInEuroCents()
    {
        return this.getTreatment().getTotalTreatmentPriceInEuroCents() + this.getTotalDinnerPriceInEuroCents();
    }

    public void sendConfirmationEmail()
    {
        String confirmationMessage = "Booking confirmation for " + this.getGuest().getName() + " on " + this.getDate() + ". Total price: €" + String.format("%.2f", this.getTotalBookingPriceInEuroCents() / 100.0);

        System.out.println("Sending email to " + this.getGuest().getEmail() + ":");
        System.out.println(confirmationMessage);
    }
}