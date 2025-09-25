package com.atcsimulator.app.engine.simulation.scheduler;

import com.atcsimulator.app.engine.simulation.service.FlightSimulationService;
import com.atcsimulator.app.modules.flight.entity.Flight;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@AllArgsConstructor
public class FlightSimulationScheduler {
    private final FlightSimulationService flightSimulationService;

    @Scheduled(fixedDelay = 1000)
    public void run(){

    }
}
