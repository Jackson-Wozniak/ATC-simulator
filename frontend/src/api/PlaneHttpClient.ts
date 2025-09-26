import type { PlanePositionDto } from "../types/api/PositionDto";

export async function fetchPlanePositions(){
    const response = await fetch("http://localhost:8080/api/v1/flight-tracking");
    const positions: PlanePositionDto[] = await response.json();
    return positions;
}