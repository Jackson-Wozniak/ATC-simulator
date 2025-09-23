package com.atcsimulator.app.modules.airport.entity.component;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.core.entity.Meters;
import com.atcsimulator.app.modules.airport.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "taxiway")
@Table(name = "taxiways")
@Getter
@Setter
@NoArgsConstructor
public class Taxiway extends BaseEntity {
    @Column(name = "taxiway_name")
    private String taxiwayName;

    @Column(name = "taxiway_heading")
    private int heading;

    @Embedded
    private LocalCoordinate startingLocalCoordinate;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "width_meters"))
    private Meters width;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "length_meters"))
    private Meters length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Taxiway(String name, int heading, double width,
                   double length, LocalCoordinate start){
        this.taxiwayName = name;
        this.heading = heading;
        this.width = new Meters(width);
        this.length = new Meters(length);
        this.startingLocalCoordinate = start;

        double radians = Math.toRadians(heading);
        double endX = start.getX().getValue() + length * Math.sin(radians);
        double endY = start.getY().getValue() + length * Math.cos(radians);

        //this.endingLocalCoordinate = LocalCoordinate.fromMeters(endX, endY, 0);
    }
}
