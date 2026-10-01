package com.nhlstenden.thermostatresort.guest;

import java.time.LocalDate;
import java.time.Period;

public class Guest
{
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private LocalDate dateOfBirth;

    public Guest(String name, String address, String email, int phoneNumber, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName()
    {
        return this.name;
    }

    private void setName(String name)
    {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public String getAddress()
    {
        return this.address;
    }

    private void setAddress(String address)
    {
        if (address == null || address.isEmpty())
        {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }

        this.address = address;
    }

    public String getEmail()
    {
        return this.email;
    }

    private void setEmail(String email)
    {
        if (email == null || email.isEmpty())
        {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        this.email = email;
    }

    public int getPhoneNumber()
    {
        return this.phoneNumber;
    }

    private void setPhoneNumber(int phoneNumber)
    {
        if (phoneNumber < 0)
        {
            throw new IllegalArgumentException("PhoneNumber cannot be negative.");
        }

        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("DateOfBirth cannot be null.");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        LocalDate today = LocalDate.now();

        return Period.between(this.getDateOfBirth(),today).getYears();
    }
}