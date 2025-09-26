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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Taxiway(String name, double width, LocalCoordinate start, LocalCoordinate end){
        this.taxiwayName = name;
        this.width = new Meters(width);
        this.startingCoordinates = start;
        this.endingCoordinates = end;
    }
}
