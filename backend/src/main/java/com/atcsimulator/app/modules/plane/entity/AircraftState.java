package com.atcsimulator.app.modules.plane.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "aircraftState")
@Table(name = "aircraft_states")
@Getter
@Setter
@NoArgsConstructor
public class AircraftState extends BaseEntity {
    @Embedded
    private LocalCoordinate coordinates;

    @Column(name = "speed_knots")
    private double speedKnots;

    @Column(name = "heading")
    private double heading;

    @OneToOne(mappedBy = "aircraftState")
    @JoinColumn(name = "plane_id", referencedColumnName = "id")
    private Plane plane;
}
