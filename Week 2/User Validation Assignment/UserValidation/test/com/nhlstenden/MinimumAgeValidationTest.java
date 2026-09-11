package com.nhlstenden;

import com.nhlstenden.useraccount.UserAccount;
import com.nhlstenden.validation.MinimumAgeValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MinimumAgeValidationTest
{
    private UserAccount youngUser;
    private UserAccount thresholdAgeUser;
    private UserAccount oldUser;

    private MinimumAgeValidation minimumAgeValidation;

    @BeforeEach
    void setUp()
    {
        this.youngUser = new UserAccount("John1Martin", "I dont know0!", "john.martin@gmail.com", LocalDate.now().minusYears(15));
        this.thresholdAgeUser = new UserAccount("MartaHart2", "something", "idontwanttogivemyeamil", LocalDate.now().minusYears(18));
        this.oldUser = new UserAccount("Cameron", "seilá!", "algo@gmail.com", LocalDate.now().minusYears(24));

        this.minimumAgeValidation = new MinimumAgeValidation(18);
    }

    @Test
    void isValid_nullUser_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.minimumAgeValidation.isValid(null));
    }

    @Test
    void isValid_youngUser_shouldReturnFalse()
    {
        assertFalse(this.minimumAgeValidation.isValid(this.youngUser));
    }

    @Test
    void isValid_thresholdAgeUser_shouldReturnTrue()
    {
        assertTrue(this.minimumAgeValidation.isValid(this.thresholdAgeUser));
    }

    @Test
    void isValid_oldUser_shouldReturnTrue()
    {
        assertTrue(this.minimumAgeValidation.isValid(this.oldUser));
    }
}