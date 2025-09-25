package com.atcsimulator.app.engine.simulation.controller;

import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.engine.simulation.service.FlightSimulationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/flight-tracking")
@AllArgsConstructor
public class FlightTrackingController {
    private final FlightSimulationService flightSimulationService;

    @GetMapping
    public FlightMovementTest testMovement(){
        return flightSimulationService.test();
    }
}
