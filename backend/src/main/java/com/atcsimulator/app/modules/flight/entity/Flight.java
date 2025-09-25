package com.atcsimulator.app.modules.flight.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.modules.plane.entity.Plane;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id")
    private Plane plane;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_plan_id", referencedColumnName = "id")
    private FlightPlan flightPlan;

    public Flight(Plane plane, FlightPlan flightPlan){
        this.plane = plane;
        this.flightPlan = flightPlan;
    }
}
