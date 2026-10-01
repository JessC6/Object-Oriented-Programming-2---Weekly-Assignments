package com.nhlstenden.thermostatresort.department;

import com.nhlstenden.thermostatresort.ThermostatResortSystem;
import com.nhlstenden.thermostatresort.checkin.CheckIn;

import java.time.LocalDate;

public class Restaurant implements DepartmentObserver
{
    private ThermostatResortSystem thermostatResortSystem;
    private int expectedDinnerGuests;

    public Restaurant(ThermostatResortSystem thermostatResortSystem)
    {
        this.setThermostatResortSystem(thermostatResortSystem);
        this.setExpectedDinnerGuests(0);
    }

    public ThermostatResortSystem getThermostatResortSystem()
    {
        return this.thermostatResortSystem;
    }

    private void setThermostatResortSystem(ThermostatResortSystem thermostatResortSystem)
    {
        if (thermostatResortSystem == null)
        {
            throw new IllegalArgumentException("ThermostatResortSystem cannot be null.");
        }

        this.thermostatResortSystem = thermostatResortSystem;
    }

    public int getExpectedDinnerGuests()
    {
        return this.expectedDinnerGuests;
    }

    private void setExpectedDinnerGuests(int expectedDinnerGuests)
    {
        if (expectedDinnerGuests < 0)
        {
            throw new IllegalArgumentException("ExpectedDinnerGuests cannot be negative.");
        }

        this.expectedDinnerGuests = expectedDinnerGuests;
    }

    private int calculateExpectedDinnerGuests()
    {
        int totalAccompanyingGuestForTheNight = 0;
        int totalMainGuests = 0;

        LocalDate today = LocalDate.now();

        for (CheckIn existingCheckIn : this.getThermostatResortSystem().getCheckIns())
        {
            if (existingCheckIn.getCorrespondingBooking().getIsDinnerIncluded() && existingCheckIn.getCheckInTime().toLocalDate().isEqual(today))
            {
                totalMainGuests++;

                for (Integer amount : existingCheckIn.getCorrespondingBooking().getAccompanyingGuests().values())
                {
                    totalAccompanyingGuestForTheNight += amount;
                }
            }
        }

        return totalMainGuests + totalAccompanyingGuestForTheNight;
    }

    @Override
    public void update(CheckIn checkIn)
    {
        if (checkIn == null)
        {
            throw new IllegalArgumentException("Check in cannot be null.");
        }

        this.setExpectedDinnerGuests(this.calculateExpectedDinnerGuests());
    }
}