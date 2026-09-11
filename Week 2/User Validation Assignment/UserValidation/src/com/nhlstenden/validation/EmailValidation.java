package com.nhlstenden.validation;

import com.nhlstenden.useraccount.UserAccount;

public class EmailValidation implements Validation
{
    @Override
    public boolean isValid(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("UserAccount cannot be null.");
        }

        if (!userAccount.getEmail().contains("@") || !userAccount.getEmail().contains("."))
        {
            return false;
        }

        return true;
    }
}