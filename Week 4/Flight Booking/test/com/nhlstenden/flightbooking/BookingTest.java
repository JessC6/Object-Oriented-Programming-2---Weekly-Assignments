package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.CommercialAirplane;
import com.nhlstenden.flightbooking.airplane.PrivateAirplane;
import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.flight.FlightStatus;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;
import com.nhlstenden.flightbooking.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest
{
    private Person person;
    private PrivateAirplane privateAirplane;
    private CommercialAirplane commercialAirplane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Flight privateFlight;
    private Flight commercialFlight;
    private Booking booking;
    private Luggage carryOn;
    private Luggage holdLuggage;

    @BeforeEach
    void setUp()
    {
        this.person = new Person("John");

        this.departureAirport = new Airport(AirportCode.AMS);
        this.arrivalAirport = new Airport(AirportCode.LAX);

        this.privateAirplane = new PrivateAirplane("P-001", 100000, 10, 2);

        this.commercialAirplane = new CommercialAirplane("C-001", 100000, 100, 20, 50, 10);

        this.privateFlight = new Flight(this.departureAirport, this.arrivalAirport, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.privateAirplane);

        this.commercialFlight = new Flight(this.departureAirport, this.arrivalAirport, LocalDateTime.now().plusMonths(6), FlightStatus.AWAITING_DEPARTURE, this.commercialAirplane);

        this.booking = new Booking(this.person, this.commercialFlight);

        this.carryOn = new Luggage(8.0, LuggageType.CARRY_ON);
        this.holdLuggage = new Luggage(20.0, LuggageType.HOLD);
    }

    @Test
    void setFlight_nullFlight_shouldThrowException()
    {
        // Arrange
        Flight flight1 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.setFlight(flight1));
    }

    @Test
    void setFlight_notAvailableFlight_shouldThrowException()
    {
        // Arrange
        this.privateFlight.setStatus(FlightStatus.LANDED);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.setFlight(privateFlight));
    }

    @Test
    void setFlight_availableFlight_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.booking.setFlight(privateFlight));
    }

    //-------------------------------------------------------

    @Test
    void addLuggage_NullLuggage_shouldThrowExceptionAndNotAddLuggage()
    {
        // Arrange
        Luggage luggage1 = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.addLuggage(luggage1));
        assertEquals(0, this.booking.getLuggage().size());
    }

    @Test
    void addLuggage_existentLuggage_shouldThrowExceptionAndNotAddLuggage()
    {
        // Arrange
        this.booking.addLuggage(this.holdLuggage);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.addLuggage(this.holdLuggage));
        assertEquals(1, this.booking.getLuggage().size());
    }

    @Test
    void addLuggage_commercialFlightFirstCarryOn_shouldNotThrowExceptionAndAddLuggage()
    {
        // Arrange
        this.booking.setFlight(this.commercialFlight);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.addLuggage(this.carryOn));
        assertEquals(1, this.booking.getLuggage().size());
        assertEquals(1, this.commercialFlight.getLuggage().size());
    }

    @Test
    void addLuggage_commercialFlightFirstHold_shouldNotThrowExceptionAndAddLuggage()
    {
        // Arrange
        this.booking.setFlight(this.commercialFlight);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.addLuggage(this.holdLuggage));
        assertEquals(1, this.booking.getLuggage().size());
        assertEquals(1, this.commercialFlight.getLuggage().size());
    }

    @Test
    void addLuggage_commercialFlightSecondCarryOn_shouldThrowExceptionAndNotAddLuggage()
    {
        // Arrange
        Luggage secondCarryOn = new Luggage(1.5, LuggageType.CARRY_ON);

        this.booking.setFlight(this.commercialFlight);
        this.booking.addLuggage(this.carryOn);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.addLuggage(secondCarryOn));
        assertEquals(1, this.booking.getLuggage().size());
        assertEquals(1, this.commercialFlight.getLuggage().size());
    }

    @Test
    void addLuggage_commercialFlightSecondHold_shouldNotThrowExceptionAndAddLuggage()
    {
        // Arrange
        Luggage secondHold = new Luggage(15.8, LuggageType.HOLD);

        this.booking.setFlight(this.commercialFlight);
        this.booking.addLuggage(this.holdLuggage);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.addLuggage(secondHold));
        assertEquals(2, this.booking.getLuggage().size());
        assertEquals(2, this.commercialFlight.getLuggage().size());
    }

    @Test
    void addLuggage_privateFlightFirstCarryOn_shouldNotThrowExceptionAndAddLuggage()
    {
        // Arrange
        this.booking.setFlight(this.privateFlight);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.addLuggage(this.carryOn));
        assertEquals(1, this.booking.getLuggage().size());
        assertEquals(1, this.privateFlight.getLuggage().size());
    }

    @Test
    void addLuggage_privateFlightSecondCarryOn_shouldNotThrowExceptionAndAddLuggage()
    {
        // Arrange
        Luggage secondCarryOn = new Luggage(1.5, LuggageType.CARRY_ON);

        this.booking.setFlight(this.privateFlight);
        this.booking.addLuggage(carryOn);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.addLuggage(secondCarryOn));
        assertEquals(2, this.booking.getLuggage().size());
        assertEquals(2, this.privateFlight.getLuggage().size());
    }

    @Test
    void addLuggage_privateFlightFirstHold_shouldThrowExceptionAndNotAddLuggage()
    {
        // Arrange
        this.booking.setFlight(this.privateFlight);
        this.privateFlight.addLuggage(this.carryOn);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.booking.addLuggage(holdLuggage));
        assertEquals(0, this.booking.getLuggage().size());
        assertEquals(1, this.privateFlight.getLuggage().size());
    }

    //-------------------------------------------------------

    @Test
    void reserveSeat_commercialFlight_shouldNotThrowAndDecreaseNumberOfEmptySeats()
    {
        // Arrange
        this.booking.setFlight(this.commercialFlight);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.reserveSeat());
        assertEquals(59, this.commercialAirplane.getTotalNumberOfEmptySeats());
    }

    @Test
    void reserveSeat_privateFlight_shouldNotThrowAndDecreaseNumberOfEmptySeats()
    {
        // Arrange
        this.booking.setFlight(this.privateFlight);

        // Action + Assert
        assertDoesNotThrow(() -> this.booking.reserveSeat());
        assertEquals(7, this.privateAirplane.getTotalNumberOfEmptySeats());
    }
}