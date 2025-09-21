package com.atcsimulator.app.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    @Column(name = "x_coordinate")
    private double x;
    @Column(name = "y_coordinate")
    private double y;
    @Column(name = "altitude_meters")
    private double altitudeMeters;
}
