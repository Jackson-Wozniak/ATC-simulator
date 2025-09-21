package com.atcsimulator.app.modules.airport.entity.component;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.Coordinate;
import com.atcsimulator.app.modules.airport.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "runway")
@Table(name = "runways")
@Getter
@Setter
@NoArgsConstructor
public class Runway extends BaseEntity {
    @Column(name = "runway_name")
    private String runwayName;

    @Column(name = "runway_heading")
    private int heading;

    @Embedded
    private Coordinate startingCoordinate;

    @Embedded
    private Coordinate endingCoordinate;

    @Column(name = "width")
    private double width;

    @Column(name = "length")
    private double length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Runway(String runwayName, int heading, double width, double length, Coordinate start){
        this.runwayName = runwayName;
        this.heading = heading;
        this.width = width;
        this.length = length;
        this.startingCoordinate = start;

        double radians = Math.toRadians(heading);
        double endX = start.getX() + length * Math.sin(radians);
        double endY = start.getY() + length * Math.cos(radians);

        this.endingCoordinate = new Coordinate(endX, endY, 0);
    }
}
