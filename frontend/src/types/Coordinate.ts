
export interface Coordinate {
    x: number;
    y: number;
    altitude: number;
}

export function coordinates(x: number, y: number, altitude: number): Coordinate {
    return {
        x: x,
        y: y,
        altitude: altitude
    }
}