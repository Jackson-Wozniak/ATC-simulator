package com.atcsimulator.app.modules.airport.object;

import com.atcsimulator.app.modules.airport.entity.Airport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AirportJsonObject {
    private String name;
    private String location;
    private List<RunwayJsonObject> runways;
    private List<TaxiwayJsonObject> taxiways;

    public Airport map(){
        return new Airport.Builder(name, location)
                .runways(runways.stream().map(RunwayJsonObject::map).toList())
                .taxiways(taxiways.stream().map(TaxiwayJsonObject::map).toList())
                .build();
    }
}
