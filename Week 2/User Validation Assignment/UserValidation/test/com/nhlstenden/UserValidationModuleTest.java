package com.nhlstenden;

import com.nhlstenden.storage.Storage;
import com.nhlstenden.useraccount.UserAccount;
import com.nhlstenden.validation.EmailValidation;
import com.nhlstenden.validation.MinimumAgeValidation;
import com.nhlstenden.validation.PasswordValidation;
import com.nhlstenden.validation.UserNameValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationModuleTest
{
    private UserAccount standardUserAccount;
    private UserAccount invalidEmailUserAccount;
    private UserAccount youngUserAccount;
    private UserAccount invalidUserNameAccount;
    private UserAccount weakPasswordUserAccount;
    private UserAccount existingEmail;

    private Storage storage;

    private UserValidationModule userValidationModule;

    private EmailValidation emailValidation;
    private MinimumAgeValidation minimumAgeValidation;
    private UserNameValidation userNameValidation;

    private PasswordValidation allRulesAppliedPasswordValidation;

    @BeforeEach
    void setUp()
    {
        this.standardUserAccount = new UserAccount("John1Martin", "Idontknow0!", "john.martin@gmail.com", LocalDate.now().minusYears(18));
        this.invalidEmailUserAccount = new UserAccount("MartaHart2", "somEthing2$", "idontwanttogivemyeamil", LocalDate.now().minusYears(25));
        this.youngUserAccount = new UserAccount("KyleTensen", "!FirstPassword0", "kile@gmail.com", LocalDate.now().minusYears(10));
        this.invalidUserNameAccount = new UserAccount("John1Martin", "whateVer0!", "something@gamil.com", LocalDate.now().minusYears(20));
        this.weakPasswordUserAccount = new UserAccount("MilenaReis", "noidea", "milena@gmail.com", LocalDate.now().minusYears(19));
        this.existingEmail = new UserAccount("Michael", "euSeiLá!0", "john.martin@gmail.com", LocalDate.now().minusYears(18));

        this.storage = new Storage();

        this.userValidationModule = new UserValidationModule(storage);

        this.emailValidation = new EmailValidation();
        this.minimumAgeValidation = new MinimumAgeValidation(18);
        this.userNameValidation = new UserNameValidation(storage);

        this.allRulesAppliedPasswordValidation = new PasswordValidation(true, true, true, true, true);
    }

    @Test
    void validateUserAccount_validUser_shouldNotThrowAndAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        // Action + Assert
        assertDoesNotThrow(() -> this.userValidationModule.validateUserAccount(this.standardUserAccount));
        assertEquals(1, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_nullUser_shouldThrowAndNotAddAccountToStorage()
    {
        assertThrows(IllegalArgumentException.class, () -> this.userValidationModule.validateUserAccount(null));
        assertEquals(0, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_noValidationsChosen_shouldThrowAndNotAddAccountToStorage()
    {
        assertThrows(IllegalStateException.class, () -> this.userValidationModule.validateUserAccount(this.standardUserAccount));
        assertEquals(0, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_invalidEmailUser_shouldReturnFalseAndNotAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        // Action + Assert
        assertFalse(this.userValidationModule.validateUserAccount(this.invalidEmailUserAccount));
        assertEquals(0, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_invalidAgeUser_shouldReturnFalseAndNotAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        // Action + Assert
        assertFalse(this.userValidationModule.validateUserAccount(this.youngUserAccount));
        assertEquals(0, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_invalidUserName_shouldReturnFalseAndNotAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        this.userValidationModule.validateUserAccount(this.standardUserAccount);

        // Action + Assert
        assertFalse(this.userValidationModule.validateUserAccount(this.invalidUserNameAccount));
        assertEquals(1, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_invalidPassword_shouldReturnFalseAndNotAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        this.userValidationModule.validateUserAccount(this.standardUserAccount);

        // Action + Assert
        assertFalse(this.userValidationModule.validateUserAccount(this.weakPasswordUserAccount));
        assertEquals(1, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_existingUserWithEmail_shouldThrowAndNotAddAccountToStorage()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(this.allRulesAppliedPasswordValidation);

        this.userValidationModule.validateUserAccount(this.standardUserAccount);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.userValidationModule.validateUserAccount(this.existingEmail));
        assertEquals(1, this.storage.getValidatedUserAccounts().size());
    }

    @Test
    void validateUserAccount_existingEmail_shouldHandleException()
    {
        // Arrange
        this.userValidationModule.addValidation(this.emailValidation);
        this.userValidationModule.addValidation(this.minimumAgeValidation);
        this.userValidationModule.addValidation(this.userNameValidation);
        this.userValidationModule.addValidation(
                this.allRulesAppliedPasswordValidation
        );

        this.userValidationModule.validateUserAccount(this.standardUserAccount);

        // Act + Assert
        try
        {
            this.userValidationModule.validateUserAccount(this.existingEmail);

            fail("Expected IllegalArgumentException to be thrown.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(
                    "An account with this email address already exists in the system.",
                    e.getMessage()
            );
        }

        assertEquals(1, this.storage.getValidatedUserAccounts().size());
    }
}