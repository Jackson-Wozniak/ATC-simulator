package com.atcsimulator.app.engine.simulation.service;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.engine.simulation.dto.FlightMovementTest;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.plane.entity.Plane;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightPathService {

    public FlightMovementTest test(){
        Runway runway = new Runway("36", 360, 10,
                200, LocalCoordinate.fromMeters(100, 100, 0));

        Airport airport = new Airport.Builder("Test Name", "Test Location")
                .runways(List.of(runway))
                .build();
        Plane plane = new Plane();
        plane.setCallsign("Test");
        plane.setCoordinates(LocalCoordinate.fromMeters(200, 200, 0));

        plane.setHeading(Math.atan2(-100, -100));
        System.out.println(plane.getHeading());
        plane.setSpeedKnots(100);

        FlightMovementTest test = new FlightMovementTest(runway.getStartingLocalCoordinate(),
                plane.getCoordinates(), new HashMap<>());
        double metersPerSecond = plane.getSpeedKnots() * 0.514444;
        for(int i = 1; i <= 100; i++){
            double distance = metersPerSecond * 1; //1 second
            double changeX = distance * Math.sin(plane.getHeading());
            double changeY = distance * Math.cos(plane.getHeading());
            double newX = plane.getCoordinates().getX().getValue() + changeX;
            double newY = plane.getCoordinates().getY().getValue() + changeY;
            plane.setCoordinates(LocalCoordinate.fromMeters(newX, newY, 0));
            test.getCoordinateTrack().put(i, plane.getCoordinates());
        }
        return test;
    }
}
