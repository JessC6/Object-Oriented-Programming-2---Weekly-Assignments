package com.nhlstenden.appstores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ResearchAppTest
{
    private User user1;
    private User user2;
    private User user3;

    private App app1;
    private App app2;
    private App app3;
    private App app4;

    private Currency dollars;
    private Currency euros;

    private AppleAppStore appleStore;
    private GooglePlayStore googleStore;

    private ResearchApp researchApp;

    @BeforeEach
    void setUp() throws DownloadNotAllowedException
    {
        this.user1 = new User("John", "john@gmail.com", LocalDate.now().minusYears(15));
        this.user2 = new User("Mary", "no", LocalDate.now().minusYears(18));
        this.user3 = new User("Cameron", "something@", LocalDate.now().minusYears(16));

        this.app1 = new App("Name1", 10, false, false);
        this.app2 = new App("Name2", 7, true, false);
        this.app3 = new App("Name3", 15, false, true);
        this.app4 = new App("Name4", 8, true, true);

        this.dollars = Currency.DOLLARS;
        this.euros = Currency.EUROS;

        this.appleStore = new AppleAppStore(dollars);
        this.googleStore = new GooglePlayStore(euros);

        this.researchApp = new ResearchApp();

        this.researchApp.addAppStore(this.appleStore);
        this.researchApp.addAppStore(this.googleStore);

        this.appleStore.addUser(this.user1);
        this.appleStore.addUser(this.user2);
        this.appleStore.addUser(this.user3);
        this.googleStore.addUser(this.user1);
        this.googleStore.addUser(this.user2);
        this.googleStore.addUser(this.user3);

        this.appleStore.addApp(this.app1);
        this.appleStore.addApp(this.app2);
        this.googleStore.addApp(this.app1);
        this.googleStore.addApp(this.app2);
        this.googleStore.addApp(this.app3);
        this.googleStore.addApp(this.app4);

        this.appleStore.addUserPurchase(this.user1, this.app1);
        this.appleStore.addUserPurchase(this.user2, this.app1);
        this.appleStore.addUserPurchase(this.user3, this.app1);
        this.appleStore.addUserPurchase(this.user2, this.app2);
        this.googleStore.addUserPurchase(this.user1, this.app1);
        this.googleStore.addUserPurchase(this.user2, this.app1);
        this.googleStore.addUserPurchase(this.user3, this.app1);
        this.googleStore.addUserPurchase(this.user2, this.app2);
        this.googleStore.addUserPurchase(this.user2, this.app3);
    }

    @Test
    void getRevenueInCentsFromApp_nullApp_shouldThrowException()
    {
        // Arrange
        App app5 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.researchApp.getRevenueInCentsFromApp(this.appleStore, app5));
    }

    @Test
    void getRevenueInCentsFromApp_nullAppStore_shouldThrowException()
    {
        // Arrange
        GooglePlayStore googlePlay = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.researchApp.getRevenueInCentsFromApp(googlePlay, this.app1));
    }

    @Test
    void getRevenueInCentsFromApp_nonExistentAppStore_shouldThrowException()
    {
        // Arrange
        GooglePlayStore aStore = new GooglePlayStore(this.euros);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.researchApp.getRevenueInCentsFromApp(aStore, this.app1));
    }

    @Test
    void getRevenueInCentsFromApp_googlePlayStoreWithApp1_shouldNotThrowExceptionAndReturn30()
    {
        // Action + Assert
        assertDoesNotThrow(() -> this.researchApp.getRevenueInCentsFromApp(this.googleStore, this.app1));
        assertEquals(30, this.researchApp.getRevenueInCentsFromApp(this.googleStore, this.app1));
    }

    @Test
    void getRevenueInCentsFromApp_appleAppStoreWithApp1_shouldNotThrowExceptionAndReturn21()
    {
        // Action + Assert
        assertDoesNotThrow(() -> this.researchApp.getRevenueInCentsFromApp(this.appleStore, this.app1));
        assertEquals(21, this.researchApp.getRevenueInCentsFromApp(this.appleStore, this.app1));
    }
}