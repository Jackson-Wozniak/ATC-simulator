package com.atcsimulator.app.modules.airport.entity.component;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.core.entity.Meters;
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
    @AttributeOverrides({
            @AttributeOverride(name = "x.value", column = @Column(name = "starting_x")),
            @AttributeOverride(name = "y.value", column = @Column(name = "starting_y")),
            @AttributeOverride(name = "altitude.value", column = @Column(name = "starting_altitude"))
    })
    private LocalCoordinate startingCoordinates;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x.value", column = @Column(name = "ending_x")),
            @AttributeOverride(name = "y.value", column = @Column(name = "ending_y")),
            @AttributeOverride(name = "altitude.value", column = @Column(name = "ending_altitude"))
    })
    private LocalCoordinate endingCoordinates;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "width_meters"))
    private Meters width;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "length_meters"))
    private Meters length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Runway(String runwayName, int heading, double width, double length, LocalCoordinate start){
        this.runwayName = runwayName;
        this.heading = heading;
        this.width = new Meters(width);
        this.length = new Meters(length);
        this.startingCoordinates = start;

        double radians = Math.toRadians(heading);
        double endX = start.getX().getValue() + length * Math.sin(radians);
        double endY = start.getY().getValue() + length * Math.cos(radians);

        this.endingCoordinates = LocalCoordinate.fromMeters(endX, endY, 0);
    }
}
