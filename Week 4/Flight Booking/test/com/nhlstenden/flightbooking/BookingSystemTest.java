package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.PrivateAirplane;
import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.flight.FlightStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest
{
    private BookingSystem bookingSystem;
    private Flight amsToLax;
    private Flight jfkToMex;
    private Flight unavailableFlight;
    private PrivateAirplane airplane;

    @BeforeEach
    void setUp()
    {
        this.bookingSystem = new BookingSystem();

        Airport ams = new Airport(AirportCode.AMS);
        Airport lax = new Airport(AirportCode.LAX);
        Airport jfk = new Airport(AirportCode.JFK);
        Airport mex = new Airport(AirportCode.MEX);

        this.airplane = new PrivateAirplane("P-001", 100000, 10, 2);

        this.amsToLax = new Flight(ams, lax, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        this.jfkToMex = new Flight(jfk, mex, LocalDateTime.now().plusMonths(5), FlightStatus.AWAITING_DEPARTURE, new PrivateAirplane("P-002", 100000, 10, 2));

        this.unavailableFlight = new Flight(mex, ams, LocalDateTime.now().plusMonths(8), FlightStatus.DEPARTED, new PrivateAirplane("P-003", 100000, 10, 2));

        this.bookingSystem.getFlights().add(this.amsToLax);
        this.bookingSystem.getFlights().add(this.jfkToMex);
        this.bookingSystem.getFlights().add(this.unavailableFlight);
    }

    @Test
    void getFirstAvailableFlight_nullAirports_showThrowException()
    {
        // Arrange
        Airport airport1 = null;
        Airport airport2 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_nullDepartureAirport_showThrowException()
    {
        // Arrange
        Airport airport1 = null;
        Airport airport2 = new Airport(AirportCode.AMS);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_nullArrivalAirport_showThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.AMS);
        Airport airport2 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_noFlights_showThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.AMS);
        Airport airport2 = new Airport(AirportCode.LAX);

        BookingSystem bookingSystem1 = new BookingSystem();

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> bookingSystem1.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_noMatchingFlightRoute_showThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.LAX);
        Airport airport2 = new Airport(AirportCode.MEX);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_matchingUnavailableFlightRoute_showThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.MEX);
        Airport airport2 = new Airport(AirportCode.AMS);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_matchingFlightRoute_showNotThrowExceptionAndReturnCorrespondingFlight()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.AMS);
        Airport airport2 = new Airport(AirportCode.LAX);

        // Action + Assert
        assertDoesNotThrow(() -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
        assertEquals(this.amsToLax, this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }

    @Test
    void getFirstAvailableFlight_anotherMatchingFlightRoute_showNotThrowExceptionAndReturnCorrespondingFlight()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.JFK);
        Airport airport2 = new Airport(AirportCode.MEX);

        // Action + Assert
        assertDoesNotThrow(() -> this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
        assertEquals(this.jfkToMex, this.bookingSystem.getFirstAvailableFlight(airport1, airport2));
    }
}