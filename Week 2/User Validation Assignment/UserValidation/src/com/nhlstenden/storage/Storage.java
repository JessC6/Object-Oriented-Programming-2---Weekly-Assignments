package com.nhlstenden.storage;

import com.nhlstenden.useraccount.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class Storage
{
    private List<UserAccount> validatedUserAccounts;

    public Storage()
    {
        this.setValidatedUserAccounts(new ArrayList<>());
    }

    public List<UserAccount> getValidatedUserAccounts()
    {
        return this.validatedUserAccounts;
    }

    public void setValidatedUserAccounts(List<UserAccount> validatedUserAccounts)
    {
        if (validatedUserAccounts == null)
        {
            throw new IllegalArgumentException("ValidatedUserAccounts cannot be null.");
        }

        this.validatedUserAccounts = validatedUserAccounts;
    }

    public void addValidatedUserAccount(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("ValidatedUserAccounts cannot be null.");
        }

        for (UserAccount existingUserAccount : this.getValidatedUserAccounts())
        {
            if (userAccount.getEmail().equalsIgnoreCase(existingUserAccount.getEmail()))
            {
                throw new IllegalArgumentException("An account with this email address already exists in the system.");
            }
        }

        this.getValidatedUserAccounts().add(userAccount);
    }

    public void removeValidatedUserAccount(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("ValidatedUserAccounts cannot be null.");
        }

        if (!this.getValidatedUserAccounts().contains(userAccount))
        {
            throw new IllegalArgumentException("Thi user account does not exists in the system.");
        }

        this.getValidatedUserAccounts().remove(userAccount);
    }
}