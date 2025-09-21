package com.atcsimulator.app.modules.airport.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.modules.airport.entity.component.ParkingSpot;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.airport.entity.component.Taxiway;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "airport")
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
public class Airport extends BaseEntity {
    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "airport_location")
    private String airportLocation;

    @Column(name = "map_coordinate_width")
    private int mapCoordinateWidth;

    @Column(name = "map_coordinate_height")
    private int mapCoordinateHeight;

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Runway> runways = new ArrayList<>();

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Taxiway> taxiways = new ArrayList<>();

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    public Airport(Builder builder){
        super();
        this.airportName = builder.name;
        this.airportLocation = builder.location;
        this.mapCoordinateWidth = builder.mapCoordinateWidth;
        this.mapCoordinateHeight = builder.mapCoordinateHeight;
        this.runways = builder.runways;
        this.taxiways = builder.taxiways;
        this.parkingSpots = builder.parkingSpots;
    }

    public static class Builder {
        private final String name;
        private final String location;
        private int mapCoordinateWidth;
        private int mapCoordinateHeight;
        private final List<Runway> runways = new ArrayList<>();
        private final List<Taxiway> taxiways = new ArrayList<>();
        private final List<ParkingSpot> parkingSpots = new ArrayList<>();

        public Builder(String name, String location){
            this.name = name;
            this.location = location;
        }

        public Builder coordinateDimensions(int width, int height){
            this.mapCoordinateWidth = width;
            this.mapCoordinateHeight = height;
            return this;
        }

        public Builder runways(List<Runway> runways){
            this.runways.addAll(runways);
            return this;
        }

        public Builder taxiways(List<Taxiway> taxiways){
            this.taxiways.addAll(taxiways);
            return this;
        }

        public Builder parkingSpots(List<ParkingSpot> parkingSpots){
            this.parkingSpots.addAll(parkingSpots);
            return this;
        }

        public Airport build(){
            return new Airport(this);
        }
    }
}
