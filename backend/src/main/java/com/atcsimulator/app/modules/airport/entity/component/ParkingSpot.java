package com.atcsimulator.app.modules.airport.entity.component;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.modules.airport.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "parkingSpot")
@Table(name = "parking_spots")
@Getter
@Setter
@NoArgsConstructor
public class ParkingSpot extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;
}
