interface Coordinate {
  x: number;
  y: number;
}

interface Runway {
  start: Coordinate;
  end: Coordinate;
  width: number;
  name: string;
}

interface Taxiway {
    points: Coordinate[];
    name: string;
    width: number;
}

interface Plane {
  position: Coordinate;
  altitude: number;
  heading: number;
  callsign: string;
}

function drawRunway(ctx: CanvasRenderingContext2D, runway: Runway) {
  const dx = runway.end.x - runway.start.x;
  const dy = runway.end.y - runway.start.y;
  const length = Math.sqrt(dx*dx + dy*dy);
  const angle = Math.atan2(dy, dx);

  ctx.save();
  ctx.translate(runway.start.x, runway.start.y);
  ctx.rotate(angle);

  ctx.fillStyle = "#555"; // dark gray runway
  ctx.fillRect(0, -runway.width/2, length, runway.width);

  // Draw runway name
  ctx.fillStyle = "white";
  ctx.font = "14px Arial";
  ctx.fillText(runway.name, length/2 - 10, 0);

  ctx.restore();
}

function drawTaxiway(ctx: CanvasRenderingContext2D, taxiway: Taxiway) {
    if (taxiway.points.length < 2) return;
    ctx.save();
    ctx.strokeStyle = "#888";
    ctx.lineWidth = taxiway.width;
    ctx.beginPath();
    ctx.moveTo(taxiway.points[0].x, taxiway.points[0].y);
    for (let i = 1; i < taxiway.points.length; i++) {
        ctx.lineTo(taxiway.points[i].x, taxiway.points[i].y);
    }
    ctx.stroke();

    // Draw name at midpoint
    const mid = taxiway.points[Math.floor(taxiway.points.length / 2)];
    ctx.fillStyle = "white";
    ctx.font = "12px Arial";
    ctx.fillText(taxiway.name, mid.x, mid.y);
    ctx.restore();
}

function drawPlane(ctx: CanvasRenderingContext2D, plane: Plane) {
  ctx.beginPath();
  ctx.arc(plane.position.x, plane.position.y, 5, 0, 2*Math.PI);
  ctx.fillStyle = plane.altitude > 0 ? "blue" : "green";
  ctx.fill();
}

window.addEventListener("load", () => {
    const airportCanvas = document.getElementById("airportCanvas") as HTMLCanvasElement;
    const planeCanvas = document.getElementById("planeCanvas") as HTMLCanvasElement;

    const planeCtx = planeCanvas.getContext("2d");
    const airportCtx = airportCanvas.getContext("2d");

    if(airportCtx == null || planeCtx == null) return;

    // Runways
    const runways: Runway[] = [
        { start: { x: 100, y: 100 }, end: { x: 100, y: 500 }, width: 20, name: "18R" },
        { start: { x: 200, y: 200 }, end: { x: 600, y: 200 }, width: 20, name: "36L" },
    ];

    // Taxiways
    const taxiways: Taxiway[] = [
        { points: [{ x: 100, y: 500 }, { x: 200, y: 500 }, { x: 200, y: 200 }], width: 10, name: "Taxiway A" },
        { points: [{ x: 600, y: 200 }, { x: 600, y: 300 }, { x: 500, y: 300 }], width: 10, name: "Taxiway B" },
    ];

    // Planes
    let planes: Plane[] = [
        { position: { x: 100, y: 100 }, heading: 0, altitude: 0, callsign: "AAL123" },
        { position: { x: 210, y: 210 }, heading: 90, altitude: 0, callsign: "DAL456" },
        { position: { x: 400, y: 220 }, heading: 270, altitude: 3000, callsign: "UAL789" }, // airborne
    ];

    // Draw all
    airportCtx.clearRect(0, 0, airportCanvas.width, airportCanvas.height);
    runways.forEach(r => drawRunway(airportCtx, r));
    taxiways.forEach(t => drawTaxiway(airportCtx, t));

    planeCtx.clearRect(0, 0, planeCanvas.width, planeCanvas.height);
    planes.forEach(p => drawPlane(planeCtx, p));
    
    drawAllPlanes(planeCtx, planes, planeCanvas, 0);
});

function drawAllPlanes(planeCtx: CanvasRenderingContext2D, planes: Plane[], 
    planeCanvas: HTMLCanvasElement, count: number){
    planeCtx.clearRect(0, 0, planeCanvas.width, planeCanvas.height);
    planes.forEach(p => {
        p.position.x += count > 150 ? 1 : -1;
        drawPlane(planeCtx, p);
    });

    requestAnimationFrame(() => drawAllPlanes(planeCtx, planes, planeCanvas, count + 1));
}
