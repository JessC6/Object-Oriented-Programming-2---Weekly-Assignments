package com.nhlstenden.flightbooking.flight;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.airplane.PrivateAirplane;
import com.nhlstenden.flightbooking.airport.Airport;
import com.nhlstenden.flightbooking.airport.AirportCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class Flight24UploaderTest
{
    private Flight24Uploader uploader;
    private Flight flight;

    @BeforeEach
    void setUp()
    {
        this.uploader = new Flight24Uploader();

        Airport departure = new Airport(AirportCode.AMS);
        Airport arrival = new Airport(AirportCode.LAX);

        Airplane airplane = new PrivateAirplane("P-001", 100000, 10, 2);

        this.flight = new Flight(departure, arrival, LocalDateTime.now().plusMonths(3), FlightStatus.AWAITING_DEPARTURE, airplane);

        this.uploader.getFlights().add(this.flight);
    }

    @Test
    void uploadOverallInformation_emptyFlightList_shouldThrowException()
    {
        // Arrange
        Flight24Uploader uploader1 = new Flight24Uploader();

        // Action + Assert
        assertThrows(IllegalArgumentException.class, () -> uploader1.uploadOverallInformation());
    }

    @Test
    void uploadOverallInformation_oneFlight_shouldNotThrowExceptionAndCreateFileWithInformation()
    {
        assertDoesNotThrow(() -> this.uploader.uploadOverallInformation());
    }
}