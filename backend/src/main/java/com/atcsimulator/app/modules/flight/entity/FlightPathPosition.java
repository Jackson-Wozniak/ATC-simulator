package com.atcsimulator.app.modules.flight.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity(name = "flightPathPosition")
@Table(name = "flight_path_positions")
@Getter
@Setter
@NoArgsConstructor
public class FlightPathPosition extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "flight_plan_id", referencedColumnName = "id")
    private FlightPlan flightPlan;

    @Column(name = "position_timestamp")
    private Instant timestamp;

    @Embedded
    private LocalCoordinate position;

    private double heading;

    public FlightPathPosition(FlightPlan plan, Instant instant, LocalCoordinate position, double heading){
        flightPlan = plan;
        timestamp = instant;
        this.position = position;
        this.heading = heading;
    }
}
