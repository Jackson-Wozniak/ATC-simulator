package com.atcsimulator.app.engine.simulation.service;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.physics.service.FlightPhysicsEngine;
import com.atcsimulator.app.engine.simulation.object.NavigationTargets;
import com.atcsimulator.app.modules.flight.entity.Flight;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NavigationService {
    private final FlightPhysicsEngine flightPhysicsEngine;

    public void updateFlightPlan(){

    }

    /*
    computeNextControlInputs(aircraftState, destination, etc.)
        this method returns the control input changes that are needed to reach the correct
        destination. For example, it may return pitch -3degrees, speed 120 knots, heading 120degrees
        which is what is needed to actually reach the destination. At that point, the calling
        method takes the new inputs, and calls the physics engine to move the plane.
        PhysicsEngine can take desired destination inputs, and incorporates those changes
        into the movement. I.e. if the current heading is 125degrees and NavigationPlanService
        requests
        120degree heading to destination, then PhysicsEngine will move -3degrees in one second
        (max of 3 degrees per second turn), and update the new heading as 122degrees. Then, the
        next run of the simulation would check if the trajectory is right yet (it isn't, so another
        change is made while moving forward).

        IMPORTANTLY, this class only computes DESIRED CHANGES, not MOVEMENT
     */
    public NavigationTargets computeControls(Flight flight){
        LocalCoordinate currentPosition = flight.getPlane().getPosition();
        LocalCoordinate destinationPosition = flight.getFlightPlan()
                .getDestinationRunway().getStartingCoordinates();
        double targetHeading = flightPhysicsEngine.headingToDestination(currentPosition, destinationPosition);
        return new NavigationTargets(targetHeading, flight.getPlane().getAircraftState().getSpeedKnots());
    }
}
