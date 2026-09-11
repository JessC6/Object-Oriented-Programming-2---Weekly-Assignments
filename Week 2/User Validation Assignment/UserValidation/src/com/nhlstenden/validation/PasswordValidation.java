package com.nhlstenden.validation;

import com.nhlstenden.useraccount.UserAccount;

public class PasswordValidation implements Validation
{
    private boolean areSpacesAllowed;
    private boolean areSpecialCharactersRequired;
    private boolean areNumbersRequired;
    private boolean isLowercaseSymbolRequired;
    private boolean isUppercaseSymbolRequired;

    public PasswordValidation(boolean areSpacesAllowed, boolean areSpecialCharactersRequired, boolean areNumbersRequired, boolean isLowercaseSymbolRequired, boolean isUppercaseSymbolRequired)
    {
        this.setAreSpacesAllowed(areSpacesAllowed);
        this.setAreSpecialCharactersRequired(areSpecialCharactersRequired);
        this.setAreNumbersRequired(areNumbersRequired);
        this.setLowercaseSymbolRequired(isLowercaseSymbolRequired);
        this.setUppercaseSymbolRequired(isUppercaseSymbolRequired);
    }

    public boolean getAreSpacesAllowed()
    {
        return this.areSpacesAllowed;
    }

    public void setAreSpacesAllowed(boolean areSpacesAllowed)
    {
        this.areSpacesAllowed = areSpacesAllowed;
    }

    public boolean getAreSpecialCharactersRequired()
    {
        return this.areSpecialCharactersRequired;
    }

    public void setAreSpecialCharactersRequired(boolean areSpecialCharactersRequired)
    {
        this.areSpecialCharactersRequired = areSpecialCharactersRequired;
    }

    public boolean getAreNumbersRequired()
    {
        return this.areNumbersRequired;
    }

    public void setAreNumbersRequired(boolean areNumbersRequired)
    {
        this.areNumbersRequired = areNumbersRequired;
    }

    public boolean getIsLowercaseSymbolRequired()
    {
        return this.isLowercaseSymbolRequired;
    }

    public void setLowercaseSymbolRequired(boolean lowercaseSymbolRequired)
    {
        this.isLowercaseSymbolRequired = lowercaseSymbolRequired;
    }

    public boolean getIsUppercaseSymbolRequired()
    {
        return this.isUppercaseSymbolRequired;
    }

    public void setUppercaseSymbolRequired(boolean uppercaseSymbolRequired)
    {
        this.isUppercaseSymbolRequired = uppercaseSymbolRequired;
    }

    private boolean areSpacesBeingUsed(UserAccount userAccount)
    {
        String password = userAccount.getPassword();

        for (int i = 0; i < password.length(); i++)
        {
            if (Character.isWhitespace(password.charAt(i)))
            {
                return true;
            }
        }

        return false;
        // or
        // return this.getAreSpacedAllowed() || userAccount.getPassword().contains(" ");
    }

    private boolean areSpecialCharactersBeingUsed(UserAccount userAccount)
    {
        String password = userAccount.getPassword();

        for (int i = 0; i < password.length(); i++)
        {
            if (!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i)))
            {
                return true;
            }
        }

        return false;
    }

    private boolean areNumbersBeingUsed(UserAccount userAccount)
    {
        String password = userAccount.getPassword();

        for (int i = 0; i < password.length(); i++)
        {
            if (Character.isDigit(password.charAt(i)))
            {
                return true;
            }
        }

        return false;
    }

    private boolean isLowercaseSymbolBeingUsed(UserAccount userAccount)
    {
        String password = userAccount.getPassword();

        for (int i = 0; i < password.length(); i++)
        {
            if (Character.isLowerCase(password.charAt(i)))
            {
                return true;
            }
        }

        return false;
    }

    private boolean isUppercaseSymbolBeingUsed(UserAccount userAccount)
    {
        String password = userAccount.getPassword();

        for (int i = 0; i < password.length(); i++)
        {
            if (Character.isUpperCase(password.charAt(i)))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isValid(UserAccount userAccount)
    {
        if (userAccount == null)
        {
            throw new IllegalArgumentException("UserAccount cannot be null.");
        }

        // seek violation of validations
        if ((!this.getAreSpacesAllowed() && this.areSpacesBeingUsed(userAccount)) ||
                (this.getAreSpecialCharactersRequired() && !this.areSpecialCharactersBeingUsed(userAccount)) ||
                (this.getAreNumbersRequired() && !this.areNumbersBeingUsed(userAccount)) ||
                (this.getIsLowercaseSymbolRequired() && !this.isLowercaseSymbolBeingUsed(userAccount)) ||
                (this.getIsUppercaseSymbolRequired() && !this.isUppercaseSymbolBeingUsed(userAccount)))
        {
            return false;
        }

        return true;
    }
}