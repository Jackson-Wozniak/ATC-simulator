package com.atcsimulator.app.engine.physics.service;

import com.atcsimulator.app.engine.simulation.object.NavigationTargets;
import com.atcsimulator.app.modules.plane.entity.AircraftState;
import org.springframework.stereotype.Component;

@Component
public class FlightPhysicsEngine {

    public AircraftState simulateMovement(AircraftState aircraftState, int secondsPassed,
                                          NavigationTargets navigationTargets){
        return simulateMovement(aircraftState, secondsPassed);
    }

    public AircraftState simulateMovement(AircraftState aircraftState, int secondsPassed){
        return null;
    }
}
