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

class CommercialAirplaneTest
{
    private CommercialAirplane commercialAirplane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Flight flight;
    private Luggage luggage;

    @BeforeEach
    void setUp()
    {
        this.commercialAirplane = new CommercialAirplane("C-001", 100000, 100, 20, 50, 10);

        this.departureAirport = new Airport(AirportCode.AMS);
        this.arrivalAirport = new Airport(AirportCode.LAX);

        this.flight = new Flight(this.departureAirport, this.arrivalAirport, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, this.commercialAirplane);

        this.luggage = new Luggage(20.0, LuggageType.HOLD);
    }

    @Test
    void setEconomySeatsTaken_negativeSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.setEconomySeatsTaken(-1));
    }

    @Test
    void setEconomySeatsTaken_thresholdSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.commercialAirplane.setEconomySeatsTaken(100));
    }

    @Test
    void setEconomySeatsTaken_noSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.commercialAirplane.setEconomySeatsTaken(0));
    }

    @Test
    void setEconomySeatsTaken_aboveThresholdSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.setEconomySeatsTaken(101));
    }

    //---------------------------------------------------------

    @Test
    void setBusinessSeatsTaken_negativeSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.setBusinessSeatsTaken(-1));
    }

    @Test
    void setBusinessSeatsTaken_thresholdSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.commercialAirplane.setBusinessSeatsTaken(20));
    }
    @Test
    void setBusinessSeatsTaken_noSeatsTaken_shouldNotThrowException()
    {
        assertDoesNotThrow(() -> this.commercialAirplane.setBusinessSeatsTaken(0));
    }

    @Test
    void setBusinessSeatsTaken_aboveThresholdSeatsTaken_shouldThrowException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.setBusinessSeatsTaken(21));
    }

    //---------------------------------------------------------

    @Test
    void getFuelConsumption_validFlightWithoutLuggage_shouldNotThrowException()
    {
        // Arrange
        double calculator = ((this.commercialAirplane.getEconomySeats() * 1.75) + (this.commercialAirplane.getBusinessSeats() * 1.98)) *
                flight.getDistanceInKm() + (this.commercialAirplane.getEconomySeatsTaken() * 2.02) +
                (this.commercialAirplane.getBusinessSeatsTaken() * 2.87) + (commercialAirplane.getWeightOfTotalLuggageInKg(flight) * 0.3);

        // Action + Assert
        assertDoesNotThrow(() -> this.commercialAirplane.getFuelConsumption(flight));
        assertEquals(calculator, this.commercialAirplane.getFuelConsumption(flight));
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

        double calculator = ((this.commercialAirplane.getEconomySeats() * 1.75) + (this.commercialAirplane.getBusinessSeats() * 1.98)) *
                flight.getDistanceInKm() + (this.commercialAirplane.getEconomySeatsTaken() * 2.02) +
                (this.commercialAirplane.getBusinessSeatsTaken() * 2.87) + (commercialAirplane.getWeightOfTotalLuggageInKg(flight) * 0.3);

        // Action + Assert
        assertDoesNotThrow(() -> this.commercialAirplane.getFuelConsumption(flight));
        assertEquals(calculator, this.commercialAirplane.getFuelConsumption(flight));
    }

    @Test
    void getFuelConsumption_flightIsNull_shouldThrowException()
    {
        // Arrange
        Flight flight = null;

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.getFuelConsumption(flight));
    }

    // I have no idea why this unit test is not working
    // I set the arrival to be the same as departure which means the distance is 0 and therefore it should throw
    @Test
    void getFuelConsumption_flightSetToTheSameAirport_shouldThrowException()
    {
        // Arrange
        Airport airport1 = new Airport(AirportCode.MEX);
        Airport airport2 = new Airport(AirportCode.MEX);

        Flight flight1 = new Flight(airport1, airport2, LocalDateTime.now().plusMonths(3), FlightStatus.BOARDING, this.commercialAirplane);

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.getFuelConsumption(flight1));
    }

    //---------------------------------------------------------
    @Test
    void getTotalNumberOfEmptySeats_validAirplane_shouldReturn60()
    {
        assertEquals(60, this.commercialAirplane.getTotalNumberOfEmptySeats());
    }

    //---------------------------------------------------------

    @Test
    void reserveSeat_airplaneNotFull_shouldNotThrowExceptionAndIncreaseEconomySeatsTaken()
    {
        assertDoesNotThrow(() -> this.commercialAirplane.reserveSeat());
        assertEquals(51, this.commercialAirplane.getEconomySeatsTaken());
    }
    @Test
    void reserveSeat_airplaneEconomySeatsFull_shouldNotThrowExceptionAndIncreaseBusinessSeatsTaken()
    {
        // Arrange
        this.commercialAirplane.setEconomySeatsTaken(100);

        assertDoesNotThrow(() -> this.commercialAirplane.reserveSeat());
        assertEquals(100, this.commercialAirplane.getEconomySeatsTaken());
        assertEquals(11, this.commercialAirplane.getBusinessSeatsTaken());
    }

    @Test
    void reserveSeat_airplaneIsFull_shouldThrowExceptionAndNotIncreaseSeatsTaken()
    {
        // Arrange
        this.commercialAirplane.setEconomySeatsTaken(100);
        this.commercialAirplane.setBusinessSeatsTaken(20);

        assertThrows(IllegalArgumentException.class, () -> this.commercialAirplane.reserveSeat());
        assertEquals(100, this.commercialAirplane.getEconomySeatsTaken());
        assertEquals(20, this.commercialAirplane.getBusinessSeatsTaken());
    }
}