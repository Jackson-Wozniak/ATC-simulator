package com.atcsimulator.app.engine.simulation.service;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.physics.service.FlightPhysicsEngine;
import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.engine.simulation.object.NavigationTargets;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.flight.entity.Flight;
import com.atcsimulator.app.modules.plane.entity.AircraftState;
import com.atcsimulator.app.modules.plane.entity.Plane;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightSimulationService {
    private final NavigationService navigationService;
    private final FlightPhysicsEngine flightPhysicsEngine;

    public void progressFlight(Flight flight){
        NavigationTargets navigationTargets = navigationService.computeControls();

        flightPhysicsEngine.simulateMovement(null, 1, navigationTargets);

        navigationService.updateFlightPlan();
    }

    public FlightMovementTest test(){
        Runway runway = new Runway("36", 360, 10,
                200, LocalCoordinate.fromMeters(100, 100, 0));

        Airport airport = new Airport.Builder("Test Name", "Test Location")
                .runways(List.of(runway))
                .build();
        Plane plane = new Plane();
        plane.setCallsign("Test");

        AircraftState state = new AircraftState();
        state.setCoordinates(LocalCoordinate.fromMeters(200, 200, 0));

        state.setHeading(Math.atan2(-100, -100));
        state.setSpeedKnots(100);

        FlightMovementTest test = new FlightMovementTest(runway.getStartingLocalCoordinate(),
                state.getCoordinates(), new HashMap<>());
        double metersPerSecond = state.getSpeedKnots() * 0.514444;
        for(int i = 1; i <= 100; i++){
            double distance = metersPerSecond * 1; //1 second
            double changeX = distance * Math.sin(state.getHeading());
            double changeY = distance * Math.cos(state.getHeading());
            double newX = state.getCoordinates().getX().getValue() + changeX;
            double newY = state.getCoordinates().getY().getValue() + changeY;
            state.setCoordinates(LocalCoordinate.fromMeters(newX, newY, 0));
            test.getCoordinateTrack().put(i, state.getCoordinates());
        }
        return test;
    }
}
