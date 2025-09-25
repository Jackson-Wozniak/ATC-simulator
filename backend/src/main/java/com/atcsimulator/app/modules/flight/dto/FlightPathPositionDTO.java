package com.atcsimulator.app.modules.flight.dto;

import com.atcsimulator.app.modules.flight.entity.FlightPathPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightPathPositionDTO {
    private final String timestamp;
    private final double xPosition;
    private final double yPosition;
    private final double heading;

    public FlightPathPositionDTO(FlightPathPosition position){
        this.timestamp = position.getTimestamp().toString();
        this.xPosition = position.getPosition().x();
        this.yPosition = position.getPosition().y();
        this.heading = position.getHeading();
    }
}
