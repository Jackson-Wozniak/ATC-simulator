package com.atcsimulator.app.modules.flight.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.plane.entity.Plane;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity(name = "flightPlan")
@Table(name = "flight_plans")
@Getter
@Setter
@NoArgsConstructor
public class FlightPlan extends BaseEntity {
    @OneToOne(mappedBy = "flightPlan")
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    //eventually, well want to keep a queue of coordinates/points to reach as part of flight path

    @ManyToOne
    @JoinColumn(name = "runway", referencedColumnName = "id")
    private Runway destinationRunway;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FlightPathPosition> flightPathPositions = new ArrayList<>();
}
