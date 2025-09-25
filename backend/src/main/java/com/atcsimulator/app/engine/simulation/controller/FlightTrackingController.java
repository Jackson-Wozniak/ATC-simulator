package com.atcsimulator.app.engine.simulation.controller;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.engine.simulation.service.FlightSimulationService;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.flight.dto.FlightPathPositionDTO;
import com.atcsimulator.app.modules.flight.entity.Flight;
import com.atcsimulator.app.modules.flight.entity.FlightPathPosition;
import com.atcsimulator.app.modules.flight.entity.FlightPlan;
import com.atcsimulator.app.modules.plane.entity.AircraftState;
import com.atcsimulator.app.modules.plane.entity.Plane;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/flight-tracking")
@AllArgsConstructor
public class FlightTrackingController {
    private final FlightSimulationService flightSimulationService;

    @GetMapping
    public List<FlightPathPositionDTO> testMovement(){
        Plane plane = new Plane("TEST", new AircraftState(
                LocalCoordinate.fromMeters(200, 200, 0), 10, 100));
        FlightPlan plan = new FlightPlan();
        plan.setDestinationRunway(new Runway("TEST", 50, 10, 200, LocalCoordinate.fromMeters(100, 100, 0)));

        Flight flight = new Flight(plane, plan);
        plan.setFlight(flight);
        flightSimulationService.progressFlight(flight);
        flightSimulationService.progressFlight(flight);
        flightSimulationService.progressFlight(flight);
        flightSimulationService.progressFlight(flight);
        return flight.getFlightPlan().getFlightPathPositions().stream().map(FlightPathPositionDTO::new).toList();
    }
}
