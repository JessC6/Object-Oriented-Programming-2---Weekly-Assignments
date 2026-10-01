package com.nhlstenden.thermostatresort;

import com.nhlstenden.thermostatresort.booking.Booking;
import com.nhlstenden.thermostatresort.checkin.CheckIn;
import com.nhlstenden.thermostatresort.guest.Guest;
import com.nhlstenden.thermostatresort.notifier.DepartmentNotifier;
import com.nhlstenden.thermostatresort.treatment.Treatment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThermostatResortSystem
{
    private List<CheckIn> checkIns;
    private List<Booking> bookings;
    private DepartmentNotifier departmentNotifier;

    public ThermostatResortSystem(DepartmentNotifier departmentNotifier)
    {
        this.setCheckIns(new ArrayList<>());
        this.setBookings(new ArrayList<>());
        this.setDepartmentNotifier(departmentNotifier);
    }

    public List<CheckIn> getCheckIns()
    {
        return this.checkIns;
    }

    private void setCheckIns(List<CheckIn> checkIns)
    {
        if (checkIns == null)
        {
            throw new IllegalArgumentException("CheckIns cannot be null.");
        }

        this.checkIns = checkIns;
    }

    public void addCheckIn(CheckIn checkIn)
    {
        if (checkIn == null)
        {
            throw new IllegalArgumentException("Check in cannot be null.");
        }

        if (this.getCheckIns().contains(checkIn))
        {
            throw new IllegalArgumentException("This check in already exists in the system.");
        }

        this.getCheckIns().add(checkIn);
    }

    public List<Booking> getBookings()
    {
        return this.bookings;
    }

    private void setBookings(List<Booking> bookings)
    {
        if (bookings == null)
        {
            throw new IllegalArgumentException("Bookings cannot be null.");
        }

        this.bookings = bookings;
    }

    public void addBooking(Booking booking)
    {
        if (booking == null)
        {
            throw new IllegalArgumentException("Booking cannot be null.");
        }

        if (this.getBookings().contains(booking))
        {
            throw new IllegalArgumentException("This booking already exists in the system.");
        }

        this.getBookings().add(booking);
    }

    public DepartmentNotifier getDepartmentNotifier()
    {
        return this.departmentNotifier;
    }

    private void setDepartmentNotifier(DepartmentNotifier departmentNotifier)
    {
        if (departmentNotifier == null)
        {
            throw new IllegalArgumentException("DepartmentNotifier cannot be null.");
        }

        this.departmentNotifier = departmentNotifier;
    }

    public Guest registerGuest(String name, String address, String email, int phoneNumber, LocalDate dateOfBirth)
    {
        return new Guest(name, address, email, phoneNumber, dateOfBirth);
    }

    public Booking createBooking(Treatment treatment, Guest guest, boolean isDinnerIncluded, LocalDate date)
    {
        Booking newBooking = new Booking(treatment, guest, isDinnerIncluded, date);
        this.addBooking(newBooking);
        newBooking.sendConfirmationEmail();

        return newBooking;
    }

    public boolean validateGuestIdentification(String idName, Booking booking)
    {
        if (idName == null || idName.isEmpty() || booking == null)
        {
            throw new IllegalArgumentException("Neither id name or booking can be null, and the first one cannot be empty either.");
        }

        return booking.getGuest().getName().equalsIgnoreCase(idName);
    }

    private void notifyDepartments(CheckIn checkIn)
    {
        if (checkIn == null)
        {
            throw new IllegalArgumentException("Check in cannot be null.");
        }

        this.getDepartmentNotifier().notifyObservers(checkIn);
    }

    public CheckIn createCheckIn(String guestIdName, Booking correspondingBooking, boolean isOutsidePoolIncluded, boolean isMusicAllowedDuringTreatment)
    {
        if (correspondingBooking == null)
        {
            throw new IllegalArgumentException("Booking cannot be null.");
        }

        if (guestIdName == null || guestIdName.isEmpty())
        {
            throw new IllegalArgumentException("Guest ID name cannot be empty.");
        }

        // Check for valid ID
        if (!validateGuestIdentification(guestIdName, correspondingBooking))
        {
            throw new IllegalArgumentException("Guest Id is not valid.");
        }

        // Check for existing booking
        if (!this.getBookings().contains(correspondingBooking))
        {
            throw new IllegalArgumentException("This booking does not exist in our system yet.");
        }

        // Check Payment
        if (!this.makePayment())
        {
            throw new IllegalArgumentException("Guest must first complete payment.");
        }

        CheckIn newCheckIn = new CheckIn(correspondingBooking, isOutsidePoolIncluded, isMusicAllowedDuringTreatment);
        this.addCheckIn(newCheckIn);
        this.notifyDepartments(newCheckIn);

        return newCheckIn;
    }

    public boolean makePayment()
    {
        return true;
    }

    public int getRevenueInEuroCents()
    {
        int total = 0;

        for (CheckIn checkIn : this.getCheckIns())
        {
            total += checkIn.getCorrespondingBooking().getTotalBookingPriceInEuroCents();
        }

        return total;
    }
}