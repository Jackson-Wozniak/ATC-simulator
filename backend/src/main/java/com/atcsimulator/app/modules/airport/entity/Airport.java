package com.atcsimulator.app.modules.airport.entity;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.modules.airport.entity.component.ParkingSpot;
import com.atcsimulator.app.modules.airport.entity.component.Runway;
import com.atcsimulator.app.modules.airport.entity.component.Taxiway;
import com.atcsimulator.app.modules.plane.entity.Plane;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Runway> runways = new ArrayList<>();

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Taxiway> taxiways = new ArrayList<>();

    @OneToMany(mappedBy = "airport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    protected Airport(Builder builder){
        super();
        this.airportName = builder.name;
        this.airportLocation = builder.location;
        this.runways.addAll(builder.runways);
        this.taxiways.addAll(builder.taxiways);
        this.parkingSpots.addAll(builder.parkingSpots);
        this.runways.forEach(r -> r.setAirport(this));
        this.taxiways.forEach(r -> r.setAirport(this));
        this.parkingSpots.forEach(r -> r.setAirport(this));
    }

    public static class Builder {
        private final String name;
        private final String location;
        private final List<Runway> runways = new ArrayList<>();
        private final List<Taxiway> taxiways = new ArrayList<>();
        private final List<ParkingSpot> parkingSpots = new ArrayList<>();

        public Builder(String name, String location){
            this.name = name;
            this.location = location;
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
            Airport airport = new Airport(this);
            airport.runways.forEach(r -> r.setAirport(airport));
            airport.taxiways.forEach(t -> t.setAirport(airport));
            airport.parkingSpots.forEach(p -> p.setAirport(airport));
            return airport;
        }
    }

    @Override
    public String toString(){
        return airportName + ", " + airportLocation + ", " + runways.stream().map(Runway::getRunwayName).toList();
    }
}
