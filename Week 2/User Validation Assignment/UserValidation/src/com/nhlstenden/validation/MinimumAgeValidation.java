package com.nhlstenden.validation;

import com.nhlstenden.useraccount.UserAccount;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeValidation implements Validation
{
    private int minimumAgeRequired;

    public MinimumAgeValidation(int minimumAgeRequired)
    {
        this.setMinimumAgeRequired(minimumAgeRequired);
    }

    public int getMinimumAgeRequired()
    {
        return this.minimumAgeRequired;
    }

    public void setMinimumAgeRequired(int minimumAgeRequired)
    {
        if (minimumAgeRequired < 0)
        {
            throw new IllegalArgumentException("MinimumAgeRequired cannot be negative.");
        }

        this.minimumAgeRequired = minimumAgeRequired;
    }

    private int getAge(UserAccount userAccount)
    {
        LocalDate today = LocalDate.now();

        return Period.between(userAccount.getDateOfBirth(), today).getYears();
    }

    @Override
    public boolean isValid(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("UserAccount cannot be null.");
        }

        if (this.getAge(userAccount) < this.getMinimumAgeRequired())
        {
            return false;
        }

        return true;
    }
}