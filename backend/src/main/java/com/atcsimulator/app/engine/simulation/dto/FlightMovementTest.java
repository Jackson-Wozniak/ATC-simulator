package com.atcsimulator.app.engine.simulation.dto;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class FlightMovementTest {
    private LocalCoordinate destination;
    private LocalCoordinate start;
    private Map<Integer, LocalCoordinate> coordinateTrack;
}
