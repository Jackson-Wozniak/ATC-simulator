package com.atcsimulator.app.modules.plane.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AircraftState {
    @Embedded
    private LocalCoordinate coordinates;

    @Column(name = "speed_knots")
    private double speedKnots;

    @Column(name = "heading")
    private double heading;

    public AircraftState(LocalCoordinate coordinates, double speedKnots, double heading) {
        this.coordinates = coordinates;
        this.speedKnots = speedKnots;
        this.heading = heading;
    }
}
