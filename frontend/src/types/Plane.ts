import type { Coordinate } from "./Coordinate";

export interface Plane {
    callsign: string;
    aircraftState: AircraftState;
}

export interface AircraftState {
    position: Coordinate,
    heading: number,
    speedKnots: number
}