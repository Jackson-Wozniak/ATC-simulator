package com.atcsimulator.app.modules.airport.object;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RunwayJsonObject {
    private String name;
    private int heading;
    private double startingX;
    private double startingY;
    private double width;
    private double length;

    public Runway map(){
        return new Runway(name, heading, width, length,
                LocalCoordinate.fromMeters(startingX, startingY, 0));
    }
}
