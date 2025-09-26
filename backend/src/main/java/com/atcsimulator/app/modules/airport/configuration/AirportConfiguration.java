package com.atcsimulator.app.modules.airport.configuration;

import com.atcsimulator.app.core.utils.JsonUtils;
import com.atcsimulator.app.modules.airport.entity.Airport;
import com.atcsimulator.app.modules.airport.object.AirportJsonObject;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.List;

@Configuration
@DependsOn(value = {"appConfiguration"})
@AllArgsConstructor
public class AirportConfiguration {
    private static final String PATH = "data/airports.json";

    @PostConstruct
    public void readAirportJSON() throws IOException {
        List<AirportJsonObject> airports = JsonUtils.mapFromJson(PATH, new TypeReference<List<AirportJsonObject>>() { });
        System.out.println(airports.stream().map(AirportJsonObject::map).toList());
    }
}
