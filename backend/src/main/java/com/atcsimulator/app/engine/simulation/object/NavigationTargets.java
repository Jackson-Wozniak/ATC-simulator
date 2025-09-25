package com.atcsimulator.app.engine.simulation.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NavigationTargets {
    private double heading;
    private double speedKnots;

    public NavigationTargets(double heading, double speedKnots) {
        this.heading = heading;
        this.speedKnots = speedKnots;
    }
}
