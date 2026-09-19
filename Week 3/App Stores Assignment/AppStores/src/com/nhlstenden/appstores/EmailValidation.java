package com.nhlstenden.appstores;

public class EmailValidation
{
    public static boolean validateEmailAddress(User user)
    {
        if (user == null || user.getEmail() == null)
        {
            return  false;
        }

        return user.getEmail().contains("@") &&
                user.getEmail().contains(".");
    }
}