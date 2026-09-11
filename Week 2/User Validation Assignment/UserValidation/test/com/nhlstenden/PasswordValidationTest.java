package com.nhlstenden;

import com.nhlstenden.useraccount.UserAccount;
import com.nhlstenden.validation.PasswordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationTest
{
    private UserAccount strongPasswordAccount;
    private UserAccount weakPasswordAccount;

    private PasswordValidation allRulesAppliedPasswordValidation;
    private PasswordValidation noRulesAppliedPasswordValidation;
    private PasswordValidation spacesAreNotAllowed;
    private PasswordValidation specialCharactersAreRequired;
    private PasswordValidation numbersAreRequired;
    private PasswordValidation lowercaseRequired;
    private PasswordValidation uppercaseRequired;

    @BeforeEach
    void setUp()
    {
        this.strongPasswordAccount = new UserAccount("John1Martin", "I dont know0!", "john.martin@gmail.com", LocalDate.now().minusYears(18));
        this.weakPasswordAccount = new UserAccount("MartaHart2", "something", "idontwanttogivemyeamil", LocalDate.now().minusYears(25));

        this.allRulesAppliedPasswordValidation = new PasswordValidation(false, true, true, true, true);
        this.noRulesAppliedPasswordValidation = new PasswordValidation(true, false, false, false, false);
        this.spacesAreNotAllowed = new PasswordValidation(false, false, false, false, false);
        this.specialCharactersAreRequired = new PasswordValidation(true, true, false, false, false);
        this.numbersAreRequired = new PasswordValidation(true, false, true, false, false);
        this.lowercaseRequired = new PasswordValidation(true, false, false, true, false);
        this.uppercaseRequired = new PasswordValidation(true, false, false, false,true);
    }

    @Test
    void isValid_noPasswordValidationsWithNullAccount_shouldThrow()
    {
        assertThrows(IllegalArgumentException.class, () -> this.noRulesAppliedPasswordValidation.isValid(null));
    }

    @Test
    void isValid_noPasswordValidations_shouldReturnTrueForBoth()
    {
        assertTrue(this.noRulesAppliedPasswordValidation.isValid(strongPasswordAccount));
        assertTrue(this.noRulesAppliedPasswordValidation.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_allPasswordValidations_shouldReturnFalseForBoth()
    {
        assertFalse(this.allRulesAppliedPasswordValidation.isValid(strongPasswordAccount));
        assertFalse(this.allRulesAppliedPasswordValidation.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_spacesAreNotAllowed_shouldReturnFalseForStrongAccountAndTrueForWeakAccount()
    {
        assertFalse(this.spacesAreNotAllowed.isValid(strongPasswordAccount));
        assertTrue(this.spacesAreNotAllowed.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_specialCharactersRequired_shouldReturnTrueForStrongAccountAndFalseForWeakAccount()
    {
        assertTrue(this.specialCharactersAreRequired.isValid(strongPasswordAccount));
        assertFalse(this.specialCharactersAreRequired.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_numbersAreRequired_shouldReturnTrueForStrongAccountAndFalseForWeakAccount()
    {
        assertTrue(this.numbersAreRequired.isValid(strongPasswordAccount));
        assertFalse(this.numbersAreRequired.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_lowercaseRequired_shouldReturnTrueForboth()
    {
        assertTrue(this.lowercaseRequired.isValid(strongPasswordAccount));
        assertTrue(this.lowercaseRequired.isValid(weakPasswordAccount));
    }

    @Test
    void isValid_uppercaseRequired_shouldReturnTrueForStrongAccountAndFalseForWeakAccount()
    {
        assertTrue(this.uppercaseRequired.isValid(strongPasswordAccount));
        assertFalse(this.uppercaseRequired.isValid(weakPasswordAccount));
    }
}