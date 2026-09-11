package com.nhlstenden;

import com.nhlstenden.storage.Storage;
import com.nhlstenden.useraccount.UserAccount;
import com.nhlstenden.validation.Validation;

import java.util.ArrayList;
import java.util.List;

public class UserValidationModule
{
    private Storage storage;
    private List<Validation> chosenValidations;

    public UserValidationModule(Storage storage)
    {
        this.setStorage(storage);
        this.setChosenValidations(new ArrayList<>());
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

    public List<Validation> getChosenValidations()
    {
        return this.chosenValidations;
    }

    public void setChosenValidations(List<Validation> choosenValidations)
    {
        if (choosenValidations == null)
        {
            throw new IllegalArgumentException("ChoosenValidations cannot be null.");
        }

        this.chosenValidations = choosenValidations;
    }

    public void addValidation(Validation validation)
    {
        if (validation == null)
        {
            throw new IllegalArgumentException("Validation cannot be null.");
        }

        if (this.getChosenValidations().contains(validation))
        {
            throw new IllegalArgumentException("This validation is already present in the list.");
        }

        this.getChosenValidations().add(validation);
    }

    public void removeValidation(Validation validation)
    {
        if (validation == null)
        {
            throw new IllegalArgumentException("Validation cannot be null.");
        }

        if (!this.getChosenValidations().contains(validation))
        {
            throw new IllegalArgumentException("This validation does not exist in the list.");
        }

        this.getChosenValidations().remove(validation);
    }

    public boolean validateUserAccount(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("UserAcoount cannot be null.");
        }

        if (this.getChosenValidations().isEmpty())
        {
            throw new IllegalStateException("At least one validation must be chosen.");
        }

        for (Validation validation : this.getChosenValidations())
        {
            if (!validation.isValid(userAccount))
            {
                return false;
            }
        }

        this.getStorage().addValidatedUserAccount(userAccount);

        return true;
    }
}