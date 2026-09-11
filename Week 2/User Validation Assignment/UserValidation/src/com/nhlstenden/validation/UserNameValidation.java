package com.nhlstenden.validation;

import com.nhlstenden.storage.Storage;
import com.nhlstenden.useraccount.UserAccount;

public class UserNameValidation implements Validation
{
    private Storage storage;

    public UserNameValidation(Storage storage)
    {
        this.setStorage(storage);
    }

    public Storage getStorage()
    {
        return this.storage;
    }

    public void setStorage(Storage storage)
    {
        if (storage == null)
        {
            throw new IllegalArgumentException("Storage cannot be null.");
        }

        this.storage = storage;
    }

    @Override
    public boolean isValid(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("UserAccount cannot be null.");
        }

        for (UserAccount userAccountsInStorage : this.getStorage().getValidatedUserAccounts())
        {
            if (userAccountsInStorage.getUserName().equalsIgnoreCase(userAccount.getUserName()))
            {
                return false;
            }
        }

        return true;
    }
}