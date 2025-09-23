package com.atcsimulator.app.core.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meters {
    private Double value;

    public Meters(double value){
        this.value = value;
    }
}
