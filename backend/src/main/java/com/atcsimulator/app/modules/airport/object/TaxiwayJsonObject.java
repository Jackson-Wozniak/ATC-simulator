package com.atcsimulator.app.modules.airport.object;

import com.atcsimulator.app.core.entity.LocalCoordinate;
import com.atcsimulator.app.modules.airport.entity.component.Taxiway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxiwayJsonObject {
    private String name;
    private double widthMeters;
    private double startingX;
    private double startingY;
    private double endingX;
    private double endingY;

    public Taxiway map(){
        return new Taxiway(name, widthMeters,
                LocalCoordinate.fromMeters(startingX, startingY, 0),
                LocalCoordinate.fromMeters(endingX, endingY, 0));
    }
}
