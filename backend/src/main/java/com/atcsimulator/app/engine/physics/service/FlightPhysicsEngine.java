package com.atcsimulator.app.engine.physics.service;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.physics.utils.PhysicsUtils;
import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.engine.simulation.object.NavigationTargets;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.plane.entity.AircraftState;
import com.atcsimulator.app.modules.plane.entity.Plane;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class FlightPhysicsEngine {

    public void simulateMovement(AircraftState aircraftState, int secondsPassed,
                                          NavigationTargets navigationTargets){
        simulateMovement(aircraftState, secondsPassed);
    }

    public void simulateMovement(AircraftState aircraftState, int secondsPassed){
        double mathAngle = Math.toRadians(aircraftState.getHeading());

        double metersPerSecond = PhysicsUtils.knotsToMetersPerSecond(aircraftState.getSpeedKnots());
        double distance = metersPerSecond * secondsPassed;
        double changeX = distance * Math.sin(mathAngle);
        double changeY = distance * Math.cos(mathAngle);
        double newX = aircraftState.getCoordinates().x() + changeX;
        double newY = aircraftState.getCoordinates().y() + changeY;
        aircraftState.setCoordinates(LocalCoordinate.fromMeters(newX, newY, 0));
    }

    public double headingToDestination(LocalCoordinate position, LocalCoordinate destination){
        double differenceX = destination.x() - position.x();
        double differenceY = destination.y() - position.y();

        double radians = Math.atan2(differenceY, differenceX);

        return Math.toDegrees(radians);
    }
}
