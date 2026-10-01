package com.nhlstenden.thermostatresort.checkin;

import com.nhlstenden.thermostatresort.booking.Booking;

import java.time.LocalDateTime;

public class CheckIn
{
    private Booking correspondingBooking;
    private boolean isOutsidePoolIncluded;
    private boolean isMusicAllowedDuringTreatment;
    private LocalDateTime checkInTime;

    public CheckIn(Booking correspondingBooking, boolean isOutsidePoolIncluded, boolean isMusicAllowedDuringTreatment)
    {
        this.setCorrespondingBooking(correspondingBooking);
        this.isOutsidePoolIncluded = isOutsidePoolIncluded;
        this.isMusicAllowedDuringTreatment = isMusicAllowedDuringTreatment;
        this.setCheckInTime(LocalDateTime.now());
    }

    public Booking getCorrespondingBooking()
    {
        return this.correspondingBooking;
    }

    private void setCorrespondingBooking(Booking correspondingBooking)
    {
        if (correspondingBooking == null)
        {
            throw new IllegalArgumentException("CorrespondingBooking cannot be null.");
        }

        this.correspondingBooking = correspondingBooking;
    }

    public boolean getIsOutsidePoolIncluded()
    {
        return this.isOutsidePoolIncluded;
    }

    public boolean getIsMusicAllowedDuringTreatment()
    {
        return this.isMusicAllowedDuringTreatment;
    }

    public LocalDateTime getCheckInTime()
    {
        return this.checkInTime;
    }

    private void setCheckInTime(LocalDateTime checkInTime)
    {
        if (checkInTime == null)
        {
            throw new IllegalArgumentException("CheckInTime cannot be null.");
        }

        this.checkInTime = checkInTime;
    }
}