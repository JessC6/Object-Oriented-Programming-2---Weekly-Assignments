package com.nhlstenden.flightbooking.flight;

import com.nhlstenden.flightbooking.airplane.PrivateAirplane;
import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest
{

    private Airport departureAirport;
    private Airport arrivalAirport;
    private PrivateAirplane airplane;
    private Flight flight;
    private Luggage luggage;

    @BeforeEach
    void setUp()
    {
        this.departureAirport = new Airport(AirportCode.AMS);
        this.arrivalAirport = new Airport(AirportCode.LAX);

        this.airplane = new PrivateAirplane("P-001", 100000, 10, 4);

        this.flight = new Flight(this.departureAirport, this.arrivalAirport, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        this.luggage = new Luggage(20.0, LuggageType.CARRY_ON);
    }

    @Test
    void getDistanceInKm_betweenJfkAndAms_shouldReturn5848()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.JFK);
        Airport airport2 = new Airport(AirportCode.AMS);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(5848, flight1.getDistanceInKm());
        assertEquals(5848, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenJfkAndMex_shouldReturn3366()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.JFK);
        Airport airport2 = new Airport(AirportCode.MEX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(3366, flight1.getDistanceInKm());
        assertEquals(3366, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenJfkAndLax_shouldReturn3975()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.JFK);
        Airport airport2 = new Airport(AirportCode.LAX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(3975, flight1.getDistanceInKm());
        assertEquals(3975, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenAmsAndMex_shouldReturn9206()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.AMS);
        Airport airport2 = new Airport(AirportCode.MEX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(9206, flight1.getDistanceInKm());
        assertEquals(9206, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenAmsAndLax_shouldReturn8956()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.AMS);
        Airport airport2 = new Airport(AirportCode.LAX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(8956, flight1.getDistanceInKm());
        assertEquals(8956, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenMexAndLax_shouldReturn2500()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.MEX);
        Airport airport2 = new Airport(AirportCode.LAX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);
        Flight flight2 = new Flight(airport2, airport1, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(2500, flight1.getDistanceInKm());
        assertEquals(2500, flight2.getDistanceInKm());
    }

    @Test
    void getDistanceInKm_betweenTheSameAirport_shouldReturn0()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.MEX);
        Airport airport2 = new Airport(AirportCode.MEX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.airplane);

        // Action + Assert
        assertEquals(0, flight1.getDistanceInKm());
    }

    //---------------------------------------------------------

    @Test
    void getFlightInformation_expectedFormat_shouldReturnTheCorrectFormat()
    {
        // Arrange
        String expected = String.format("F: AMS -> LAX. Departure %1$td-%1$tm-%1$tY %1$tH:%1$tM.", LocalDateTime.now().plusMonths(3));

        // Action + Assert
        assertEquals(expected, this.flight.getFlightInformation());
    }

    //---------------------------------------------------------

    @Test
    void getAirplaneInformation_expectedFormat_shouldReturnTheCorrectFormat()
    {
        // Arrange
        String expected = "P: P-001. 100000 liter fuel. 6 empty seats.";

        // Action + Assert
        assertEquals(expected, this.flight.getAirplaneInformation());
    }

    //---------------------------------------------------------

    @Test
    void getOverallInformation_expectedFormat_shouldReturnTheCorrectFormat()
    {
        // Arrange
        StringBuilder sb = new StringBuilder("");

        sb.append(this.flight.getFlightInformation());
        sb.append("\n");
        sb.append(this.flight.getAirplaneInformation());

        String expected = sb.toString();

        assertEquals(expected, this.flight.getOverallInformation());
    }

    //---------------------------------------------------------

    @Test
    void depart_notEnoughFuel_shouldThrowExceptionAndNotChangeFlightStatus()
    {
        assertThrows(IllegalArgumentException.class, () -> this.flight.depart());
        assertEquals(FlightStatus.AWAITING_DEPARTURE, this.flight.getStatus());
    }

    @Test
    void depart_enoughFuel_shouldNotThrowExceptionAndChangeFlightStatusToDeparted()
    {
        // Arrange
        double calculator = this.airplane.getNumberOfSeats() * 1.31 *
                flight.getDistanceInKm() + (this.airplane.getSeatsTaken() * 1.87) +
                (20.0 * 0.4);

        this.flight.addLuggage(luggage);
        this.airplane.setCurrentFuelLevel(calculator);

        assertDoesNotThrow(() -> this.flight.depart());
        assertEquals(FlightStatus.DEPARTED, this.flight.getStatus());
    }

    //---------------------------------------------------------

    @Test
    void isAvailable_awaitingDepartureStatusAndSeatsAvailable_shouldReturnTrue()
    {
        assertTrue(this.flight.isAvailable());
    }

    @Test
    void isAvailable_boardingStatusAndSeatsAvailable_shouldReturnTrue()
    {
        // Arrange
        this.flight.setStatus(FlightStatus.BOARDING);

        // Action + Assert
        assertTrue(this.flight.isAvailable());
    }

    @Test
    void isAvailable_departedStatusAndSeatsAvailable_shouldReturnFalse()
    {
        // Arrange
        this.flight.setStatus(FlightStatus.DEPARTED);

        // Action + Assert
        assertFalse(this.flight.isAvailable());
    }

    @Test
    void isAvailable_landedStatusAndSeatsAvailable_shouldReturnFalse()
    {
        // Arrange
        this.flight.setStatus(FlightStatus.LANDED);

        // Action + Assert
        assertFalse(this.flight.isAvailable());
    }

    @Test
    void isAvailable_awaitingDepartureStatusAndNoSeatsAvailable_shouldReturnFalse()
    {
        // Arrange
        this.airplane.setSeatsTaken(10);

        // Action + Assert
        assertFalse(this.flight.isAvailable());
    }

    //---------------------------------------------------------

    @Test
    void reserveSeat_availableFlight_shouldNotThrowExceptionAndIncreaseSeatsTakenOnAirplane()
    {
        assertDoesNotThrow(() -> this.flight.reserveSeat());
        assertEquals(5, this.airplane.getSeatsTaken());
    }

    @Test
    void reserveSeat_notAvailableFlight_shouldThrowExceptionAndNotIncreaseSeatsTakenOnAirplane()
    {
        // Arrange
        this.flight.setStatus(FlightStatus.LANDED);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.flight.reserveSeat());
        assertEquals(4, this.airplane.getSeatsTaken());
    }
}