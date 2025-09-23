package com.atcsimulator.app.modules.plane.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.modules.airport.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "plane")
@Table(name = "planes")
@Getter
@Setter
@NoArgsConstructor
public class Plane extends BaseEntity {
    @Column(name = "callsign")
    private String callsign;

    @Embedded
    private LocalCoordinate coordinates;

    @Column(name = "speed_knots")
    private double speedKnots;

    @Column(name = "heading")
    private double heading;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;
}
