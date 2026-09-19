package com.nhlstenden.appstores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppleAppStoreTest
{
    private App app1;
    private App app2;
    private App app3;
    private App app4;

    private Currency appleCurrency;

    private AppleAppStore appleStore;

    @BeforeEach
    void setUp()
    {
        this.app1 = new App("Name1", 10, false, false);
        this.app2 = new App("Name2", 7, true, false);
        this.app3 = new App("Name3", 15, false, true);
        this.app4 = new App("Name4", 8, true, true);

        this.appleCurrency = Currency.DOLLARS;

        this.appleStore = new AppleAppStore(appleCurrency);
    }

    @Test
    void addApp_appIsNull_shouldThrowExceptionAndNotAddApp()
    {
        // Arrange
        App app5 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appleStore.addApp(app5));
        assertEquals(0, this.appleStore.getApps().size());
    }

    @Test
    void addApp_appAlreadyExists_shouldThrowExceptionAndNotAddApp()
    {
        // Arrange
        this.appleStore.addApp(this.app1);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appleStore.addApp(this.app1));
        assertEquals(1, this.appleStore.getApps().size());
    }

    @Test
    void addApp_appWithNudity_shouldThrowExceptionAndNotAddApp()
    {
        // Arrange
        this.appleStore.addApp(this.app1);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.appleStore.addApp(this.app3));
        assertEquals(1, this.appleStore.getApps().size());
    }

    @Test
    void addApp_validApp_shouldNotThrowExceptionAndAddApp()
    {
        // Arrange
        this.appleStore.addApp(this.app1);

        // Action + Assert
        assertDoesNotThrow(() -> this.appleStore.addApp(this.app2));
        assertEquals(2, this.appleStore.getApps().size());
    }
}