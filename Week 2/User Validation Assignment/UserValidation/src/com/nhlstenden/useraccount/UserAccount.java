package com.nhlstenden.useraccount;

import java.time.LocalDate;

public class UserAccount
{
    private String userName;
    private String password;
    private String email;
    private LocalDate dateOfBirth;

    public UserAccount(String userName, String password, String email, LocalDate dateOfBirth)
    {
        this.setUserName(userName);
        this.setPassword(password);
        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        if (userName == null || userName.isBlank())
        {
            throw new IllegalArgumentException("UserName cannot be null or blank.");
        }

        this.userName = userName;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        if (password == null || password.isBlank())
        {
            throw new IllegalArgumentException("Password cannot be null or blank.");
        }

        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        if (email == null || email.isBlank())
        {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }

        this.email = email;
    }

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

        this.dateOfBirth = dateOfBirth;
    }
}