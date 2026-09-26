package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.flight.FlightStatus;
import com.nhlstenden.flightbooking.luggage.Luggage;

import com.nhlstenden.flightbooking.luggage.LuggageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PrivateAirplaneTest
{
    private PrivateAirplane privateAirplane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Flight flight;
    private Luggage luggage;

    @BeforeEach
    void setUp()
    {
        this.privateAirplane = new PrivateAirplane("P-001", 100000, 10, 4);

        this.departureAirport = new Airport(AirportCode.MEX);
        this.arrivalAirport = new Airport(AirportCode.LAX);

        this.flight = new Flight(this.departureAirport, this.arrivalAirport, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.privateAirplane);

        this.luggage = new Luggage(20.0, LuggageType.CARRY_ON);
    }

    @Test
    void setSeatsTaken_validNumberOfSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.privateAirplane.setSeatsTaken(6));
    }

    @Test
    void setSeatsTaken_thresholdNumberOfSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.privateAirplane.setSeatsTaken(10));
    }

    @Test
    void setSeatsTaken_negativeNumberOfSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.privateAirplane.setSeatsTaken(-1));
    }

    @Test
    void setSeatsTaken_invalidNumberOfSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.privateAirplane.setSeatsTaken(11));
    }

    //____________________________________________________________

    @Test
    void getFuelConsumption_validFlightWithoutLuggage_shouldNotThrowException()
    {
        // Arrange
        double calculator = this.privateAirplane.getNumberOfSeats() * 1.31 *
                flight.getDistanceInKm() + (this.privateAirplane.getSeatsTaken() * 1.87) +
                (privateAirplane.getWeightOfTotalLuggageInKg(flight) * 0.4);

        // Action + Assert
        assertDoesNotThrow(() -> this.privateAirplane.getFuelConsumption(flight));
        assertEquals(calculator, this.privateAirplane.getFuelConsumption(flight));
    }

    @Test
    void getFuelConsumption_validFlightWithLuggage_shouldNotThrowException()
    {
        // Arrange
        Luggage luggage1 = new Luggage(20, LuggageType.HOLD);
        Luggage luggage2 = new Luggage(1.2, LuggageType.CARRY_ON);

        flight.addLuggage(luggage);
        flight.addLuggage(luggage1);
        flight.addLuggage(luggage2);

        double calculator = this.privateAirplane.getNumberOfSeats() * 1.31 *
                flight.getDistanceInKm() + (this.privateAirplane.getSeatsTaken() * 1.87) +
                (privateAirplane.getWeightOfTotalLuggageInKg(flight) * 0.4);

        // Action + Assert
        assertDoesNotThrow(() -> this.privateAirplane.getFuelConsumption(flight));
        assertEquals(calculator, this.privateAirplane.getFuelConsumption(flight));
    }

    @Test
    void getFuelConsumption_flightIsNull_shouldThrowException()
    {
        // Arrange
        Flight flight = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.privateAirplane.getFuelConsumption(flight));
    }

    // I have no idea why this unit test is not working
    // I set the arrival to be the same as departure which means the distance is 0 and therefore it should throw
    @Test
    void getFuelConsumption_flightSetToTheSameAirport_shouldThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.MEX);
        Airport airport2 = new Airport(AirportCode.MEX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.BOARDING, this.privateAirplane);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.privateAirplane.getFuelConsumption(flight1));
    }

    //____________________________________________________________

    @Test
    void getTotalNumberOfEmptySeats_validAirplane_shouldReturn6()
    {
        assertEquals(6, this.privateAirplane.getTotalNumberOfEmptySeats());
    }

    //____________________________________________________________

    @Test
    void reserveSeat_airplaneNotFull_shouldNotThrowExceptionAndIncreaseSeatsTaken()
    {
        assertDoesNotThrow(() -> this.privateAirplane.reserveSeat());
        assertEquals(5, this.privateAirplane.getSeatsTaken());
    }

    @Test
    void reserveSeat_airplaneIsFull_shouldThrowExceptionAndNotIncreaseSeatsTaken()
    {
        // Arrange
        this.privateAirplane.reserveSeat();
        this.privateAirplane.reserveSeat();
        this.privateAirplane.reserveSeat();
        this.privateAirplane.reserveSeat();
        this.privateAirplane.reserveSeat();
        this.privateAirplane.reserveSeat();

        assertThrows(IllegalArgumentException.class, () -> this.privateAirplane.reserveSeat());
        assertEquals(10, this.privateAirplane.getSeatsTaken());
    }
}