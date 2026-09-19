package com.nhlstenden.appstores;

import java.time.LocalDate;
import java.time.Period;

public class User
{
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public User(String name, String email, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.email = email;
        this.setDateOfBirth(dateOfBirth);

        if (!EmailValidation.validateEmailAddress(this))
        {
            this.email = null;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null  || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }

    //public void setEmail(String email)
    //{
    //    if (email != null && email.isEmpty())
    //    {
    //        throw new IllegalArgumentException("Email cannot be empty.");
    //    }

    //    this.email = email;
    //}

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("DateOfBirth cannot be null.");
        }

        LocalDate today = LocalDate.now();

        if (dateOfBirth.isAfter(today))
        {
            throw new IllegalArgumentException("DateOfBirth cannot be set in the future.");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        LocalDate today = LocalDate.now();

        return Period.between(this.getDateOfBirth(), today).getYears();
    }
}