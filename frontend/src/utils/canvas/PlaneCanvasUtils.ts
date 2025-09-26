import type { Plane } from "../../types/Plane";

export function drawPlane(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D, plane: Plane) {
    ctx.save();
    ctx.setTransform(1, 0, 0, 1, 0, 0);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.restore();

    ctx.beginPath();
    ctx.arc(plane.aircraftState.position.x, plane.aircraftState.position.y, 20, 0, 2*Math.PI);
    ctx.fillStyle = plane.aircraftState.position.altitude > 0 ? "blue" : "red";
    ctx.fill();
}