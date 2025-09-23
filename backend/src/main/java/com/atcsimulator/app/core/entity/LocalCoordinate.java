package com.atcsimulator.app.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class LocalCoordinate {
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "x_coordinate_meters"))
    private Meters x;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "y_coordinate_meters"))
    private Meters y;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "altitude_meters"))
    private Meters altitude;

    protected LocalCoordinate(double x, double y, double altitude){
        this.x = new Meters(x);
        this.y = new Meters(y);
        this.altitude = new Meters(altitude);
    }

    public static LocalCoordinate fromMeters(double x, double y, double altitude){
        return new LocalCoordinate(x, y, altitude);
    }
}
