package com.atcsimulator.app.engine.simulation.service;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.physics.service.FlightPhysicsEngine;
import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.engine.simulation.object.NavigationTargets;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.flight.entity.Flight;
import com.atcsimulator.app.modules.flight.entity.FlightPathPosition;
import com.atcsimulator.app.modules.plane.entity.AircraftState;
import com.atcsimulator.app.modules.plane.entity.Plane;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightSimulationService {
    private final NavigationService navigationService;
    private final FlightPhysicsEngine flightPhysicsEngine;

    public void progressFlight(Flight flight){
        NavigationTargets navigationTargets = navigationService.computeControls(flight);

        Plane plane = flight.getPlane();
        flightPhysicsEngine.simulateMovement(plane.getAircraftState(), 1, navigationTargets);

        FlightPathPosition position = new FlightPathPosition(flight.getFlightPlan(), Instant.now(), plane.getPosition(), plane.getAircraftState().getHeading());

        flight.getFlightPlan().getFlightPathPositions().add(position);
        navigationService.updateFlightPlan();
    }
}
