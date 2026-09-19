package com.nhlstenden.appstores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppStoreTest
{
    private User user1;
    private User user2;
    private User user3;

    private App app1;
    private App app2;
    private App app3;
    private App app4;

    private Currency currency;

    private AppStore appStore;

    @BeforeEach
    void setUp()
    {
        this.user1 = new User("John", "john@gmail.com", LocalDate.now().minusYears(15));
        this.user2 = new User("Mary", "no", LocalDate.now().minusYears(18));
        this.user3 = new User("Cameron", "something@", LocalDate.now().minusYears(16));

        this.app1 = new App("Name1", 10, false, false);
        this.app2 = new App("Name2", 7, true, false);
        this.app3 = new App("Name3", 15, false, true);
        this.app4 = new App("Name4", 8, true, true);

        this.currency = Currency.EUROS;

        this.appStore = new AppStore(currency);

        this.appStore.addUser(this.user1);
        this.appStore.addUser(this.user2);
        this.appStore.addUser(this.user3);

        this.appStore.addApp(this.app1);
        this.appStore.addApp(this.app2);
        this.appStore.addApp(this.app3);
        this.appStore.addApp(this.app4);
    }

    @Test
    void addUserPurchase_appIsNull_shouldThrowExceptionAndNotAddToPurchases()
    {
        // Arrange
        App app5 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addUserPurchase(this.user1, app5));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_userIsNull_shouldThrowExceptionAndNotAddToPurchases()
    {
        // Arrange
        User user4 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addUserPurchase(user4, this.app1));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_userAndAppAreNull_shouldThrowExceptionAndNotAddToPurchases()
    {
        // Arrange
        User user4 = null;
        App app5 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addUserPurchase(user4, app5));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_userNotInList_shouldThrowExceptionAndNotAddToPurchases()
    {
        // Arrange
        User user4 = new User("Manuela", "manuela@g.com", LocalDate.now().minusYears(25));

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addUserPurchase(user4, this.app1));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_appNotInList_shouldThrowExceptionAndNotAddToPurchases()
    {
        // Arrange
        App app5 = new App("Name4", 35, false, false);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addUserPurchase(this.user1, app5));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_userNotOfAge_shouldThrowCustomExceptionAndNotAddToPurchases()
    {
        // Action + Assert
        assertThrows(DownloadNotAllowedException.class, () -> this.appStore.addUserPurchase(this.user1, this.app4));
        assertEquals(0, this.appStore.getUserPurchases().size());
    }

    @Test
    void addUserPurchase_validUserAndValidApp_shouldNotThrowExceptionAndAddToPurchases() throws DownloadNotAllowedException
    {
        // Arrange
        this.appStore.addUserPurchase(this.user2, this.app1);
        this.appStore.addUserPurchase(this.user2, this.app2);
        this.appStore.addUserPurchase(this.user2, this.app3);

        // Action + Assert
        assertDoesNotThrow(() -> this.appStore.addUserPurchase(this.user2, this.app4));
        assertEquals(4, this.appStore.getUserPurchases().get(this.user2).size());
    }
}