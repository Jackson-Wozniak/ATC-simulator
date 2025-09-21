package com.atcsimulator.app.modules.airport.entity.component;

import com.atcsimulator.app.core.entity.BaseEntity;
import com.atcsimulator.app.modules.airport.entity.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "runway")
@Table(name = "runways")
@Getter
@Setter
@NoArgsConstructor
public class Runway extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id")
    private Airport airport;

}
